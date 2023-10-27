package com.backend.model;

public class Odontologo {

    private int numeroDEmatricula;
    private String nombre;
    private String apellido;

    public Odontologo(int numeroDEmatricula, String nombre, String apellido) {
        this.numeroDEmatricula = numeroDEmatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getNumeroDEmatricula() {
        return numeroDEmatricula;
    }

    public void setNumeroDEmatricula(int numeroDEmatricula) {
        this.numeroDEmatricula = numeroDEmatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
