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
import android.database.Cursor;
import android.content.DialogInterface;

import com.example.cardgame.clientpage.ClientActivity;
import com.example.cardgame.R;
import com.example.cardgame.clientpage.ClientManager;

public class SignInActivity extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private static final String baseName = "CardUser";
    private static final int baseVer = 1;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Button signBtn = findViewById(R.id.login);
        editText1 = findViewById(R.id.eview1);
        editText2 = findViewById(R.id.eview2);
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkUser()) {
                    startGame();
                } else {
                    wrongMess();

                }
            }
        });
    }

    private boolean checkUser() {
        String name = editText1.getText().toString();

        //storing username
        userName = name;

        String pass = editText2.getText().toString();
        DataBaseHelp dbl = new DataBaseHelp(this, baseName, null, baseVer);
        SQLiteDatabase dataBase = dbl.getReadableDatabase();
        Cursor cursor = dataBase.query("username", new String[]{"name", "password", "score"},
                "name=?", new String[]{name},
                null, null, "password");
        if (cursor.moveToNext()) {
            String password = cursor.getString(cursor.getColumnIndex("password"));
            if (pass.equals(password)) {
                cursor.close();
                return true;
            }
        }
        return false;
    }

    private void startGame() {
        final Intent intent = new Intent(this, ClientActivity.class);
        //passing down the username to the ClientActivity
        intent.putExtra("UserName", userName);
        ClientManager client = new ClientManager();
        client.setUsername(userName);

        new AlertDialog.Builder(this).setTitle("CORRECT")
                .setMessage("LOGIN SUCCESS").
                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(intent);
                    }
                }).show();
    }

    private void wrongMess() {
        Toast.makeText(this, "THE NAME OR THE PASSWORD IS NOT CORRECT",
                Toast.LENGTH_LONG).show();
    }

}