package com.irving.basepersonita;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;

import java.util.ArrayList;

public class DataManager {
    private Context miContexto;
    private SQLiteOpenHelper dbHelper;
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
        ContentValues valores = new ContentValues(1);

        valores.put("nombre",fulanito.getNombre());

        miBase.insert("personita",null,valores);
    }

    ArrayList<Personita> leerPersonitas(){
        ArrayList<Personita> personitas = new ArrayList<>();
        String[] columnas = {"nombre"};

        Cursor miCursor = miBase.query("personita",columnas,
                                    null,null,null,null,
                                    "nombre");

        while (miCursor.moveToNext()){
            Personita fulanito = new Personita();

            fulanito.setNombre(miCursor.getString(0));

            personitas.add(fulanito);
        }
        miCursor.close();
        return personitas;
    }
}
