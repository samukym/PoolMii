package com.example.samu.poolmii.listadoServicios;


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
        //Implementar OBTENER DE BD
    }
}
