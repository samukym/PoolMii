package com.example.samu.poolmii.Preferencias;


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
public class BusquedasFragment extends Fragment implements ListaDiasAdapter.onClickDiaListener{

    private ListView lvDias;
    private ArrayList<String> mLista;
    private ListaDiasAdapter listaDiasAdapter;
    private onClickDiaFromFrListener listener;

    public BusquedasFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_busquedas, container, false);

        mLista = new ArrayList<>();
        rellenarDias(mLista);
        lvDias = (ListView)rootView.findViewById(R.id.listViewDiasB);
        listaDiasAdapter = new ListaDiasAdapter(mLista, getActivity(), this);
        lvDias.setAdapter(listaDiasAdapter)
        ;
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
        listener.onClickDiaFromFr(1, dia);
    }
    public interface onClickDiaFromFrListener{
        void onClickDiaFromFr(int frag, String dia);
    }
}
