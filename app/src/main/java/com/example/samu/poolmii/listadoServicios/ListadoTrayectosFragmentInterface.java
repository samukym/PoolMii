package com.example.samu.poolmii.listadoServicios;

import com.example.samu.poolmii.Beans.TrayectoFirebase;

import java.util.List;

/**
 * Created by samu on 16/05/16.
 */
public interface ListadoTrayectosFragmentInterface {
    public void setPresenter(ListadoTrayectosPresenter presenter);
    public void mostrarAlumnos(List<TrayectoFirebase> trayectos);
}
