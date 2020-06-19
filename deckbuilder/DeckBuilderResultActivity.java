package com.example.cardgame.deckbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.cardgame.cardplay.CardPlayActivity;
import com.example.cardgame.clientpage.BackUp;
import com.example.cardgame.gamedata.Card;
import com.example.cardgame.clientpage.ClientActivity;
import com.example.cardgame.R;

import java.util.ArrayList;

public class DeckBuilderResultActivity extends AppCompatActivity {

    ArrayList<Card> deck = new ArrayList<>();
    int coins;
    int score;
    String userName;
    BackUp backupgame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button nextGame;
        Button restart;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_first_game_result);


        TextView nameLabel = findViewById(R.id.nameLabel);
        TextView coinLabel = findViewById(R.id.coinLabel);
        TextView scoreLabel = findViewById(R.id.scoreLabel);
        TextView deckbuilderScoreLabel = findViewById(R.id.firstscoreLable);

        userName = getIntent().getStringExtra("UserName");
        coins = getIntent().getIntExtra("Coin", 0);
        score = getIntent().getIntExtra("Score", 0);
        deck = getIntent().getParcelableArrayListExtra("Deck");

        nameLabel.setText("Name : " + userName);
        coinLabel.setText("Remaining Coins : " + coins);
        scoreLabel.setText("Total Score : " + score);
        deckbuilderScoreLabel.setText("Your DeckBuilder score is : " + score);

        nextGame = findViewById(R.id.nextGameButon);
        nextGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCardPlay();
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

    public void startCardPlay() {

        Intent cardPlayIntent = new Intent(this, CardPlayActivity.class);

        cardPlayIntent.putExtra("UserName", userName);
        cardPlayIntent.putExtra("Coin", coins);
        cardPlayIntent.putExtra("Score", score);
        cardPlayIntent.putParcelableArrayListExtra("Deck", deck);

        startActivity(cardPlayIntent);

    }

    public void restartGame() {
        Intent intent = new Intent(this, ClientActivity.class);

        intent.putExtra("CurrentCoin", coins);
        intent.putExtra("CurrentScore", score);
        intent.putParcelableArrayListExtra("CurrentDeck", deck);
        intent.putExtra("CurrentState", "DeckBuilder");

        startActivity(intent);
    }


}
