package com.example.samu.poolmii.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samu.poolmii.DetallePreferencias;
import com.example.samu.poolmii.R;

import java.util.List;

/**
 * Created by samu on 8/07/16.
 */
public class ListaDiasAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mListaDias;
    private LayoutInflater mInflater;

    public ListaDiasAdapter(List<String> dias, Context context){
        this.mContext = context;
        this.mListaDias = dias;
        this.mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return mListaDias.size();
    }

    @Override
    public Object getItem(int position) {
        return mListaDias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_listadodias, null);
            viewHolder = new ViewHolder();
            viewHolder.nombreDia = (TextView) convertView.findViewById(R.id.lista_dia);
            viewHolder.toggleButton = (Switch) convertView.findViewById(R.id.lista_tooglebtn);



            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.nombreDia.setText(mListaDias.get(position));
        viewHolder.toggleButton.setChecked(true);

        viewHolder.toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewHolder.toggleButton.isChecked()){
                    Toast.makeText(v.getContext(), "ja!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewHolder.nombreDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), DetallePreferencias.class));
            }
        });



        return convertView;
    }

    private static class ViewHolder{
        TextView nombreDia;
        Switch toggleButton;
    }
}
