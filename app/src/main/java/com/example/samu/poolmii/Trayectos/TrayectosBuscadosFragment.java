package com.example.samu.poolmii.Trayectos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.samu.poolmii.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrayectosBuscadosFragment extends Fragment {


    public TrayectosBuscadosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trayectos_buscados, container, false);
    }

}
