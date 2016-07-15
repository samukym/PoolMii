package com.example.samu.poolmii.Beans;

/**
 * Created by samu on 15/07/16.
 */
public class TrayectoFirebase {
    private String dia;
    private int hora;
    private String avenida;
    private boolean disponibilidad;
    private int precio;
    private String conductor_id;

    public TrayectoFirebase(){}

    public TrayectoFirebase(String dia, int hora, String avenida, boolean disponibilidad, int precio, String conductor_id) {
        this.dia = dia;
        this.hora = hora;
        this.avenida = avenida;
        this.disponibilidad = disponibilidad;
        this.precio = precio;
        this.conductor_id = conductor_id;
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

    public String getConductor_id() {
        return conductor_id;
    }

    public void setConductor_id(String conductor_id) {
        this.conductor_id = conductor_id;
    }

    @Override
    public String toString() {
        return "TrayectoFirebase{" +
                "dia='" + dia + '\'' +
                ", hora=" + hora +
                ", avenida='" + avenida + '\'' +
                ", disponibilidad=" + disponibilidad +
                ", precio=" + precio +
                ", conductor_id='" + conductor_id + '\'' +
                '}';
    }
}
