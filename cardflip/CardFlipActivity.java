package com.example.cardgame.cardflip;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardgame.R;

import java.util.ArrayList;
import java.util.List;

public class CardFlipActivity extends AppCompatActivity {
    ImageView curView = null;
    int currentPos = -1;
    private Button buyCoin;
    List<Integer> record = new ArrayList<>();
    int first;
    TextView flipScore;
    TextView coinLeft;
    CardFlipManager CardFlip;

    String userName;
    int score;
    int coins;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_play);

        coinLeft = findViewById(R.id.coin);
        flipScore = findViewById(R.id.flipLable);
        CardFlip = new CardFlipManager();
        final int[] pos = CardFlip.getPos();
        final int[] drawble = CardFlip.getImage();

        userName = getIntent().getStringExtra("UserName");
        coins = getIntent().getIntExtra("Coin", 0);
        score = getIntent().getIntExtra("Score", 0);

        CardFlip.addCoin(coins);
        coinLeft.setText("Coins:" + CardFlip.getCoin());

        buyCoin = findViewById(R.id.restartButton);
        buyCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardFlip.buyFlip();
                update();
            }
        });

        GridView gridView = (GridView) findViewById(R.id.gridView);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int life = CardFlip.getLife();
                if (!record.contains(position) & life > 0) {
                    if (currentPos < 0) {
                        currentPos = position;
                        curView = (ImageView) view;
                        ((ImageView) view).setImageResource(drawble[pos[position]]);
                        first = position;
                    } else {
                        if (first != position) {
                            CardFlip.damage();
                            update();
                            if (pos[currentPos] != pos[position]) {
                                curView.setImageResource(R.drawable.hidden);
                                Toast.makeText(getApplicationContext(), "Not match",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                CardFlip.useCard();
                                CardFlip.addScore();
                                record.add(position);
                                record.add(currentPos);
                                ((ImageView) view).setImageResource(drawble[pos[position]]);
                                Toast.makeText(getApplicationContext(), "Nice",
                                        Toast.LENGTH_SHORT).show();
                            }
                            currentPos = -1;
                        }
                    }
                }
                if (CardFlip.getLife() <= 0 | CardFlip.getCardLeft() == 0) {
                    restartGame();
                }
            }
        });
    }

    //End the game
    public void restartGame() {
        Intent cardFlipIntent = new Intent(this, GameOver.class);

        cardFlipIntent.putExtra("UserName", userName);
        cardFlipIntent.putExtra("Coin", CardFlip.getCoin());
        cardFlipIntent.putExtra("Score", score);

        startActivity(cardFlipIntent);
    }

    //update the TextView.
    public void update() {
        String flip = "Flip:" + CardFlip.getLife();
        flipScore.setText(flip);
        String c = "Coin:" + CardFlip.getCoin();
        coinLeft.setText(c);
    }

}
