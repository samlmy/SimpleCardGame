package com.example.cardgame.deckbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cardgame.gamedata.Card;
import com.example.cardgame.R;
import com.example.cardgame.clientpage.ClientManager;

public class DeckBuilderActivity extends AppCompatActivity {

    ImageButton[] buttonList = new ImageButton[4];

    private Button refreshBtn;
    private Button tempNext;
    private Button restartBtn;

    private TextView totalDeck;
    private TextView gold;
    private TextView score;

    DeckBuilderManager deckBuilder;
    String userName;
    ClientManager client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_builder);

        // all stuff below are creating UI

        client = new ClientManager();
        deckBuilder = new DeckBuilderManager();
        userName = client.getUsername();


        //get userName from Main
//        final String userName = getIntent().getStringExtra("UserName");

        //**new** set all the image buttons
        buttonList[0] = findViewById(R.id.card1);
        buttonList[1] = findViewById(R.id.card2);
        buttonList[2] = findViewById(R.id.card3);
        buttonList[3] = findViewById(R.id.card4);
        for (int i = 0; i < buttonList.length; i++) {
            setButton(buttonList[i], i);
        }

        // temporary button
        tempNext = findViewById(R.id.NextButton);
        tempNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toResult();
            }
        });

        //restart button
        restartBtn = findViewById(R.id.restartButton);
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deckBuilder.restart();
                refresh();
                update();
                tempNext.setVisibility(View.INVISIBLE);
            }
        });


        refreshBtn = findViewById(R.id.refreshButton);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deckBuilder.getDeck().size() < 10) {
                    refresh();
                }
            }
        });

        totalDeck = findViewById(R.id.totalDeckTextView);
        gold = findViewById(R.id.goldTextView);
        score = findViewById(R.id.scoreTextView);

        deckBuilder.restart();
        refresh();
        update();
        tempNext.setVisibility(View.INVISIBLE);
    }

    /**
     * Update the information of the gold, deck size and score.
     */
    public void update() {
        String g = "Your Gold:" + deckBuilder.getMoney();
        gold.setText(g);
        String deckSize = "Your Deck:" + deckBuilder.getDeck().size() + "/10";
        totalDeck.setText(deckSize);
        String totalScore = "Your Score: " + deckBuilder.getScore();
        score.setText(totalScore);

    }

    /**
     * Transfer the deck to a result screen and start the next level game.
     */

    public void toResult() {

        Intent openResult = new Intent(this, DeckBuilderResultActivity.class);

        openResult.putExtra("UserName", userName);
        openResult.putExtra("Coin", deckBuilder.getMoney());
        openResult.putExtra("Score", deckBuilder.getScore());
        openResult.putParcelableArrayListExtra("Deck", deckBuilder.getDeck());

        startActivity(openResult);

    }


    /**
     * Buy the card and update gold, deck size and score.
     */
    public void buy(ImageButton btn, int c) {

        deckBuilder.buyCard(c);
        btn.setClickable(false);
        btn.setImageResource(R.drawable.card);

        String deckSize = "Your Deck:" + deckBuilder.getDeck().size() + "/10";
        totalDeck.setText(deckSize);

        String g = "Your Gold:" + deckBuilder.getMoney();
        gold.setText(g);

        String totalScore = "Your Score: " + deckBuilder.getScore();
        score.setText(totalScore);

    }

    void setButton(final ImageButton btn, final int i) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deckBuilder.getMoney() >= deckBuilder.shop.getCurrent().get(i).getCost() &&
                        deckBuilder.getDeck().size() < 10) {
                    deckSizeCheck(i + 1);
                    buy(btn, i);
                }
                if (deckBuilder.getDeck().size() == 10) {
                    tempNext.setVisibility(View.VISIBLE);
                }

            }
        });
    }


    /**
     * When you are not satisfied of the four choices or bought out all choices,
     * you can refresh to generate new offers.
     */

    public void refresh() {

        deckSizeCheck(deckBuilder.getDeck().size());

        //reset images of card1-4

        deckBuilder.refresh();

        if (deckBuilder.getMoney() < 2) {
            refreshBtn.setClickable(false);
        } else {
            refreshBtn.setClickable(true);
        }

        String g = "Your Gold:" + deckBuilder.getMoney();
        gold.setText(g);

        for (int i = 0; i < 4; i++) {
            Card curr = deckBuilder.shop.getCurrent().get(i);
            String name = curr.getName();
            int resID = getResources().getIdentifier(name, "drawable", getPackageName());
            if (i == 0) {
                buttonList[0].setImageResource(resID);
                if (curr.getCost() <= deckBuilder.getMoney()) {
                    buttonList[0].setClickable(true);
                }
            } else if (i == 1) {
                buttonList[1].setImageResource(resID);
                if (curr.getCost() <= deckBuilder.getMoney()) {
                    buttonList[1].setClickable(true);
                }
            } else if (i == 2) {
                buttonList[2].setImageResource(resID);
                if (curr.getCost() <= deckBuilder.getMoney()) {
                    buttonList[2].setClickable(true);
                }
            } else {
                buttonList[3].setImageResource(resID);
                if (curr.getCost() <= deckBuilder.getMoney()) {
                    buttonList[3].setClickable(true);
                }
            }
        }
    }

    /**
     * Non-clickable once the deck reaches to 10 cards
     */
    private void deckSizeCheck(int d) {

        if (d == 10) {
            refreshBtn.setClickable(false);
            for (ImageButton button : buttonList) {
                button.setClickable(false);
            }
        }
    }
}
