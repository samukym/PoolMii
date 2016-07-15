package com.example.samu.poolmii.Beans;

/**
 * Created by samu on 15/07/16.
 */
public class TrayectoFirebase {
    private int id;
    private String dia;
    private int hora;
    private String avenida;
    private boolean disponibilidad;
    private int precio;
    private int conductor_id;

    public TrayectoFirebase(){}

    public TrayectoFirebase(int id, String dia, int hora, String avenida, boolean disponibilidad, int precio, int conductor_id) {
        this.id = id;
        this.dia = dia;
        this.hora = hora;
        this.avenida = avenida;
        this.disponibilidad = disponibilidad;
        this.precio = precio;
        this.conductor_id = conductor_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public String getAvenida() {
        return avenida;
    }

    public void setAvenida(String avenida) {
        this.avenida = avenida;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getConductor_id() {
        return conductor_id;
    }

    public void setConductor_id(int conductor_id) {
        this.conductor_id = conductor_id;
    }
}
