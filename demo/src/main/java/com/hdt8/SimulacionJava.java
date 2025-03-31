package com.hdt8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class SimulacionJava {
    public static void main(String[] args) {
        PriorityQueue<Paciente> sala = new PriorityQueue<>();
        String archivoTXT = "pacientes.txt";

        System.out.println("Cargando pacientes desde archivo de texto...\n");

        // Cargar el archivo desde resources
        InputStream inputStream = SimulacionJava.class.getClassLoader().getResourceAsStream(archivoTXT);

        if (inputStream == null) {
            System.err.println("Error: No se pudo encontrar el archivo 'pacientes.txt' en resources.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 3) {
                    String nombre = datos[0].trim();
                    String sintoma = datos[1].trim();
                    String nivel = datos[2].trim().toUpperCase();
                    System.out.printf("Paciente: %s | Síntoma: %s | Nivel: %s\n", nombre, sintoma, nivel);

                } else {
                    System.err.println("Línea mal formateada: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}