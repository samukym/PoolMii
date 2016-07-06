package com.example.samu.poolmii.Beans;

/**
 * Created by samu on 6/07/16.
 */
public class Usuario{
    private String nombre;
    private String dni;

    public Usuario(String nombre,  String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public Usuario(){}


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
