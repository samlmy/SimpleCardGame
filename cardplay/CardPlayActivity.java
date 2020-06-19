package com.example.cardgame.cardplay;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardgame.gamedata.Card;
import com.example.cardgame.gamedata.CardAdapter;
import com.example.cardgame.R;

import java.util.ArrayList;

public class CardPlayActivity extends AppCompatActivity {

    private ImageView bossImageView;
    private ImageView clawImageView;
    private TextView enemyHP; //Simplified HP display (improve by displaying a hp bar)
    private TextView playerHP;
    private Button playBtn;
    private Button nextBtn;
    private Button restartBtn;
    private Button endTurnButton;
    private CardAdapter cardAdapter;
    private Spinner myHand;
    ArrayList<Card> deck = new ArrayList<>();
    private CardPlayManager cardPlay;

    private int CardSelectedIndex;
    private long bossAniDuration = 1000;
    private String userName;
    private int score;
    private int coins;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_play);

        //initializing all variables
        deck = getIntent().getParcelableArrayListExtra("Deck");
        userName = getIntent().getStringExtra("UserName");
        coins = getIntent().getIntExtra("Coin", 0);
        score = getIntent().getIntExtra("Score", 0);
        cardPlay = new CardPlayManager(deck);
        cardAdapter = new CardAdapter(this, cardPlay.getPlayer().getHand());
        cardPlay.startEncounter();

        //initializing all xml layouts
        this.enemyHP = findViewById(R.id.EnemyHPtextView);
        this.playerHP = findViewById(R.id.PlayerHPtextView);
        this.clawImageView = findViewById(R.id.clawImageView);
        this.bossImageView = findViewById(R.id.bossImageView);
        this.myHand = findViewById(R.id.myHandSpinner);

        update();

        // create a drop down list
        this.myHand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Card clickedItem = (Card) parent.getItemAtPosition(position);
                String clickedCardName = clickedItem.getName();
                String clickedCardDescription = clickedItem.getDescription();
                Toast.makeText(CardPlayActivity.this, clickedCardName + clickedCardDescription, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(CardPlayActivity.this, "Click to Select a Card to Play", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * Initializing all buttons
         */

        nextBtn = findViewById(R.id.startCardFlipButton);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toResult2(0);
            }
        });

        //restart button
        restartBtn = findViewById(R.id.restartButton);
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toResult2(1);
            }
        });

        //end turn button
        endTurnButton = findViewById(R.id.endTurnButton);
        endTurnButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                bossDealDamageAni(clawImageView);
                cardPlay.endTurn();
                update();
            }
        });


        //play button
        playBtn = findViewById(R.id.playButton);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cardPlay.getEnemy().getHealth() > 0 && myHand.getScrollBarSize() > 0) {
                    CardSelectedIndex = myHand.getSelectedItemPosition();

                    cardPlay.play(CardSelectedIndex, cardPlay);

                    update();

                    bossReceiveDamageAni(v);
                }
            }
        });


    }

    public void update() {

        this.enemyHP.setText(String.format("%d/100", this.cardPlay.getEnemy().getHealth()));
        this.playerHP.setText(String.format("%d/100", this.cardPlay.getPlayer().getHealth()));
        this.myHand.setAdapter(this.cardAdapter);

        if (cardPlay.victoryChecker()) {
            nextBtn.setVisibility(View.VISIBLE);
            cardPlay.deck.clear();
            playBtn.setClickable(false);
            endTurnButton.setClickable(false);
        } else if (cardPlay.player.health <= 0) {
            restartBtn.setVisibility(View.VISIBLE);
            playBtn.setClickable(false);
            endTurnButton.setClickable(false);
        }

    }

    public void toResult2(int condition) {

        /**
         * Opening CardPlay Result
         */

        if (condition == 0) {
            score += 100000;
        }

        Intent openResult = new Intent(this, CardPlayResultActivity.class);

        //passing variables
        openResult.putExtra("UserName", userName);
        openResult.putExtra("Coin", coins);
        openResult.putExtra("Score", score);
        openResult.putExtra("Condition", condition);

        startActivity(openResult);

    }

    public void bossReceiveDamageAni(View view) {
        ObjectAnimator rotationAnimation = ObjectAnimator.ofFloat(bossImageView, "rotation", 1.0f, 0.0f);
        rotationAnimation.setDuration(bossAniDuration);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(rotationAnimation);
        animatorSet.start();
    }

    public void bossDealDamageAni(View view) {

        clawImageView.setVisibility(View.VISIBLE);
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(clawImageView, "x", 300f);
        animatorX.setDuration(bossAniDuration);

        ObjectAnimator animatorY = ObjectAnimator.ofFloat(clawImageView, "y", 300f);
        animatorY.setDuration(bossAniDuration);

        ObjectAnimator clawAnimation = ObjectAnimator.ofFloat(clawImageView, View.ALPHA, 1.0f, 0.0f);
        clawAnimation.setDuration(bossAniDuration);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(clawAnimation);
        animatorSet.start();
    }
}

