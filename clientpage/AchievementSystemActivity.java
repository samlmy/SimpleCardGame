package com.example.cardgame.clientpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cardgame.R;

public class AchievementSystemActivity extends AppCompatActivity {

    TextView achievement1;
    TextView achievement2;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement_system);

        achievement1 = findViewById(R.id.achievement1);
        achievement2 = findViewById(R.id.achievement2);

        int coin = getIntent().getIntExtra("HighestGameCoin", 0);
        int score = getIntent().getIntExtra("HighestGameScore", 0);

        achievement1.setText(AchievementManager.highScoreAchievementChecker(score));
        achievement2.setText(AchievementManager.coinAchievementChecker(coin));


        back = findViewById(R.id.backPageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPage();
            }
        });


    }

    private void backPage() {
        Intent intent = new Intent(this, ClientActivity.class);
        startActivity(intent);
    }

}
