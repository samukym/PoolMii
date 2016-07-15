package com.example.samu.poolmii.listadoServicios;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
    private ListadoTrayectosAdapter mADapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trayectos, container, false);
        Log.i("qui", "asñdlkfjañsdkjf");
        listView = (ListView) rootView.findViewById(R.id.listViewDiasT);

        setPresenter(new ListadoTrayectosPresenterImpl(this));
        presenter.obtenerTrayectos();

        return rootView;
    }


    @Override
    public void setPresenter(ListadoTrayectosPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void mostrarAlumnos(List<TrayectoFirebase> trayectos) {
        listView.setAdapter(new ListadoTrayectosAdapter(getContext(),trayectos,id));
    }
}
