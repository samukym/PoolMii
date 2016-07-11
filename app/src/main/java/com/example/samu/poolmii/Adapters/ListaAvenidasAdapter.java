package com.example.samu.poolmii.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.samu.poolmii.R;

import java.util.List;

/**
 * Created by samu on 8/07/16.
 */
public class ListaAvenidasAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mListaAvenidas;
    private LayoutInflater mInflater;

    public ListaAvenidasAdapter(List<String> avenidas, Context context){
        this.mContext = context;
        this.mListaAvenidas = avenidas;
        this.mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return mListaAvenidas.size();
    }

    @Override
    public Object getItem(int position) {
        return mListaAvenidas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_listadoavenidas, null);
            viewHolder = new ViewHolder();

            viewHolder.nombreAvenida = (TextView) convertView.findViewById(R.id.tvAvenida);
            viewHolder.imgBorrar = (ImageView) convertView.findViewById(R.id.imgBorrar);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.nombreAvenida.setText(mListaAvenidas.get(position));
        viewHolder.imgBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }

    private static class ViewHolder{
        TextView nombreAvenida;
        ImageView imgBorrar;
    }
}
