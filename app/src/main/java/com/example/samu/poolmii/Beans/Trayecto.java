package com.example.samu.poolmii.Beans;

import java.util.List;

/**
 * Created by samu on 8/07/16.
 */
public class Trayecto {
   // Calendar fecha;
    List<String> avenidas;

    public Trayecto(List<String> avenidas) {
    //    this.fecha = fecha;
        this.avenidas = avenidas;
    }

    public List<String> getAvenidas() {
        return avenidas;
    }

    public void setAvenidas(List<String> avenidas) {
        this.avenidas = avenidas;
    }


}
