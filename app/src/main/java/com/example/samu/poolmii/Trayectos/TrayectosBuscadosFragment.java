package com.example.samu.poolmii.Trayectos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.samu.poolmii.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrayectosBuscadosFragment extends Fragment {

    private DatabaseReference mDatabase;


    public TrayectosBuscadosFragment() {
        mDatabase = FirebaseDatabase.getInstance().getReference("servicios");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_trayectos_buscados, container, false);
    }

}
