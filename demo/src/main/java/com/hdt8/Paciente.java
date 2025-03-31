package com.hdt8;
/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas, Marcelo Detlefsen
 * Creación: 27/03/2025
 * última modificación: 31/03/2025
 * File Name: Paciente.java
 * Descripción: Fichas de los pacientes a tratar en la sala simulada con colas con prioridad implementando vector heap.
 */

public class Paciente implements Comparable<Paciente> {
    private String nombre, sintoma, emergencia;

    public Paciente(String nombre, String sintoma, String emergencia) {
        this.nombre = nombre;
        this.sintoma = sintoma;
        this.emergencia = emergencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSintoma() {
        return sintoma;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    public String getEmergencia() {
        return emergencia;
    }

    public void setEmergencia(String emergencia) {
        this.emergencia = emergencia;
    }

    @Override
    public int compareTo(Paciente paciente) {
        return this.emergencia.compareTo(paciente.getEmergencia());
    }

    @Override
    public String toString(){
        return "Paciente: " + nombre + " Sintoma: " +  sintoma + " Nivel de emergencia: " +  emergencia;
    }
}