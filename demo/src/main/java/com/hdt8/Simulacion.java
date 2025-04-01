package com.hdt8;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Marcelo Detlefsen
 * Creación: 27/03/2025
 * última modificación: 31/03/2025
 * File Name: SimulacionJavaVectorHeap.java
 * Descripción: Simulación de una sala de emergencias con cola de prioridad implementando VectorHeap
 */

public class Simulacion {
    public static void main(String[] args) {
        String archivoTXT = "pacientes.txt";
        Scanner scanner = new Scanner(System.in);
        boolean revision = true;
        String implementacion = null;
        int opcion;
        while (revision){
            System.out.println("Ingrese el numero de la implementacion que desea utilizar");
            System.out.println("1. Vector Heap");
            System.out.println("2. Priority Queue JCF");
            opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion == 1){
                implementacion = "Vector Heap";
                revision = false;
            }
            else if (opcion == 2){
                implementacion = "PriorityQueue";
                revision = false;
            }
            else {
                System.out.println("Ingrese una opcion valida");
            }
        }
        scanner.close();

        PriorityQueue<Paciente> sala = PriorityQueueFactory.getPriorityQueue(implementacion);
        InputStream inputStream = Simulacion.class.getClassLoader().getResourceAsStream(archivoTXT);

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
                    sala.add(new Paciente(nombre, sintoma, nivel));
                    System.out.printf("Paciente: %s | Síntoma: %s | Nivel: %s\n", nombre, sintoma, nivel);

                } else {
                    System.err.println("Línea mal formateada: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        while (sala.isEmpty() == false){
            System.out.println("Paciente tratado: ");
            System.out.println(sala.remove().toString());
        }
    }
}