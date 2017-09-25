package com.uninorte.rubricas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Evaluaciones extends AppCompatActivity {
    public ListView lista2;
    public static ArrayList listaElemento = new ArrayList();
    public static ArrayList listapes = new ArrayList();
    public static ArrayList Sumas = new ArrayList();
    public static int lugar=0;
    private DatabaseHandler base = new DatabaseHandler(this);
    Context context;
    static double Resultado=0.0;
    static int posd=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluaciones);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);;
        Intent i = getIntent();
        int po = i.getIntExtra("PR", 0);
        int alumno = i.getIntExtra("AL", 0);

        listaElemento = base.selectElement("Elementos", po);
        listapes = base.selectpeso("Elementos", po);
        context = this;
        lista2 = (ListView) findViewById(R.id.listaeval);
        lista2.setAdapter(new GestAdapter(this, listaElemento, listapes,po,alumno));
        lista2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int h = position + 1;
               String pes = (String) listapes.get(position);
                int p = Integer.parseInt(pes);
                Intent i = new Intent(Evaluaciones.this, Subelementos2.class);
                i.putExtra("P", h);
                i.putExtra("Peso",p);
                i.putExtra("ev",Resultado);
                startActivityForResult(i,1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1) {
            if (resultCode == RESULT_OK) {
                finish();
                startActivity(getIntent());
            }
        }
    }
}
