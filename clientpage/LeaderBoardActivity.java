package com.example.cardgame.clientpage;

import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.ArrayList;

import android.widget.ArrayAdapter;


public class LeaderBoardActivity extends AppCompatActivity {

    private DataBaseHelper dbh;
    List<Leader> leaders;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = new ListView(this);
        dbh = new DataBaseHelper(this, "LeaderBoard", null, 1);
        leaders = new ArrayList<Leader>();
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, findData()));
        setContentView(listView);
    }


    public List<String> findData() {
        SQLiteDatabase db = dbh.getWritableDatabase();
        Cursor cursor = db.query("highscore", null, null, null, null, null, "score desc");
        List<String> leadersInfo = new ArrayList<String>();
        while (cursor.moveToNext()) {

            String name = cursor.getString(cursor.getColumnIndex("name"));
            int score = cursor.getInt(cursor.getColumnIndex("score"));

            Leader leader = new Leader(name, score);
            leaders.add(leader);
            leadersInfo.add(leader.makeString());
        }
        cursor.close();
        return leadersInfo;
    }
}

