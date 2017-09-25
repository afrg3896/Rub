package com.uninorte.rubricas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Andres Rolong on 25/09/2017.
 */

public class HAdapter extends BaseAdapter {
    ArrayList elemento;
    ArrayList pes;
    Context contexto;
    int pos;

    private static LayoutInflater inflater= null;
    public HAdapter (EstudianteNota estudianteNota, ArrayList progNombreLista, ArrayList peso,int posic) {
        elemento = progNombreLista;
        contexto = estudianteNota;
        pes = peso;
        pos=posic;
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
        final HAdapter.Holder holder=new HAdapter.Holder();
        View fila;
        fila = inflater.inflate(R.layout.lista_notaestu, null);
        holder.tv=(TextView) fila.findViewById(R.id.textView12);
        holder.tv1 =(TextView) fila.findViewById(R.id.textView13);
        holder.tv.setText((String) elemento.get(pos));
        double mo= holder.base.nota("NotaSum",pos+1);
        double k = Double.parseDouble(String.valueOf(pes.get(pos)));
        double bv = k/100;
        double gf =mo*bv;
        String j= String.valueOf(gf);
        holder.tv1.setText(j);

        return fila;
    }

    public class Holder
    {
        TextView tv;
        TextView tv1;
        public DatabaseHandler base = new DatabaseHandler(contexto);
    }
}
