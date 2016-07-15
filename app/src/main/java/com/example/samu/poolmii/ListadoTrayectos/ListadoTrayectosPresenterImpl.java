package com.example.samu.poolmii.ListadoTrayectos;


import com.example.samu.poolmii.Beans.Trayecto;

import java.util.List;

/**
 * Created by samu on 16/05/16.
 */
public class ListadoTrayectosPresenterImpl implements ListadoTrayectosPresenter {
    private ListadoTrayectosFragmentInterface laView;

    public ListadoTrayectosPresenterImpl(ListadoTrayectosFragmentInterface laView){
        this.laView = laView;
    }
    @Override
    public void obtenerTrayectos() {

        List<Trayecto> li  = null;

        Trayecto t = new Trayecto("Sunday",1800,"Bitch please", 666);
        li.add(t);
        laView.mostrarAlumnos(li);
    }
}
