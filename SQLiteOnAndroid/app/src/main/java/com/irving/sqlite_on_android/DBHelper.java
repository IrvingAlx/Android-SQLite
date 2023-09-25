package com.irving.sqlite_on_android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String BD_NAME = "newdB.db";
    private static final int DB_VERSION = 1;

    public DBHelper(@Nullable Context contexto) {
        super(contexto, BD_NAME , null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase base) {
        String comandoSql = "CREATE TABLE personita(id_personitas INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,nombre VARCHAR(100),apellidoP VARCHAR(100),apellidoM VARCHAR(100) ,fecha VARCHAR(20) , genero VARCHAR(10) )";
        base.execSQL(comandoSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase base, int oldVersion, int newVersion) {
        String comandoSql = "DROP TABLE personita";
        base.execSQL(comandoSql);
        onCreate(base);
    }
}
