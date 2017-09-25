package com.uninorte.rubricas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class Formulario4 extends AppCompatActivity {
    private DatabaseHandler base = new DatabaseHandler(this);
    public static ArrayList listaRub = new ArrayList();
    public static ArrayList listaCur = new ArrayList();
    public static ArrayList listaAlum = new ArrayList();
    int p;

    Spinner rubric,curso,alumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rubric = (Spinner) findViewById(R.id.spinnerrubric);
        curso= (Spinner) findViewById(R.id.spinnercurso);
        alumno = (Spinner) findViewById(R.id.spinneralumno);
        Intent i = getIntent();
        int po =i.getIntExtra("Pos",0);
        p=po;
        listaRub=base.select("Rubricas");
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listaRub);
        rubric.setAdapter(itemsAdapter);
        listaCur=base.select("Cursos");
        ArrayAdapter<String> itemsAdapter2 =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listaCur);
        curso.setAdapter(itemsAdapter2);
        listaAlum=base.select("Estudiantes");
        ArrayAdapter<String> itemsAdapter3 =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listaAlum);
        alumno.setAdapter(itemsAdapter3);
    }
    public void guardareval(View v){
        int r = rubric.getSelectedItemPosition() +1;
        int c = curso.getSelectedItemPosition() +1;
        int a = alumno.getSelectedItemPosition() +1;
        base.insertEvaluaciones(p,r,c,a);
        int h = p;
        int al=a;
        Intent i = new Intent(Formulario4.this, Evaluaciones.class);
        i.putExtra("PR",h);
        i.putExtra("AL",al);

        startActivity(i);
    }

}
