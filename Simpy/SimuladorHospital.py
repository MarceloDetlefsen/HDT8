import simpy
import random
import statistics
from dataclasses import dataclass
from collections import defaultdict

'''
Universidad del Valle de Guatemala
Algoritmos y Estructuras de Datos
Ing. Douglas Barrios
Autores: Marcelo Detlefsen, Julián Divas
Creación: 31/03/2025
Última modificación: 31/03/2025
Nombre del Archivo: SimuladorHospital.py
Descripción: Simulación de un sistema de emergencias hospitalarias utilizando DES
             con colas de prioridad basadas en triage.
'''

# Configuración inicial
random.seed(10)  # Para reproducibilidad (punto b)

@dataclass
class Configuracion:
    enfermeras: int
    doctores: int
    rayos_x: int
    laboratorios: int
    tiempo_simulacion: int = 480  # 8 horas en minutos
    intervalo_llegada: float = 5.0  # minutos entre pacientes

class Paciente:
    def __init__(self, id_paciente, hora_llegada, codigo_emergencia):
        self.id = id_paciente
        self.hora_llegada = hora_llegada
        self.codigo_emergencia = codigo_emergencia
        # Asignación inicial basada en código
        self.prioridad = ord(codigo_emergencia) - ord('A') + 1
        self.prioridad_garantizada = None  # Si se establece, no cambiará en triage
        self.tiempos = {
            'inicio_triage': None,
            'fin_triage': None,
            'inicio_doctor': None,
            'fin_doctor': None,
            'fin_atencion': None
        }

class SalaEmergencias:
    def __init__(self, env, config):
        self.env = env
        self.config = config
        
        # Recursos con prioridad (punto e)
        self.enfermeras = simpy.Resource(env, config.enfermeras)
        self.doctores = simpy.PriorityResource(env, config.doctores)
        self.rayos_x = simpy.PriorityResource(env, config.rayos_x)
        self.laboratorios = simpy.PriorityResource(env, config.laboratorios)
        
        # Estadísticas
        self.pacientes_atendidos = []
        self.tiempos_espera = []
        
    def atender_paciente(self, paciente):
        # Triage
        paciente.tiempos['inicio_triage'] = self.env.now
        with self.enfermeras.request() as req:
            yield req
            tiempo_triage = random.uniform(8, 12)
            yield self.env.timeout(tiempo_triage)
            paciente.tiempos['fin_triage'] = self.env.now
            
            # Asignar prioridad, pero respetando prioridades garantizadas
            if paciente.prioridad_garantizada is None:
                paciente.prioridad = random.randint(1, 5)
            else:
                paciente.prioridad = paciente.prioridad_garantizada
        
        # Atención médica
        paciente.tiempos['inicio_doctor'] = self.env.now
        with self.doctores.request(priority=paciente.prioridad) as req:  # punto f
            yield req
            tiempo_doctor = random.uniform(15, 45)  # 15-45 minutos
            yield self.env.timeout(tiempo_doctor)
            paciente.tiempos['fin_doctor'] = self.env.now
            
            # 40% de probabilidad de necesitar exámenes
            if random.random() < 0.4:
                if random.random() < 0.7:  # 70% rayos X
                    with self.rayos_x.request(priority=paciente.prioridad) as req_examen:
                        yield req_examen
                        yield self.env.timeout(random.uniform(10, 20))
                else:  # 30% laboratorio
                    with self.laboratorios.request(priority=paciente.prioridad) as req_examen:
                        yield req_examen
                        yield self.env.timeout(random.uniform(20, 30))
        
        paciente.tiempos['fin_atencion'] = self.env.now
        tiempo_total = paciente.tiempos['fin_atencion'] - paciente.hora_llegada
        self.pacientes_atendidos.append(paciente)
        self.tiempos_espera.append(tiempo_total)

def generador_pacientes(env, sala):
    paciente_id = 0
    # Lista que contiene todas las prioridades del 1 al 5
    prioridades_necesarias = list(range(1, 6))
    # Tiempo límite para inyectar pacientes de prioridades faltantes (a los 300 minutos)
    tiempo_limite_inyeccion = 300
    
    # Iniciar con distribución normal
    while True:
        # Cada 60 minutos, verificar si hay prioridades sin pacientes
        if env.now > 0 and env.now % 60 < 1 and env.now < tiempo_limite_inyeccion:
            # Obtener prioridades actuales de pacientes atendidos
            prioridades_existentes = set(p.prioridad for p in sala.pacientes_atendidos)
            # Identificar prioridades faltantes
            prioridades_faltantes = [p for p in prioridades_necesarias if p not in prioridades_existentes]
            
            # Si hay prioridades faltantes, inyectar pacientes con esas prioridades
            if prioridades_faltantes:
                for prioridad in prioridades_faltantes:
                    # Crear paciente con prioridad específica
                    paciente = Paciente(paciente_id, env.now, chr(ord('A') + prioridad - 1))
                    paciente.prioridad_garantizada = prioridad  # Marcar para no cambiar en triage
                    env.process(sala.atender_paciente(paciente))
                    paciente_id += 1
                    yield env.timeout(0.5)  # Pequeño intervalo entre inyecciones
        
        # Generación normal de pacientes con prioridad aleatoria
        codigo = chr(ord('A') + random.randint(0, 4))  # Códigos A-E
        paciente = Paciente(paciente_id, env.now, codigo)
        env.process(sala.atender_paciente(paciente))
        paciente_id += 1
        yield env.timeout(random.expovariate(1.0 / sala.config.intervalo_llegada))

