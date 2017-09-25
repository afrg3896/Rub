package com.uninorte.rubricas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Rubrica extends AppCompatActivity {
    public ListView lista2;
    public static ArrayList listaRubrica= new ArrayList();
    private DatabaseHandler base = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubrica);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoShow();
            }
        });
        listaRubrica = base.select("Rubricas");
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaRubrica);
        lista2 = (ListView) findViewById(R.id.listaRubric);
        lista2.setAdapter(itemsAdapter);
        lista2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             int h = position + 1;
             Intent i = new Intent(Rubrica.this, Elementos.class);
             i.putExtra("Pot", h);
             startActivity(i);

         }
        });
    }


    public void dialogoShow(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(this);
        alert.setMessage("Ingrese nombre de la Rúbrica");
        alert.setTitle("Creación de la Rúbrica");

        alert.setView(edittext);

        alert.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String text = edittext.getText().toString();
                base.insertR(text);
                Intent refresh = new Intent(Rubrica.this,Rubrica.class);
                startActivity(refresh);
                Rubrica.this.finish();

            }
        });

        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        alert.show();
    }
}
