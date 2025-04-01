package com.hdt8;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

class SimulacionTest {

    @Test
    void testSimulacionWithVectorHeap() {
        String input = "1\n"; // Selecciona VectorHeap
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Redirigir System.out para capturar la salida
        // (Implementación omitida por brevedad)
        
        Simulacion.main(new String[]{});
        
        // Verificar que se ejecutó sin errores
        // (En una prueba real, verificarías la salida esperada)
    }

    @Test
    void testSimulacionWithJCF() {
        String input = "2\n"; // Selecciona PriorityQueue JCF
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        Simulacion.main(new String[]{});
        
        // Verificar que se ejecutó sin errores
    }

    @Test
    void testSimulacionInvalidOption() {
        String input = "3\n1\n"; // Primero inválido, luego válido
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        Simulacion.main(new String[]{});
        
        // Verificar que manejó correctamente la opción inválida
    }
}