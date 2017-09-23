package com.uninorte.rubricas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Formulario2 extends AppCompatActivity {

    private DatabaseHandler base = new DatabaseHandler(this);
    public static ArrayList listaR = new ArrayList();
    int porcentaje=0;

    Spinner s;
    EditText categoria , peso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        s = (Spinner) findViewById(R.id.cursos);
        listaR = base.select("Rubricas");
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listaR);
        s.setAdapter(itemsAdapter);
    }


    public void guardarest(View v){
        categoria = (EditText) findViewById(R.id.nombre);
        peso = (EditText) findViewById(R.id.codigo);
        int j = s.getSelectedItemPosition() +1;
        String categor = categoria.getText().toString();
        int pes = Integer.parseInt(peso.getText().toString());
        base.insertElementos(categor,pes,j);
        Toast.makeText(getApplicationContext(),"Creación exitosa del elemento: "+categor,Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Salir");
        builder.setMessage("¿Desea salir de la creación de Elementos?");

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Intent returnIntent = new Intent();
                setResult(Estudiantes.RESULT_OK, returnIntent);
                finish();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                categoria.getText().clear();
                peso.getText().clear();
            }
        });

        builder.show();
    }


}
