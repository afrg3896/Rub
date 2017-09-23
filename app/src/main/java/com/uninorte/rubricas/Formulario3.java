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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Formulario3 extends AppCompatActivity {
    private DatabaseHandler base = new DatabaseHandler(this);
    public static ArrayList listasub = new ArrayList();
    int porcentaje;

    Spinner s;
    EditText nomsub , pesosub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        s = (Spinner) findViewById(R.id.elementos);
        listasub = base.select("Elementos");
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listasub);
        s.setAdapter(itemsAdapter);
    }
    public void guardarsub (View v){
        nomsub = (EditText) findViewById(R.id.nombresub);
        pesosub = (EditText) findViewById(R.id.pesosub);
        int j = s.getSelectedItemPosition() +1;
        String categor = nomsub.getText().toString();
        int pes = Integer.parseInt(pesosub.getText().toString());
        base.insertsubElementos(categor,pes,j);
        Toast.makeText(getApplicationContext(),"Creación exitosa del Subelemento: " + categor,Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Salir");
        builder.setMessage("¿Desea salir de la creación de Subelementos?");

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Intent returnIntent = new Intent();
                setResult(Estudiantes.RESULT_OK, returnIntent);
                finish();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                nomsub.getText().clear();
                pesosub.getText().clear();
            }
        });

        builder.show();
    }

    }

