package com.uninorte.rubricas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Andres Rolong on 13/09/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private SQLiteDatabase database;
    private static final int DATABASE_VERSION = 20;
    public static final String DATABASE_NAME = "Rubric.db";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Estudiantes ( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR , codigo INTEGER, curso INTEGER);");
        db.execSQL("create table Cursos ( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR);");
        db.execSQL("create table Rubricas ( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR);");
        db.execSQL("create table Elementos ( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR,peso INTEGER, idrubrica INTEGER);");
        db.execSQL("create table Subelementos ( id INTEGER PRIMARY KEY AUTOINCREMENT,subcategoria VARCHAR, peso INTEGER,l1 VARCHAR,l2 VARCHAR,l3 VARCHAR,l4 VATCHAR, elementid INTEGER);");
        db.execSQL("create table Evaluacion( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR);");
        db.execSQL("create table Evaluaciones ( id INTEGER PRIMARY KEY AUTOINCREMENT, idevaluacion INTEGER, idrubrica INTEGER, idcurso Integer,idalumno INTEGER);");
        db.execSQL("create table SubNotas ( id INTEGER PRIMARY KEY AUTOINCREMENT, nota VARCHAR,l VARCHAR,elementid INTEGER);");
        db.execSQL("create table NotaSum ( id INTEGER PRIMARY KEY AUTOINCREMENT, total VARCHAR,elementid INTEGER);");
        db.execSQL("create table Notas ( id INTEGER PRIMARY KEY AUTOINCREMENT, idalumno INTEGER, peso INTEGER,evalid INTEGER, nombre VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Estudiantes");
        db.execSQL("DROP TABLE IF EXISTS Cursos");
        db.execSQL("DROP TABLE IF EXISTS Rubricas");
        db.execSQL("DROP TABLE IF EXISTS Elementos");
        db.execSQL("DROP TABLE IF EXISTS Subelementos");
        db.execSQL("DROP TABLE IF EXISTS Evaluacion");
        db.execSQL("DROP TABLE IF EXISTS Evaluaciones");
        db.execSQL("DROP TABLE IF EXISTS SubNotas");
        db.execSQL("DROP TABLE IF EXISTS NotaSum");
        db.execSQL("DROP TABLE IF EXISTS Notas");
        onCreate(db);
    }

    public void insertCursos(String nombre) {
        this.database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        database.insert("Cursos", null, contentValues);
        database.close();
    }

    public void insertEvaluacion(String nombre) {
        this.database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        database.insert("Evaluacion", null, contentValues);
        database.close();
    }

    public void insertEvaluaciones(int eval, int rubri, int curs, int alumn) {
        this.database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idevaluacion", eval);
        contentValues.put("idrubrica", rubri);
        contentValues.put("idcurso", curs);
        contentValues.put("idalumno", alumn);
        database.insert("Evaluaciones", null, contentValues);
        database.close();
    }

    public void insertElementos(String nombre, int peso, int idrubrica) {
        this.database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("peso", peso);
        contentValues.put("idrubrica", idrubrica);
        database.insert("Elementos", null, contentValues);
        database.close();
    }

    public void insertsubElementos(String nombre, int peso, String l1, String l2, String l3, String l4, int elementid) {
        this.database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("subcategoria", nombre);
        contentValues.put("peso", peso);
        contentValues.put("l1", l1);
        contentValues.put("l2", l2);
        contentValues.put("l3", l3);
        contentValues.put("l4", l4);
        contentValues.put("elementid", elementid);
        database.insert("Subelementos", null, contentValues);
        database.close();
    }

    public void insertR(String nombre) {
        this.database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        database.insert("Rubricas", null, contentValues);
        database.close();
    }

    public void insertEstudiante(String nombre, int codigo, int idcurso) {
        this.database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("codigo", codigo);
        contentValues.put("curso", idcurso);
        database.insert("Estudiantes", null, contentValues);
        database.close();
    }

    public void insertSubNotas(String nota, String campo, int elementid) {
        this.database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nota", nota);
        contentValues.put("l", campo);
        contentValues.put("elementid", elementid);
        database.insert("SubNotas", null, contentValues);
        database.close();
    }

    public void insertNotasuma(String nota, int elementid) {
        this.database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("total", nota);
        contentValues.put("elementid", elementid);
        database.insert("NotaSum", null, contentValues);
        database.close();
    }

    public void insertNotas(int nota, int evalid, int alumtid, String nm) {
        this.database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idalumno", nota);
        contentValues.put("peso", evalid);
        contentValues.put("evalid", alumtid);
        contentValues.put("nombre", nm);

        database.insert("Notas", null, contentValues);
        database.close();
    }


    public ArrayList select(String table) {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + table, null);
        ArrayList array = new ArrayList();
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                array.add(i, cursor.getString(1));
            }
        }
        cursor.close();
        database.close();
        return array;
    }

    public ArrayList selectc(String table, int posicion) {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE curso = " + posicion, null);
        ArrayList array = new ArrayList();
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                array.add(i, cursor.getString(2));
            }
        }
        cursor.close();
        database.close();
        return array;
    }

    public ArrayList s(String table, int posicion) {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE curso = " + posicion, null);
        ArrayList array = new ArrayList();
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                array.add(i, cursor.getString(1));
            }
        }
        cursor.close();
        database.close();
        return array;
    }

    public ArrayList selectElement(String table, int posicion) {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE idrubrica = " + posicion, null);
        ArrayList array = new ArrayList();
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                array.add(i, cursor.getString(1));
            }
        }
        cursor.close();
        database.close();
        return array;
    }

    public ArrayList selectpeso(String table, int posicion) {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE idrubrica = " + posicion, null);
        ArrayList array = new ArrayList();
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                array.add(i, cursor.getString(2));
            }
        }
        cursor.close();
        database.close();
        return array;
    }

    public ArrayList selectSubElement(String table, int posicion) {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE elementid = " + posicion, null);
        ArrayList array = new ArrayList();
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                array.add(i, cursor.getString(1));
            }
        }
        cursor.close();
        database.close();
        return array;
    }

    public ArrayList selectsubpeso(String table, int posicion) {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE elementid = " + posicion, null);
        ArrayList array = new ArrayList();
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                array.add(i, cursor.getString(2));
            }
        }
        cursor.close();
        database.close();
        return array;
    }

    public ArrayList selectl1(String table, int posicion) {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE elementid = " + posicion, null);
        ArrayList array = new ArrayList();
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                array.add(i, cursor.getString(3));
            }
        }
        cursor.close();
        database.close();
        return array;
    }

    public ArrayList selectl2(String table, int posicion) {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE elementid = " + posicion, null);
        ArrayList array = new ArrayList();
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                array.add(i, cursor.getString(4));
            }
        }
        cursor.close();
        database.close();
        return array;
    }

    public ArrayList selectl3(String table, int posicion) {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE elementid = " + posicion, null);
        ArrayList array = new ArrayList();
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                array.add(i, cursor.getString(5));
            }
        }
        cursor.close();
        database.close();
        return array;
    }

    public ArrayList selectl4(String table, int posicion) {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE elementid = " + posicion, null);
        ArrayList array = new ArrayList();
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                array.add(i, cursor.getString(6));
            }
        }
        cursor.close();
        database.close();
        return array;
    }

    public double nota(String table, int posicion) {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE elementid = " + posicion, null);
        double nota = 0.0;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                double nt = Double.parseDouble(cursor.getString(1));
                nota = nota + nt;
            }
        }
        cursor.close();
        database.close();
        return nota;
    }

    public ArrayList selectp(String table, int posicion) {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE idalumno = " + posicion, null);
        ArrayList array = new ArrayList();
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                array.add(i, cursor.getString(4));
            }
        }
        cursor.close();
        database.close();
        return array;


    }

    public ArrayList selectll(String table, int posicion) {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE idalumno = " + posicion, null);
        ArrayList array = new ArrayList();
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                array.add(i, cursor.getString(2));
            }
        }
        cursor.close();
        database.close();
        return array;

    }
}


