package com.uninorte.rubricas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GestionarAdapter extends BaseAdapter {
    ArrayList resultado;
    ArrayList codigo;
    int imgId;
    Context contexto;

    private static LayoutInflater inflater= null;
    public GestionarAdapter (Estudiantes estudiantes, ArrayList progNombreLista, int progImg, ArrayList codig) {

        resultado = progNombreLista;
        contexto = estudiantes;
        imgId = progImg;
        codigo = codig;
        inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount(){
        return resultado.size();
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
        Holder holder=new Holder();
        View fila;
        fila = inflater.inflate(R.layout.lista_est, null);
        holder.tv=(TextView) fila.findViewById(R.id.tv);
        holder.img=(ImageView) fila.findViewById(R.id.imgEstu);
        holder.tv1 =(TextView) fila.findViewById(R.id.tv1);
        holder.tv.setText((String) resultado.get(position));
        holder.img.setImageResource(imgId);
        holder.tv1.setText("Código: "+ codigo.get(position));
        return fila;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
        TextView tv1;
    }

}

