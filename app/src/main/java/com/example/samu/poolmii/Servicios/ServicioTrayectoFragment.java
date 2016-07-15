package com.example.samu.poolmii.Servicios;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.samu.poolmii.Adapters.ListaAvenidasAdapter;
import com.example.samu.poolmii.Beans.Trayecto;
import com.example.samu.poolmii.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class ServicioTrayectoFragment extends Fragment implements View.OnClickListener, ListaAvenidasAdapter.BorrarImgListener {

    private List<String> mLista;
    private TextView tvReloj;
    private Integer hora;
    private String dia;
    private Realm realm;
    private List<String> avenidasNuevas;
    private List<Trayecto> misTrayectos;
    private int idCounter;

    //VIES
    private ListView lvAvenidas;
    private FloatingActionButton fabAnadirAv;
    private ListaAvenidasAdapter mAdapter;
    private Button btnAnadirAv;

    public ServicioTrayectoFragment() {
        mLista = new ArrayList<>();
        avenidasNuevas = new ArrayList<>();
        idCounter = 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //BD
        //realm = Realm.getDefaultInstance();
        //recupearmos el dia del fragment anterior
        dia ="lunes";
        Log.i("arg0", dia);

        misTrayectos = findAllTrayectosDia();
        //seteamos la hora de anteriores veces
        if(misTrayectos != null) {
            for (Trayecto t : misTrayectos) {
                mLista.add(t.getAvenida());
            }
            hora = misTrayectos.get(0).getHora();
        }
        else{
            hora = 0;
        }

        //views
        View rootView = inflater.inflate(R.layout.fragment_servicio_trayecto, container, false);
        fabAnadirAv = (FloatingActionButton) rootView.findViewById(R.id.fabGuardarT_S);
        lvAvenidas = (ListView) rootView.findViewById(R.id.lvAvenidasS);
        initAdapter();
        fabAnadirAv.setOnClickListener(this);
        btnAnadirAv = (Button) rootView.findViewById(R.id.btnAnadirAvS);
        btnAnadirAv.setOnClickListener(this);
        tvReloj = (TextView) rootView.findViewById(R.id.tvRelojS);
        tvReloj.setOnClickListener(this);
        tvReloj.setText(hora+":"+"00");

        return rootView;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvRelojS:
                // Process to get Current Time
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog tpd = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // Display Selected time in textbox
                                tvReloj.setText(new String(hourOfDay + ":" + "00"));
                                hora = hourOfDay;
                            }
                        }, mHour, mMinute, false);
                tpd.show();
                break;
            case R.id.fabGuardarT_S:
                guardarTrayecto();
                break;
            case R.id.btnAnadirAvS:
                showInputDialog();
        }
    }

    @Override
    public void onImgBorrar(int pos) {

        int posAvNuevas = (pos) - (mLista.size() - avenidasNuevas.size());
        if(posAvNuevas >= 0){
            avenidasNuevas.remove(posAvNuevas);
        }else{
           // realm.beginTransaction();
           // misTrayectos.get(pos).deleteFromRealm();
           // realm.commitTransaction();
        }
        mAdapter.remove(mLista.get(pos));
    }


    private void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(promptView);

        final EditText etDialog = (EditText) promptView.findViewById(R.id.etDialog);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mAdapter.add(etDialog.getText().toString());
                        avenidasNuevas.add(etDialog.getText().toString());
                        idCounter++;
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }


    private void initAdapter() {
        mAdapter = new ListaAvenidasAdapter(mLista, R.layout.item_listadoavenidas, R.id.tvAvenida, getContext(), this);
        lvAvenidas.setAdapter(mAdapter);
    }

    private RealmResults<Trayecto> findAllTrayectosDia() {
        /*RealmResults<Trayecto> resultadoTrayectos = realm.where(Trayecto.class)
                .equalTo("dia", dia)
                .findAll();
        return (resultadoTrayectos.size() > 0) ? resultadoTrayectos : null ;*/
        return null;
    }
    private void guardarTrayecto(){
        final List<Trayecto> trayectos = new ArrayList<>();
        //RealmResults results = Realm.getDefaultInstance().where(Trayecto.class).findAll();
        //autoID
       // idCounter = (results.size() > 0) ? Realm.getDefaultInstance().where(Trayecto.class).max("id").intValue()+ idCounter : 0;

        for(String avenida : avenidasNuevas){
            Trayecto t = new Trayecto(dia, hora, avenida, idCounter);
            trayectos.add(t);
            idCounter++;
        }
       /* realm.executeTransactionAsync(new Realm.Transaction() {

            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(trayectos);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i("INSERCION: ","SUCCESS");
                idCounter = 1;
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(getContext(), "No se pudieron guardar los cambios", Toast.LENGTH_LONG).show();
                Log.e("copyToRealm: ", ""+error);
            }
        });*/
    }

}
