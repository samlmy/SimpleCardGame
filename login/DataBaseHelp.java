package com.example.cardgame.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelp extends SQLiteOpenHelper {

    private static final String baseName = "CardUser";
    private static final int baseVer = 1;

    public DataBaseHelp(Context context, String name, CursorFactory factory, int version) {
        super(context, baseName, factory, baseVer);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table username ("

                + "name varchar(30) primary key," + "password  varchar(30),"

                + "score int not null)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
