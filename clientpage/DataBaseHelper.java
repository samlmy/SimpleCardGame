package com.example.cardgame.clientpage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String baseName = "LeaderBoard";
    private static final int baseVer = 1;

    public DataBaseHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, baseName, factory, baseVer);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table highscore ("

                + "name varchar(30) primary key," + "score int not null)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}