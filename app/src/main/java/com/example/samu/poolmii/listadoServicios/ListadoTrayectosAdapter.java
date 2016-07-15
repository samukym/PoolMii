package com.example.samu.poolmii.listadoServicios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.samu.poolmii.Beans.Trayecto;
import com.example.samu.poolmii.R;

import java.util.List;


/**
 * Created by hquintana on 3/05/16.
 */

public class ListadoTrayectosAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Trayecto> trayectos;
    private Context mContext;
    private int equipoId;

    public ListadoTrayectosAdapter(Context context, List<Trayecto> trayectos, int equipoId){
        mContext = context;
        mInflater = LayoutInflater.from(context);
        trayectos = trayectos;
        this.equipoId=equipoId;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            view = mInflater.inflate(R.layout.item_listado_servicios, null);
            viewHolder = new ViewHolder();
            viewHolder.tviCodigoAlumno = (TextView) view.findViewById(R.id.tvCodigo);
            viewHolder.tviNombreAlumno = (TextView) view.findViewById(R.id.tvNombre);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        final Trayecto trayecto = trayectos.get(i);

        viewHolder.tviCodigoAlumno.setText(trayecto.getDia());

        ImageView imgAnadirMiembro = (ImageView) view.findViewById(R.id.imgAnadir);

        imgAnadirMiembro.setOnClickListener(new View.OnClickListener() {
            Trayecto alu=trayecto;
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
