package com.uninorte.rubricas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Andres Rolong on 21/09/2017.
 */

public class GestionAdapter extends BaseAdapter {
    ArrayList elemento;
    ArrayList pes;
    Context contexto;

    private static LayoutInflater inflater= null;
    public GestionAdapter (Elementos elementos, ArrayList progNombreLista, ArrayList peso) {

       elemento = progNombreLista;
        contexto = elementos;
        pes = peso;
        inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount(){
        return elemento.size();
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
        GestionAdapter.Holder holder=new GestionAdapter.Holder();
        View fila;
        fila = inflater.inflate(R.layout.list_elem, null);
        holder.tv=(TextView) fila.findViewById(R.id.text6);
        holder.tv1 =(TextView) fila.findViewById(R.id.text7);
        holder.tv.setText((String) elemento.get(position));
        holder.tv1.setText("Peso: "+ pes.get(position));
        return fila;
    }

    public class Holder
    {
        TextView tv;
        TextView tv1;
    }
}
