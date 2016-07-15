package com.example.samu.poolmii.ListadoTrayectos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.samu.poolmii.Beans.Trayecto;
import com.example.samu.poolmii.Beans.TrayectoFirebase;
import com.example.samu.poolmii.R;

import java.util.List;

/**
 * Created by samu on 16/05/16.
 */
public class ListadoTrayectos extends Fragment implements ListadoTrayectosFragmentInterface {

    private ListView listView;
    private ListadoTrayectosPresenter presenter;
    private int id;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_servicios, container, false);

        return rootView;
    }


    @Override
    public void setPresenter(ListadoTrayectosPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void mostrarAlumnos(List<TrayectoFirebase> trayectos) {
        listView.setAdapter(new ListadoTrayectosAdapter(this.getContext(),trayectos,id));
    }
}
