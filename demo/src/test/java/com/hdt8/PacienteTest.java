package com.hdt8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class PacienteTest {

    @Test
    void testConstructorAndGetters() {
        Paciente paciente = new Paciente("Juan Perez", "Fractura", "C");
        
        assertEquals("Juan Perez", paciente.getNombre());
        assertEquals("Fractura", paciente.getSintoma());
        assertEquals("C", paciente.getEmergencia());
    }

    @Test
    void testSetters() {
        Paciente paciente = new Paciente("", "", "");
        
        paciente.setNombre("Maria Ramirez");
        paciente.setSintoma("Apendicitis");
        paciente.setEmergencia("A");
        
        assertEquals("Maria Ramirez", paciente.getNombre());
        assertEquals("Apendicitis", paciente.getSintoma());
        assertEquals("A", paciente.getEmergencia());
    }

    @Test
    void testCompareTo() {
        Paciente p1 = new Paciente("A", "S1", "A"); // Prioridad más alta
        Paciente p2 = new Paciente("B", "S2", "C");
        Paciente p3 = new Paciente("C", "S3", "E"); // Prioridad más baja
        
        assertTrue(p1.compareTo(p2) < 0);
        assertTrue(p2.compareTo(p3) < 0);
        assertTrue(p1.compareTo(p3) < 0);
        assertEquals(0, p1.compareTo(new Paciente("D", "S4", "A")));
    }

    @Test
    void testToString() {
        Paciente paciente = new Paciente("Lorenzo", "Fiebre", "D");
        String expected = "Paciente: Lorenzo Sintoma: Fiebre Nivel de emergencia: D";
        
        assertEquals(expected, paciente.toString());
    }
}