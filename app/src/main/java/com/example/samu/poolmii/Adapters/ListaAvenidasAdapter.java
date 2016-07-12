package com.example.samu.poolmii.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.samu.poolmii.R;

import java.util.List;

/**
 * Created by samu on 8/07/16.
 */

public class ListaAvenidasAdapter extends ArrayAdapter<String > {

    private BorrarImgListener listener;
    private List<String> avenida;
    private Context context;
    private LayoutInflater mLayoutInflater;


    public ListaAvenidasAdapter(List<String> avenida, int layoutFila, int itemMuestra, Context context, BorrarImgListener listener){
        super(context,layoutFila, itemMuestra,avenida);
        this.listener = listener;
        this.avenida = avenida;
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.item_listadoavenidas, null);
            viewHolder  = new ViewHolder();
            viewHolder.nombreAvenida = (TextView) convertView.findViewById(R.id.tvAvenida);
            viewHolder.imgBorrar = (ImageView) convertView.findViewById(R.id.imgBorrar);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.nombreAvenida.setText(avenida.get(position));
        viewHolder.imgBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onImgBorrar(position);
            }
        });
        return convertView;
    }

    private static class ViewHolder{
        TextView nombreAvenida;
        ImageView imgBorrar;
    }

    public interface BorrarImgListener{
        void onImgBorrar(int position);
    }
}

