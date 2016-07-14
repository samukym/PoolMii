package com.example.samu.poolmii.Beans;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by samu on 11/07/16.
 */
public class Trayecto extends RealmObject  {
    @PrimaryKey
    private int id;
    private String dia;
    private int hora;
    private String avenida;
    private String conductor_id;
    private boolean disponibilidad;

    public Trayecto() {
    }

    public Trayecto( String dia, int hora, String conductor_id, String avenida, boolean disponibilidad) {
        this.dia = dia;
        this.hora = hora;
        this.conductor_id = conductor_id;
        this.avenida = avenida;
        this.disponibilidad = disponibilidad;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConductor_id() {
        return conductor_id;
    }

    public void setConductor_id(String conductor_id) {
        this.conductor_id = conductor_id;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void newId(){
        id = Realm.getDefaultInstance().where(Trayecto.class).max("id").intValue() + 1;
    }
}
