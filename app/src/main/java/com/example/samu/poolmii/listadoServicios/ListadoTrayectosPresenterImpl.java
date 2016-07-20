package com.example.samu.poolmii.listadoServicios;


import android.util.Log;

import com.example.samu.poolmii.Beans.Trayecto;
import com.example.samu.poolmii.Beans.TrayectoFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by samu on 16/05/16.
 */
public class ListadoTrayectosPresenterImpl implements ListadoTrayectosPresenter {
    private ListadoTrayectosFragmentInterface laView;
    private DatabaseReference mDatabase;
    private Realm realm;


    public ListadoTrayectosPresenterImpl(ListadoTrayectosFragmentInterface laView){
        this.laView = laView;
        mDatabase = FirebaseDatabase.getInstance().getReference("servicios");
        realm = Realm.getDefaultInstance();

    }
    @Override
    public void obtenerTrayectos() {
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                List<TrayectoFirebase> trayectos = new ArrayList<>();
                for (DataSnapshot servicio : dataSnapshot.getChildren()) {
                    TrayectoFirebase t = servicio.getValue(TrayectoFirebase.class);
                    for(Trayecto trayecto : findAllTrayectosDia()){
                        Log.i("hora: "+trayecto.getHora(), t.getHora()+"" );
                        if(t.isDisponibilidad() && t.getAvenida().equals(trayecto.getAvenida())
                                && (t.getHora() == trayecto.getHora())){
                            Log.i("MATCHMATCH", "  TRUEEE ");
                            trayectos.add(new TrayectoFirebase(t.getDia(), t.getHora(), t.getAvenida(), t.isDisponibilidad(), 24,t.getConductor_id()));
                        }
                    }
                }
                laView.mostrarAlumnos(trayectos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private RealmResults<Trayecto> findAllTrayectosDia() {
        RealmResults<Trayecto> resultadoTrayectos = realm.where(Trayecto.class)
                .findAll();
        return resultadoTrayectos;

    }
}
