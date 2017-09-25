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
    int rubrica;
    EditText categoria, peso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listaR = base.select("Rubricas");

        Intent it = getIntent();
        rubrica = it.getIntExtra("P", 0);
    }


    public void guardarest(View v) {
        categoria = (EditText) findViewById(R.id.nombre);
        peso = (EditText) findViewById(R.id.codigo);
        String categor = categoria.getText().toString();
        final int pes = Integer.parseInt(peso.getText().toString());
        Elementos.NElementos += pes;
        if (Elementos.NElementos <= 100) {
            base.insertElementos(categor, pes, rubrica);
            Toast.makeText(getApplicationContext(), "Creación exitosa del elemento:" + categor, Toast.LENGTH_SHORT).show();
            categoria.getText().clear();
            peso.getText().clear();

            if (Elementos.NElementos == 100) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Salir");
                builder.setMessage("Ahora puede salir");

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        categoria.getText().clear();
                        peso.getText().clear();
                        Intent returnIntent = new Intent();
                        setResult(Elementos.RESULT_OK, returnIntent);
                        finish();
                    }
                });
                builder.show();
            }
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Atención");
            builder.setMessage("Agregue un item con el peso correcto");
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    Elementos.NElementos -= pes;
                    peso.setText(String.valueOf(100 - Elementos.NElementos));
                }
            });
            builder.show();
        }
    }
}

