package com.example.samu.poolmii;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.samu.poolmii.Adapters.ListaDiasAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BusquedasFragment extends Fragment {
    private List<String> mLista;
    private ListView lvDias;


    public BusquedasFragment() {
        mLista = new ArrayList<>();
        rellenarDias(mLista);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_busquedas, container, false);

        lvDias = (ListView) rootView.findViewById(R.id.listViewDias);
        ListaDiasAdapter listaDiasAdapter = new ListaDiasAdapter(mLista, getActivity());
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

}
