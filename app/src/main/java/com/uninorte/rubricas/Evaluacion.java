package com.uninorte.rubricas;

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

import java.util.ArrayList;

public class Evaluacion extends AppCompatActivity {
    public ListView lista;
    public static ArrayList listaEvaluacion = new ArrayList();
    private DatabaseHandler base = new DatabaseHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoShow();
            }
        });
        listaEvaluacion = base.select("Evaluacion");
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaEvaluacion);
        lista = (ListView) findViewById(R.id.listaevaluacion);
        lista.setAdapter(itemsAdapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int h = position +1;
                Intent i = new Intent(Evaluacion.this, Formulario4.class);
                i.putExtra("Pos",h);
                startActivity(i);
            }
        });
    }

    public void dialogoShow(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(this);
        alert.setMessage("Ingrese nombre de la Evaluación");
        alert.setTitle("Creación de la Evaluación");

        alert.setView(edittext);

        alert.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String text = edittext.getText().toString();
                base.insertEvaluacion(text);
                Intent refresh = new Intent(Evaluacion.this,Evaluacion.class);
                startActivity(refresh);
                Evaluacion.this.finish();
            }
        });

        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        alert.show();
    }
}
