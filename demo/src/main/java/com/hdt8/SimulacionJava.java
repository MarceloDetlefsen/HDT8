package com.hdt8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
public class SimulacionJava {
    public static void main(String[] args) {
        PriorityQueue<Paciente> sala = new PriorityQueue<>();
        String archivoTXT = "pacientes.txt"; // Cambia por tu ruta

            System.out.println("Cargando pacientes desde archivo de texto...\n");
            
            try (BufferedReader br = new BufferedReader(new FileReader(archivoTXT))) {
                String linea;
                
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length >= 3) {
                        String nombre = datos[0].trim();
                        String sintoma = datos[1].trim();
                        String nivel = datos[2].trim().toUpperCase();
                    } else {
                        System.err.println("Línea mal formateada: " + linea);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error al leer el archivo: " + e.getMessage());
            }
    }
}
