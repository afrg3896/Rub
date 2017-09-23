package com.uninorte.rubricas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class Subelementos extends AppCompatActivity {
    public ListView listas;
    private DatabaseHandler base = new DatabaseHandler(this);
    public static ArrayList listasub = new ArrayList();
    public static ArrayList listasubpes= new ArrayList();
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subelementos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo();
            }
        });
        Intent i = getIntent();
        int po =i.getIntExtra("P",0);
        listasub= base.selectSubElement("Subelementos",po);
        listasubpes = base.selectsubpeso("Subelementos",po);
        context=this;
        listas = (ListView) findViewById(R.id.listasubelement);
        listas.setAdapter(new GestionsAdapter(this,listasub,listasubpes));
    }

    public void dialogo(){
        Intent i = new Intent(this, Formulario3.class);
        startActivityForResult(i, 1);
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
