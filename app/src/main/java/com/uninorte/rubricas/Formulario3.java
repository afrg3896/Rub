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
    int elemento;

    Spinner s;
    EditText nomsub , pesosub,l1,l2,l3,l4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listasub = base.select("Elementos");
        Intent it =getIntent();
        elemento=it.getIntExtra("P",0);
    }
    public void guardarsub (View v){
        nomsub = (EditText) findViewById(R.id.nombresub);
        pesosub = (EditText) findViewById(R.id.pesosub);
        l1=(EditText)findViewById(R.id.editText);
        l2=(EditText)findViewById(R.id.editText2);
        l3=(EditText)findViewById(R.id.editText3);
        l4=(EditText)findViewById(R.id.editText4);
        String categor = nomsub.getText().toString();
        final int pes=Integer.parseInt(pesosub.getText().toString());

        Subelementos.NSubElementos += pes;
        String n1=l1.getText().toString();
        String n2=l2.getText().toString();
        String n3=l3.getText().toString();
        String n4=l4.getText().toString();
        if(Subelementos.NSubElementos <=100){

            base.insertsubElementos(categor,pes,n1,n2,n3,n4,elemento);
            Toast.makeText(getApplicationContext(),"Creación exitosa del subelemento:" + categor,Toast.LENGTH_SHORT).show();
            nomsub.getText().clear();
            pesosub.getText().clear();
            l1.getText().clear();
            l2.getText().clear();
            l3.getText().clear();
            l4.getText().clear();
            if(Subelementos.NSubElementos==100){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Atención");
                builder.setMessage("No puede salir hasta que el peso sea igual a 100");

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        nomsub.getText().clear();
                        pesosub.getText().clear();
                        l1.getText().clear();
                        l2.getText().clear();
                        l3.getText().clear();
                        l4.getText().clear();
                    }
                });
                builder.show();
                Intent returnIntent= new Intent();
                setResult(Subelementos.RESULT_OK,returnIntent);
                finish();
            }
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Atención");
            builder.setMessage("Agregue un subitem con el peso correcto");

            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    Subelementos.NSubElementos -=pes;
                    pesosub.setText(String.valueOf(100-Subelementos.NSubElementos));
                    l1.getText().clear();
                    l2.getText().clear();
                    l3.getText().clear();
                    l4.getText().clear();

                }
            });
            builder.show();
        }
    }
    }

