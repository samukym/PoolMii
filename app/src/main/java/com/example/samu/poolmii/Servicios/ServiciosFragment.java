package com.example.samu.poolmii.Servicios;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class ServiciosFragment extends Fragment implements ListaDiasAdapter.onClickDiaListener {

    private List<String> mLista;
    private ListView lvDias;
    private onClickDiaFromFrListener listener;


    public ServiciosFragment() {
        mLista = new ArrayList<>();
        rellenarDias(mLista);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_servicios, container, false);

        lvDias = (ListView) rootView.findViewById(R.id.listViewDias);
        ListaDiasAdapter listaDiasAdapter = new ListaDiasAdapter(mLista, getActivity(), this);
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
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (onClickDiaFromFrListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + " debe implementar OnCharlaSelectedListener");
        }
    }



    @Override
    public void onClickDia(int pos, String dia) {
        listener.onClickDiaFromFr(2, dia);
    }


    public interface onClickDiaFromFrListener{
        void onClickDiaFromFr(int pos, String dia);
    }
}