def ejecutar_simulacion(config):
    env = simpy.Environment()
    sala = SalaEmergencias(env, config)
    env.process(generador_pacientes(env, sala))
    env.run(until=config.tiempo_simulacion)
    
    # Calcular estadísticas
    if sala.tiempos_espera:
        tiempo_promedio = statistics.mean(sala.tiempos_espera)
        desviacion = statistics.stdev(sala.tiempos_espera) if len(sala.tiempos_espera) > 1 else 0
    else:
        tiempo_promedio, desviacion = 0, 0
    
    return {
        'configuracion': config,
        'pacientes_atendidos': len(sala.pacientes_atendidos),
        'tiempo_promedio': tiempo_promedio,
        'desviacion': desviacion,
        'por_prioridad': calcular_por_prioridad(sala.pacientes_atendidos)
    }

def calcular_por_prioridad(pacientes):
    resultados = defaultdict(lambda: {'cantidad': 0, 'tiempo_promedio': 0})
    
    for p in pacientes:
        resultados[p.prioridad]['cantidad'] += 1
        resultados[p.prioridad]['tiempo_total'] = (
            resultados[p.prioridad].get('tiempo_total', 0) + 
            (p.tiempos['fin_atencion'] - p.hora_llegada)
        )
    
    # Calcular promedios
    for prioridad in range(1, 6):
        if resultados[prioridad]['cantidad'] > 0:
            resultados[prioridad]['tiempo_promedio'] = (
                resultados[prioridad]['tiempo_total'] / resultados[prioridad]['cantidad']
            )
    
    return resultados

def main():
    # Configuraciones a probar (punto: recursos variables)
    configs = [
        Configuracion(enfermeras=1, doctores=2, rayos_x=1, laboratorios=1),  # Básica
        Configuracion(enfermeras=2, doctores=3, rayos_x=1, laboratorios=1),  # Intermedia
        Configuracion(enfermeras=3, doctores=5, rayos_x=2, laboratorios=2),  # Avanzada
        Configuracion(enfermeras=2, doctores=4, rayos_x=1, laboratorios=1, intervalo_llegada=3),  # Fin de semana
        Configuracion(enfermeras=2, doctores=4, rayos_x=1, laboratorios=1, intervalo_llegada=1.5, tiempo_simulacion=600),  # Feriado
    ]
    
    print("=== Simulación de Sala de Emergencias ===")
    print("Configuraciones a evaluar:\n")
    
    resultados = []
    for i, config in enumerate(configs, 1):
        print(f"Configuración {i}:")
        print(f"- Enfermeras: {config.enfermeras}")
        print(f"- Doctores: {config.doctores}")
        print(f"- Rayos X: {config.rayos_x}")
        print(f"- Laboratorios: {config.laboratorios}")
        print(f"- Intervalo llegada: {config.intervalo_llegada} min\n")
        
        resultado = ejecutar_simulacion(config)
        resultados.append(resultado)
        
        print(f"Resultados Config {i}:")
        print(f"Pacientes atendidos: {resultado['pacientes_atendidos']}")
        print(f"Tiempo promedio: {resultado['tiempo_promedio']:.2f} min")
        print(f"Desviación estándar: {resultado['desviacion']:.2f}")
        
        print("\nPor prioridad:")
        for prioridad in range(1, 6):
            datos = resultado['por_prioridad'][prioridad]
            print(f"Prioridad {prioridad}: {datos['cantidad']} pacientes, "
                f"tiempo promedio {datos['tiempo_promedio']:.2f} min")
        print("\n" + "="*50 + "\n")
    
    # Aquí puedes agregar análisis de costos (salarios, equipos)
    analizar_costos(resultados)

def analizar_costos(resultados):
    # Costos estimados para Guatemala (investigación requerida)
    costo_enfermera = 50  # GTQ por hora
    costo_doctor = 100     # GTQ por hora
    costo_rayos_x = 200    # GTQ por hora (operación + mantenimiento)
    costo_lab = 150        # GTQ por hora
    
    print("\n=== Análisis de Costos ===")
    
    for i, res in enumerate(resultados, 1):
        config = res['configuracion']
        horas_simulacion = config.tiempo_simulacion / 60
        
        # Costos fijos
        costo_total = (
            (config.enfermeras * costo_enfermera * horas_simulacion) +
            (config.doctores * costo_doctor * horas_simulacion) +
            (config.rayos_x * costo_rayos_x * horas_simulacion) +
            (config.laboratorios * costo_lab * horas_simulacion)
        )
        
        costo_por_paciente = costo_total / res['pacientes_atendidos'] if res['pacientes_atendidos'] > 0 else 0
        
        print(f"\nConfiguración {i}:")
        print(f"Costo total: GTQ {costo_total:.2f}")
        print(f"Costo por paciente: GTQ {costo_por_paciente:.2f}")
        print(f"Eficiencia (pacientes/hora): {res['pacientes_atendidos']/horas_simulacion:.2f}")

if __name__ == '__main__':
    main()