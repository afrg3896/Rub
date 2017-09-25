package com.uninorte.rubricas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Subelementos2 extends AppCompatActivity {
    public ListView listas;
    private DatabaseHandler base = new DatabaseHandler(this);
    public static ArrayList listasub2 = new ArrayList();
    public static ArrayList listasubpes2= new ArrayList();
    public static ArrayList listal1 = new ArrayList();
    public static ArrayList listal2= new ArrayList();
    public static ArrayList listal3 = new ArrayList();
    public static ArrayList listal4= new ArrayList();
    Context context;
    int posi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subelementos2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        int po =i.getIntExtra("P",0);
        posi=po;
        int peso = i.getIntExtra("Peso",0);

        listasub2= base.selectSubElement("Subelementos",po);
        listasubpes2 = base.selectsubpeso("Subelementos",po);
        listal1 = base.selectl1("Subelementos",po);
        listal2 = base.selectl2("Subelementos",po);
        listal3 = base.selectl3("Subelementos",po);
        listal4 = base.selectl4("Subelementos",po);
        context=this;
        listas = (ListView) findViewById(R.id.listasubelement2);
        listas.setAdapter(new GAdapter(this,listasub2,listasubpes2,listal1,listal2,listal3,listal4,po,peso));

    }
    @Override
    public void onBackPressed() {
        double l=Evaluaciones.Resultado;
        String r = String.valueOf(l);
        base.insertNotasuma(r,posi);
        int t=posi;
        Evaluaciones.posd=t;
        Intent ñ = new Intent(Subelementos2.this, Evaluaciones.class);
        ñ.putExtra("nota",t);
        ñ = new Intent();
        setResult(Evaluaciones.RESULT_OK, ñ);
        finish();

    }
}
