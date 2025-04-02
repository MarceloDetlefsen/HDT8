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


# Investigación
Esta simulación se basa en datos disponibles sobre el sistema de salud de Guatemala para intentar reflejar condiciones realistas en una sala de emergencias. Debido a la limitada disponibilidad de datos específicos para todos los parámetros, se utilizaron las mejores estimaciones disponibles de diversas fuentes.

**Tiempo de Triage:** Se encontró una guía del Instituto Guatemalteco de Seguridad Social (IGSS) que establece que el proceso de clasificación de pacientes (triage) no debería tomar más de 5 minutos. La simulación original utilizaba un rango de 8 a 12 minutos, por lo que este hallazgo sugiere que podría ajustarse para ser más realista.   

**Salarios del Personal de Salud:** Se investigaron los salarios promedio de enfermeras y médicos en Guatemala. Para enfermeras, se encontraron reportes de salarios mensuales promedio que oscilan entre Q3,000 y Q8,000, con un promedio alrededor de Q4,040 . Para médicos, los salarios varían significativamente según la especialidad y el sector (público o privado), con reportes que van desde Q7,301 por cuatro horas en el sector público hasta cifras mucho más altas en el sector privado . Los costos por hora utilizados en la simulación (Enfermera: Q50/hora, Doctor: Q100/hora) se consideran estimaciones razonables dentro de este rango, especialmente para un entorno de emergencia.

**Capacidad Hospitalaria:** Datos del Banco Mundial y otras fuentes indican que Guatemala tiene una de las densidades de camas de hospital más bajas de la región, con menos de una cama por cada 1,000 habitantes . En 2016, se reportó un promedio de 34 camas por hospital . Esta información contextualiza la capacidad limitada del sistema de salud.

**Otros Parámetros:** Para otros parámetros como la tasa de llegada de pacientes, el porcentaje que requiere exámenes y el tiempo de los exámenes, no se encontraron datos específicos para Guatemala en las fuentes consultadas. En estos casos, la simulación utiliza valores estimados o rangos basados en la lógica del proceso de atención de emergencias.

# Referencias
   - https://gt.computrabajo.com/salarios/medicosas
   - https://gt.computrabajo.com/salarios/enfermera
   - https://gt.computrabajo.com/salarios/medico-general
   - https://www.trade.gov/market-intelligence/guatemala-healthcare-infrastructure
   - https://www.homehospital.com.gt/post/cu%C3%A1nto-gana-una-enfermera-en-guatemala

# Autores
👨‍💻 Marcelo Detlefsen
👨‍💻 Julián Divas