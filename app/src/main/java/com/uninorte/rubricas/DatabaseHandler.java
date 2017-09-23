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
    private static final int DATABASE_VERSION = 13;
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
        db.execSQL("create table Subelementos ( id INTEGER PRIMARY KEY AUTOINCREMENT,subcategoria VARCHAR, peso INTEGER, elementid INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Estudiantes");
        db.execSQL("DROP TABLE IF EXISTS Cursos");
        db.execSQL("DROP TABLE IF EXISTS Rubricas");
        db.execSQL("DROP TABLE IF EXISTS Elementos");
        db.execSQL("DROP TABLE IF EXISTS Subelementos");
        onCreate(db);
    }

    public void insertCursos(String nombre) {
        this.database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        database.insert("Cursos", null, contentValues);
        database.close();
    }
    public void insertElementos(String nombre,int peso, int idrubrica){
        this.database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("peso", peso);
        contentValues.put("idrubrica", idrubrica);
        database.insert("Elementos", null, contentValues);
        database.close();
    }
    public void insertsubElementos(String nombre,int peso, int elementid){
        this.database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("subcategoria", nombre);
        contentValues.put("peso", peso);
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
        contentValues.put("codigo",codigo);
        contentValues.put("curso", idcurso);
        database.insert("Estudiantes", null, contentValues);
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
    public ArrayList selectc(String table ,int posicion) {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE curso = "+ posicion, null);
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
        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE curso = "+ posicion, null);
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
        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE idrubrica = "+ posicion, null);
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
        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE idrubrica = "+ posicion, null);
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
        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE elementid = "+ posicion, null);
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
        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE elementid = "+ posicion, null);
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