package com.hdt8;

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

}