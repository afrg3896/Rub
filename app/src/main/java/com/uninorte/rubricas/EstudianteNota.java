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

import java.util.ArrayList;

public class EstudianteNota extends AppCompatActivity {
    public ListView list;
    public static ArrayList lista1 = new ArrayList();
    public static ArrayList lista2 = new ArrayList();
    private DatabaseHandler base = new DatabaseHandler(this);
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_nota);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        int po=i.getIntExtra("ño",0);
        lista1 = base.selectp("Notas",po);
        lista2 = base.selectll("Notas",po);
        context = this;
        list = (ListView) findViewById(R.id.listanotaestudiante);
        list.setAdapter(new HAdapter(this, lista1, lista2,po));
    }

}
