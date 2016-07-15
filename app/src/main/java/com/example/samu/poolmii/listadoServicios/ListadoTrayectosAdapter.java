package com.example.samu.poolmii.listadoServicios;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.samu.poolmii.Beans.TrayectoFirebase;
import com.example.samu.poolmii.Beans.Usuario;
import com.example.samu.poolmii.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


/**
 * Created by hquintana on 3/05/16.
 */

public class ListadoTrayectosAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<TrayectoFirebase> trayectos;
    private Context mContext;
    private int equipoId;
    private DatabaseReference mDatabase;

    public ListadoTrayectosAdapter(Context context, List<TrayectoFirebase> trayectos, int equipoId){
        mContext = context;
        mInflater = LayoutInflater.from(context);
        this.trayectos = trayectos;
        this.equipoId=equipoId;

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }


    @Override
    public int getCount() {
        return trayectos.size();
    }

    @Override
    public Object getItem(int i) {
        return trayectos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null){
            view = mInflater.inflate(R.layout.item_listado_servicios, null);
            viewHolder = new ViewHolder();
            viewHolder.tviCodigoAlumno = (TextView) view.findViewById(R.id.tvCodigo);
            viewHolder.tviNombreAlumno = (TextView) view.findViewById(R.id.tvNombre);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        final TrayectoFirebase trayecto = trayectos.get(i);


        mDatabase.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                for(DataSnapshot user : dataSnapshot.getChildren()){
                    Usuario usuario = user.getValue(Usuario.class);
                    Log.i("lsñkdj", user.getKey());
                    if(trayectos.get(i).getConductor_id().equals(user.getKey()+"")){
                        viewHolder.tviNombreAlumno.setText(usuario.getNombre());
                        viewHolder.tviCodigoAlumno.setText(trayectos.get(i).getAvenida());
                    }
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        ImageView imgAnadirMiembro = (ImageView) view.findViewById(R.id.imgAnadir);

        imgAnadirMiembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Implementar el añadir a la BASE DE DATOS
            }
        });


        return view;
    }

    class ViewHolder{
        TextView tviNombreAlumno;
        TextView tviCodigoAlumno;
    }
}
