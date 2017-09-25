package com.uninorte.rubricas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Andres Rolong on 22/09/2017.
 */

public class GestionsAdapter extends BaseAdapter {
    ArrayList subelemento;
    ArrayList pesub;
    Context contexto;

    private static LayoutInflater inflater= null;
    public GestionsAdapter(Subelementos subelementos, ArrayList progNombreLista, ArrayList peso) {

        subelemento = progNombreLista;
        contexto = subelementos;
        pesub = peso;
        inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount(){
        return subelemento.size();
    }
    @Override
    public  Object getItem(int posicion) {
        return posicion;
    }
    @Override
    public  long  getItemId(int posicion) {
        return posicion;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        GestionsAdapter.Holder holder=new GestionsAdapter.Holder();
        View fila;
        fila = inflater.inflate(R.layout.lista_subelem, null);
        holder.t = (TextView) fila.findViewById(R.id.text8);
        holder.t1 =(TextView) fila.findViewById(R.id.text9);
        holder.t2 =(TextView) fila.findViewById(R.id.textView11);
        holder.t.setText((String) subelemento.get(position));
        holder.t1.setText("Peso: "+ pesub.get(position));
        return fila;
    }

    public class Holder
    {
        TextView t;
        TextView t1;
        TextView t2;
    }
}
