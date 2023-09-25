package com.irving.basepersonita;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static String BD_NAME = "base_personita.db";
    private static int DB_VERSION = 1;

    public DBHelper(@Nullable Context contexto) {
        super(contexto, BD_NAME , null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase base) {
        String comandoSql = "CREATE TABLE personita(nombre VARCHAR(100))";
        base.execSQL(comandoSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase base, int oldVersion, int newVersion) {
        String comandoSql = "DROP TABLE personita";
        base.execSQL(comandoSql);
        onCreate(base);
    }
}
