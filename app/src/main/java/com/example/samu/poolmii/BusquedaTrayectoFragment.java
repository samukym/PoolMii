package com.example.samu.poolmii;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.samu.poolmii.Beans.Trayecto;

import java.util.ArrayList;
import java.util.List;


public class BusquedaTrayectoFragment extends Fragment {

    private List<String> mLista;

    private OnBusquedaListener mListener;

    private ListView lvAvenidas;
    private FloatingActionButton fabAnadirAv;
    private ArrayAdapter<String> mAdapter;

    public BusquedaTrayectoFragment() {
        mLista = new ArrayList<>();
        mLista.add("asdfsdf");
        mLista.add("wafas");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_busqueda_trayecto, container, false);
        fabAnadirAv = (FloatingActionButton) rootView.findViewById(R.id.fabAnadirAv);
        lvAvenidas = (ListView) rootView.findViewById(R.id.lvAvenidas);
        fabAnadirAv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });

        actulizarUI();

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


    public interface OnBusquedaListener {
        void onBusquedaInteraction(Trayecto trayecto);
    }

    protected void showInputDialog() {

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
                        actulizarUI();
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

    private void actulizarUI(){
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(getActivity(),
                    R.layout.item_listadoavenidas,
                    R.id.tvAvenida,
                    mLista);

            lvAvenidas.setAdapter(mAdapter);

        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
