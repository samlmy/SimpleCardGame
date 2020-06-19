package com.example.cardgame.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.app.AlertDialog;

import com.example.cardgame.gamedata.MainActivity;
import com.example.cardgame.R;


public class RegisterActivity extends AppCompatActivity {

    private static final String baseName = "CardUser";
    private static final int baseVer = 1;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button regBtn = findViewById(R.id.register);
        editText1 = findViewById(R.id.editview1);
        editText2 = findViewById(R.id.editview2);
        editText3 = findViewById(R.id.editview3);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishReg();
            }
        });
    }

    public void finishReg() {
        String name = editText1.getText().toString();
        String pass = editText2.getText().toString();
        String pas2 = editText3.getText().toString();
        int score = 0;
        if (name.equals("") || pass.equals("")) {
            new AlertDialog.Builder(this).setTitle("WRONG").
                    setMessage("EMPTY PASSWORD OR USERNAME").
                    setPositiveButton("OK", null).show();
        } else if(!pass.equals(pas2)) {
            new AlertDialog.Builder(this).setTitle("WRONG").
                    setMessage("DIFFERENT PASSWORDS").
                    setPositiveButton("OK", null).show();
        }else{
            DataBaseHelp dlite = new DataBaseHelp(this, baseName, null, baseVer);
            SQLiteDatabase dataBase = dlite.getWritableDatabase();
            String sql = "insert into username (name,password,score) values(?,?,?)";
            dataBase.execSQL(sql, new Object[]{name, pass, score});
            Toast.makeText(this, "WELCOME TO THE GAME", Toast.LENGTH_LONG).show();
            Intent backIntent = new Intent(this, MainActivity.class);
            startActivity(backIntent);
        }
    }
}
