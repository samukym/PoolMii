package com.example.samu.poolmii.Preferencias;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.samu.poolmii.Adapters.ListaDiasAdapter;
import com.example.samu.poolmii.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BusquedasFragment extends Fragment implements ListaDiasAdapter.onClickDiaListener {
    private List<String> mLista;
    private ListView lvDias;
    private ListaDiasAdapter listaDiasAdapter;


    public BusquedasFragment() {
        mLista = new ArrayList<>();
        rellenarDias(mLista);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_busquedas, container, false);
        listaDiasAdapter = new ListaDiasAdapter(mLista, getActivity(),this);
        lvDias = (ListView) rootView.findViewById(R.id.listViewDias);

        lvDias.setAdapter(listaDiasAdapter);




        return rootView;
    }

    private void rellenarDias(List<String> lista) {
        lista.add("lunes");
        lista.add("martes");
        lista.add("miercoles");
        lista.add("jueves");
        lista.add("viernes");
        lista.add("sabado");
    }

    @Override
    public void onClickDia(int pos) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        BusquedaTrayectoFragment busquedaTrayectoFragment = new BusquedaTrayectoFragment();
        transaction.replace(R.id.fragment_container, busquedaTrayectoFragment);
        transaction.addToBackStack(null).commit();
    }
}
