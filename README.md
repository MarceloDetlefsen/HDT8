# HDT8 - Estructuras de Datos
Repositorio: https://github.com/MarceloDetlefsen/HDT8.git

# Descripción del Programa
Implementación de un sistema de atención de pacientes en emergencias hospitalarias utilizando Priority Queues, que incluye:

1. **Parte Java**:
   - Clase `Paciente` con prioridades A-E
   - Implementación de `VectorHeap` personalizado
   - Versión usando `PriorityQueue` de JCF
   - Simulación completa del flujo de atención

2. **Parte Python**:
   - Simulación DES con SimPy del sistema de emergencias
   - Modelado de recursos limitados (doctores, equipos)
   - Análisis estadístico de tiempos de atención
   - Recomendaciones de configuración óptima

# 🛠️ Instalación y Ejecución
1. Clonar el repositorio:
    ```bash
    git clone https://github.com/MarceloDetlefsen/HDT8.git
    cd HDT8
    ```

2. Compilar el programa:
    ```bash 
    cd demo
    javac -d out src/main/java/com/hdt8/*.java
    ```

3. Ejecutar la parte 1:
    ```bash
    cd out
    java com.hdt8.SimulacionJava
    ```

4. Ejecutar la parte 2:
    ```bash
    cd out
    python SimuladorHospital.py
    ```

# Análisis de los Resultados Simpy
...

# Referencias
...

# Autores
👨‍💻 Marcelo Detlefsen
👨‍💻 Julián Divas