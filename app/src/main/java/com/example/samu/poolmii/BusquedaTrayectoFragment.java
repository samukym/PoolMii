package com.example.samu.poolmii;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.samu.poolmii.Adapters.ListaAvenidasAdapter;
import com.example.samu.poolmii.Beans.Trayecto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class BusquedaTrayectoFragment extends Fragment implements View.OnClickListener, ListaAvenidasAdapter.BorrarImgListener {

    private List<String> mLista;
    private TextView tvReloj;
    private Trayecto trayecto;
    private Realm realm;


    private OnBusquedaListener mListener;

    private ListView lvAvenidas;
    private FloatingActionButton fabAnadirAv;
    private ListaAvenidasAdapter mAdapter;

    public BusquedaTrayectoFragment() {
        mLista = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //BD
        realm = Realm.getDefaultInstance();

        //varibles que mantienen la db
        trayecto = new Trayecto();
        String dia = this.getArguments().getString("arg0", "lunes");
        trayecto.setDia(dia);
        trayecto.setHora(0);

        //inflar array
        List<Trayecto> misTrayectos = findAllTrayectosDia();
        for (Trayecto t : misTrayectos) {
            mLista.add(t.getAvenida());
        }

        //views
        View rootView = inflater.inflate(R.layout.fragment_busqueda_trayecto, container, false);
        fabAnadirAv = (FloatingActionButton) rootView.findViewById(R.id.fabAnadirAv);
        lvAvenidas = (ListView) rootView.findViewById(R.id.lvAvenidas);
        initAdapter();
        fabAnadirAv.setOnClickListener(this);
        tvReloj = (TextView) rootView.findViewById(R.id.tvReloj);
        tvReloj.setOnClickListener(this);

        return rootView;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBusquedaListener) {
            mListener = (OnBusquedaListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvReloj:
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
                                trayecto.setHora(hourOfDay);
                            }
                        }, mHour, mMinute, false);
                tpd.show();
                break;
            case R.id.fabAnadirAv:
                showInputDialog();
                break;
        }
    }

    @Override
    public void onImgBorrar(int pos) {
        mAdapter.remove(mLista.get(pos));
    }


    public interface OnBusquedaListener {
        void onBusquedaInteraction(Trayecto trayecto);
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
                        trayecto.setAvenida(etDialog.getText().toString());
                        realm.executeTransactionAsync(new Realm.Transaction() {

                            @Override
                            public void execute(Realm realm) {
                                trayecto.newId();
                                realm.copyToRealm(trayecto);
                                Log.i("dia", trayecto.getDia());
                            }
                        }, new Realm.Transaction.OnSuccess() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(getContext(), "exito en isnertar trayecto", Toast.LENGTH_LONG).show();
                            }
                        }, new Realm.Transaction.OnError() {
                            @Override
                            public void onError(Throwable error) {
                                Toast.makeText(getContext(), "fallo en insertar trayecto "+error, Toast.LENGTH_LONG).show();
                            }
                        });
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
        RealmResults<Trayecto> resultadoTrayectos = realm.where(Trayecto.class)
                .equalTo("dia", trayecto.getDia())
                .findAll();
        return resultadoTrayectos;
    }

}
