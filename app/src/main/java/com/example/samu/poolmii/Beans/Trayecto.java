package com.example.samu.poolmii.Beans;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by samu on 11/07/16.
 */
public class Trayecto extends RealmObject {
    @PrimaryKey
    private int id;
    private String dia;
    private int hora;
    private String avenida;

    public Trayecto() {
    }

    public Trayecto(String dia, int hora, String avenida, int id) {
        this.dia = dia;
        this.hora = hora;
        this.avenida = avenida;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}