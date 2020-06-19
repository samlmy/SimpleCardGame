package com.example.cardgame.gamedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cardgame.login.RegisterActivity;
import com.example.cardgame.login.SignInActivity;
import com.example.cardgame.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = findViewById(R.id.StartButton);
        Button RegBtn = findViewById(R.id.LoginButton);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSign();
            }
        });
        RegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReg();
            }
        });
    }

    public void startSign() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    public void startReg() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}
