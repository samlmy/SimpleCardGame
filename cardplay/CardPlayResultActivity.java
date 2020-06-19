package com.example.cardgame.cardplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.cardgame.cardflip.CardViewActivity;
import com.example.cardgame.clientpage.ClientActivity;
import com.example.cardgame.R;

public class CardPlayResultActivity extends AppCompatActivity {


    String userName;
    int score;
    int coins;
    int condition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button nextGame;
        Button restart;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_second_game_result);


        TextView nameLabel = findViewById(R.id.nameLabel);
        TextView coinLabel = findViewById(R.id.coinLabel);
        TextView scoreLabel = findViewById(R.id.scoreLabel);
        TextView secondScoreLabel = findViewById(R.id.secondscoreLable);

        userName = getIntent().getStringExtra("UserName");
        coins = getIntent().getIntExtra("Coin", 0);
        score = getIntent().getIntExtra("Score", 0);
        condition = getIntent().getIntExtra("Condition", 0);

        nameLabel.setText("Name : " + userName);
        coinLabel.setText("Remaining Coins : " + coins);
        scoreLabel.setText("Total Score : " + score);

        if (condition == 0) {
            secondScoreLabel.setText("Your CardPlay Score is : " + 100000);
        } else {
            secondScoreLabel.setText("Your CardPlay Score is : " + 0);
        }

        nextGame = findViewById(R.id.nextGameButon);
        nextGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCardFlip();
            }
        });

        restart = findViewById(R.id.retryButton);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });

        ///adjusting the size of the frame
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.8), (int) (height * .5));
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.x = 0;
        lp.y = -20;

        getWindow().setAttributes(lp);

    }

    public void startCardFlip() {
        Intent cardPlayIntent = new Intent(this, CardViewActivity.class);

        cardPlayIntent.putExtra("UserName", userName);
        cardPlayIntent.putExtra("Coin", coins);
        cardPlayIntent.putExtra("Score", score);

        startActivity(cardPlayIntent);

    }

    public void restartGame() {
        Intent intent = new Intent(this, ClientActivity.class);

        intent.putExtra("CurrentCoin", coins);
        intent.putExtra("CurrentScore", score);
        intent.putExtra("CurrentState", "CardPlay");

        startActivity(intent);
    }


}
