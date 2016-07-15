package com.example.samu.poolmii.ListadoTrayectos;


import com.example.samu.poolmii.Beans.Trayecto;
import com.example.samu.poolmii.Beans.TrayectoFirebase;

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

        List<TrayectoFirebase> li  = null;

        TrayectoFirebase t = new TrayectoFirebase("Sunday",1800,"siempre viva",true, 4,"Bitch please");
        li.add(t);
        laView.mostrarAlumnos(li);
    }
}
