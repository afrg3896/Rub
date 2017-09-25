package com.uninorte.rubricas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.uninorte.rubricas.Evaluaciones.Sumas;

/**
 * Created by Andres Rolong on 23/09/2017.
 */

public class GestAdapter extends BaseAdapter {
    ArrayList elemento;
    ArrayList pes;
    Context contexto;
    int p;
    int alumno;

    private static LayoutInflater inflater= null;
    public GestAdapter (Evaluaciones evaluaciones, ArrayList progNombreLista, ArrayList peso, int pevaluacion,int alm) {
        elemento = progNombreLista;
        contexto = evaluaciones;
        pes = peso;
        p=pevaluacion;
        alumno=alm;
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
        final GestAdapter.Holder holder=new GestAdapter.Holder();
        View fila;
        fila = inflater.inflate(R.layout.lista_eval, null);
        holder.tv=(TextView) fila.findViewById(R.id.text11);
        holder.tv2=(TextView) fila.findViewById(R.id.textView11);
        holder.tv1 =(TextView) fila.findViewById(R.id.text12);
        holder.bnt = (Button) fila.findViewById(R.id.bguarda);
        holder.tv.setText((String) elemento.get(position));
        holder.tv1.setText("Peso: "+ pes.get(position));
        final int kl=Integer.parseInt((String) pes.get(position));
        final String nmobre= (String) holder.tv.getText();
        holder.bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.base.insertNotas(alumno,kl,p,nmobre);
                holder.bnt.setVisibility(View.GONE);
            }
        });
        return fila;
    }

    public class Holder
    {
        TextView tv;
        TextView tv1;
        TextView tv2;
        Button bnt;
        public DatabaseHandler base = new DatabaseHandler(contexto);
    }
}
