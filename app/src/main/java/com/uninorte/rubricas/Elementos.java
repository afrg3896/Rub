package com.uninorte.rubricas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Elementos extends AppCompatActivity {
    public ListView lista2;
    public static ArrayList listaElementos= new ArrayList();
    public static ArrayList listapeso= new ArrayList();
    private DatabaseHandler base = new DatabaseHandler(this);
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoShow();
            }
        });
        Intent i = getIntent();
        int po =i.getIntExtra("Po",0);
        listaElementos = base.selectElement("Elementos",po);
        listapeso = base.selectpeso("Elementos",po);
        context=this;
        lista2 = (ListView) findViewById(R.id.listelemento);
        lista2.setAdapter(new GestionAdapter(this,listaElementos,listapeso));
        lista2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int h = position + 1;
                Intent i = new Intent(Elementos.this,Subelementos.class);
                i.putExtra("P", h);
                startActivity(i);

            }
        });
    }


    public void dialogoShow() {
        Intent i = new Intent(this, Formulario2.class);
        startActivityForResult(i,1);
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
