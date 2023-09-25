package com.irving.sqlite_on_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataManager {
    private Context miContexto;
    private final SQLiteOpenHelper dbHelper;
    private SQLiteDatabase miBase;

    public DataManager(Context miContexto){
        this.miContexto = miContexto;
        dbHelper = new DBHelper(miContexto);
        miBase = dbHelper.getWritableDatabase();
    }

    void abrir(){
        miBase = dbHelper.getWritableDatabase();
    }

    void cerrar(){
        miBase.close();
    }

    void borrar(){
        dbHelper.onUpgrade(miBase,1,1);
    }

    void guardarPersonita( Personita fulanito){
        ContentValues valores = new ContentValues();

        valores.put("nombre",fulanito.getNombre());
        valores.put("apellidoP",fulanito.getApellidoP());
        valores.put("apellidoM",fulanito.getApellidoM());
        valores.put("genero",fulanito.getGenero());
        valores.put("fecha",fulanito.getFecha());

        miBase.insert("personita",null,valores);
    }

    ArrayList<Personita> leerPersonitas(){
        ArrayList<Personita> personitas = new ArrayList<>();
        String[] columnas = {"nombre","apellidoP","apellidoM","genero","fecha"};

        Cursor miCursor = miBase.query("personita",columnas,
                null,null,null,null,
                null);

        while (miCursor.moveToNext()){
            Personita fulanito = new Personita();

            fulanito.setNombre(miCursor.getString(0));
            fulanito.setApellidoP(miCursor.getString(1));
            fulanito.setApellidoM(miCursor.getString(2));
            fulanito.setGenero(miCursor.getString(3));
            fulanito.setFecha(miCursor.getString(4));

            personitas.add(fulanito);
        }
        miCursor.close();
        return personitas;
    }
}

