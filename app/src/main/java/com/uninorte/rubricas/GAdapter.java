package com.uninorte.rubricas;

import android.content.Context;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Andres Rolong on 23/09/2017.
 */

public class GAdapter extends BaseAdapter {
    int opcion;
    double nt1,nt2,nt3,nt4;
    ArrayList elemento;
    ArrayList pes;
    ArrayList l1;
    ArrayList l2;
    ArrayList l3;
    ArrayList l4;
    Context contexto;
    int pos;
    int pesonota;

    private static LayoutInflater inflater= null;
    public GAdapter (Subelementos2 subelementos2, ArrayList progNombreLista, ArrayList peso, ArrayList c1,ArrayList c2,ArrayList c3,ArrayList c4,int pot,int pesonot) {
        elemento = progNombreLista;
        contexto = subelementos2;
        pes = peso;
        l1=c1;
        l2=c2;
        l3=c3;
        l4=c4;
        pos=pot;
        pesonota=pesonot;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final GAdapter.Holder holder = new GAdapter.Holder();
        final View fila;
        fila = inflater.inflate(R.layout.lista_sub2, null);
        holder.tv = (TextView) fila.findViewById(R.id.tsub2name);
        holder.tv1 = (TextView) fila.findViewById(R.id.Nota);
        holder.tn= (TextView) fila.findViewById(R.id.textNot);
        holder.guar =(Button) fila.findViewById(R.id.guardar);
        holder.rb = (RadioGroup) fila.findViewById(R.id.radiogroup);
        holder.b1 = (RadioButton) fila.findViewById(R.id.radioButton);
        holder.b2 = (RadioButton) fila.findViewById(R.id.radioButton2);
        holder.b3 = (RadioButton) fila.findViewById(R.id.radioButton3);
        holder.b4 = (RadioButton) fila.findViewById(R.id.radioButton4);
        holder.tv.setText((String) elemento.get(position));
        holder.tv1.setText("Peso: " + pes.get(position)+"%");
        holder.b1.setText((String) l1.get(position));
        holder.b2.setText((String) l2.get(position));
        holder.b3.setText((String) l3.get(position));
        holder.b4.setText((String) l4.get(position));
        holder.Nota=0;
        holder.checked=0;
        holder.rb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {


                holder.checked=holder.rb.indexOfChild(fila.findViewById(checkedId));
                switch (holder.checked) {
                    case 0:
                        double p1=1.25;
                        String s = (String) pes.get(position);
                        double porc = Double.parseDouble(s);
                        double porcf = (porc/100);
                        nt1=p1*porcf;
                        String ca1 = String.valueOf(nt1);
                        holder.tn.setText(ca1);
                        holder.c = (String) holder.b1.getText();
                        opcion=1;

                        break;
                    case 1:
                        double p2=2.5;
                        String se = (String) pes.get(position);
                        double porce = Double.parseDouble(se);
                        double porcfi = (porce/100);
                         nt2=p2*porcfi;
                        String ca2 = String.valueOf(nt2);
                        holder.tn.setText(ca2);
                        holder.c = (String) holder.b2.getText();
                        opcion=2;
                        break;
                    case 2:
                        double p3=3.75;
                        String sel = (String) pes.get(position);
                        double porcen = Double.parseDouble(sel);
                        double porcfin = (porcen/100);
                         nt3=p3*porcfin;
                        String ca3 = String.valueOf(nt3);
                        holder.tn.setText(ca3);
                        holder.c = (String) holder.b3.getText();
                        opcion=3;
                        break;
                    case 3:
                        double p4=5.0;
                        String sele = (String) pes.get(position);
                        double porcent = Double.parseDouble(sele);
                        double porcfina = (porcent/100);
                         nt4=p4*porcfina;
                        String ca4 = String.valueOf(nt4);
                        holder.tn.setText(ca4);
                        holder.c = (String) holder.b4.getText();
                        opcion=4;
                        break;
                    default:
                        break;
                }
            }
        });
        holder.guar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opcion==1)
                {
                    Evaluaciones.Resultado=Evaluaciones.Resultado+nt1;
                }
                if(opcion==2)
                {
                    Evaluaciones.Resultado=Evaluaciones.Resultado+nt2;
                }
                if(opcion==3)
                {
                    Evaluaciones.Resultado=Evaluaciones.Resultado+nt3;
                }
                if(opcion==4)
                {
                    Evaluaciones.Resultado=Evaluaciones.Resultado+nt4;
                }
                String d = (String) holder.tn.getText();
                holder.base.insertSubNotas(d,holder.c,pos);
                holder.guar.setVisibility(View.GONE);
            }
        });
        return fila;
    }

    public class Holder {
        TextView tv;
        TextView tv1;
        TextView tn;
        RadioGroup rb;
        RadioButton b1,b2,b3,b4;
        int checked;
        int NotaF;
        String c;
        double Nota;
        Button guar;
        public DatabaseHandler base = new DatabaseHandler(contexto);
    }

}
