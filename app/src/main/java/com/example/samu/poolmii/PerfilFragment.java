package com.example.samu.poolmii;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.samu.poolmii.Beans.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;

    private TextView tvNombre;
    private TextView tvDni;

    public PerfilFragment() {
        auth = FirebaseAuth.getInstance();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_perfil, container, false);
        tvNombre = (TextView) rootView.findViewById(R.id.tvNombrePerfil);
        tvDni = (TextView) rootView.findViewById(R.id.tvDniPerfil);

        setUsuario(auth.getCurrentUser().getUid());

        return rootView;
    }

    private void setUsuario(String userId){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                tvNombre.setText(usuario.getNombre());
                tvDni.append(usuario.getDni());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("ERror", "recolectando data en getUsuario perfilfragment");
            }
        });
    }

}
