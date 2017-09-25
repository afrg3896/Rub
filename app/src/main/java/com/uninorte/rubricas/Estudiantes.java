package com.uninorte.rubricas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class Estudiantes extends AppCompatActivity {
    public ListView list;
    public static ArrayList listaEstudiante = new ArrayList();
    public static ArrayList listacodigo = new ArrayList();
    private DatabaseHandler base = new DatabaseHandler(this);
    public static int pimagen=R.drawable.persona;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiantes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onResume();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dialogo();
            }
        });
        Intent i = getIntent();
        int po=i.getIntExtra("Pos",0);
        listaEstudiante = base.s("Estudiantes",po);
        listacodigo = base.selectc("Estudiantes",po);
        context =this;
        list = (ListView) findViewById(R.id.listaestudiante);
        list.setAdapter(new GestionarAdapter(this,listaEstudiante,pimagen,listacodigo));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int h = position + 1;
                Intent i = new Intent(Estudiantes.this,EstudianteNota.class);
                i.putExtra("ño", h);
                startActivity(i);
            }
        });

    }

    public void dialogo(){
        Intent i = new Intent(this, Formulario.class);
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
