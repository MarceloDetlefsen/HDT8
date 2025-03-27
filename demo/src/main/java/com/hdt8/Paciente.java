package com.hdt8;

public class Paciente {
    private String nombre, sintoma;
    private char emergencia;

    public Paciente(String nombre, String sintoma, char emergencia) {
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

    public char getEmergencia() {
        return emergencia;
    }

    public void setEmergencia(char emergencia) {
        this.emergencia = emergencia;
    }

}