package com.example.cardgame.clientpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cardgame.R;
import com.example.cardgame.cardplay.CardPlayResultActivity;
import com.example.cardgame.deckbuilder.DeckBuilderResultActivity;
import com.example.cardgame.deckbuilder.DeckBuilderActivity;
import com.example.cardgame.gamedata.Card;
import com.example.cardgame.login.DataBaseHelp;


import java.util.ArrayList;

public class ClientActivity extends AppCompatActivity {

    TextView scoreTextView;
    Button newGameBtn;
    Button continueBtn;
    Button listButton;
    ImageButton achievementBtn;
    ClientManager client;
    String userName;
    int previousGameScore;
    int previousGameCoin;
    BackUp gameBackUp;
    String previousGameState;
    DataBaseHelp dbl;
    SQLiteDatabase dataBase;
    private static final String baseName = "CardUser";
    private static final int baseVer = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_menu);

        client = new ClientManager();
        userName = client.getUsername();
        previousGameCoin = getIntent().getIntExtra("FinalCoinLeft", 0);
        previousGameScore = getIntent().getIntExtra("FinalScore", 0);

        //initializing all buttons
        newGameBtn = findViewById(R.id.newGameButton);
        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame();
            }
        });

        continueBtn = findViewById(R.id.continueButton);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSaved();
            }
        });

        listButton = findViewById(R.id.board);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBoard();
            }
        });

        achievementBtn = findViewById(R.id.achievementImageButton);
        achievementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAchievement();
            }
        });

        if (getIntent().getStringExtra("CurrentState") != null) {
            continueBtn.setVisibility(View.VISIBLE);
            previousGameState = getIntent().getStringExtra("CurrentState");
            checkState(previousGameState);
        }

        if (previousGameCoin > client.getHighestCoin()) {
            client.setHighestCoin(previousGameCoin);
        }
        if (previousGameScore > client.getHighestScore()) {
            client.setHighestScore(previousGameScore);
        }

        scoreTextView = findViewById(R.id.highestScoreTextView);
        client.setHighestScore(findScore());
        String highscore = "Highest Score : " + client.getHighestScore();
        scoreTextView.setText(highscore);

    }

    private void startNewGame() {

        Intent intent = new Intent(this, DeckBuilderActivity.class);

        //passing down the username before it launches the next activity
        intent.putExtra("UserName", userName);

        startActivity(intent);

    }

    private void openBoard() {

        Intent intent = new Intent(this, LeaderBoardActivity.class);
        startActivity(intent);

    }

    private void openAchievement() {
        Intent achievementIntent = new Intent(this, AchievementSystemActivity.class);

        achievementIntent.putExtra("HighestGameScore", client.getHighestScore());
        achievementIntent.putExtra("HighestGameCoin", client.getHighestCoin());

        startActivity(achievementIntent);
    }

    private void checkState(String currentState) {

        if (currentState.equals("DeckBuilder")) {
            int currentCoin = getIntent().getIntExtra("CurrentCoin", 0);
            int currentScore = getIntent().getIntExtra("CurrentScore", 0);
            ArrayList<Card> currentDeck = getIntent().getParcelableArrayListExtra("CurrentDeck");
            gameBackUp = new BackUp(currentCoin, currentScore, currentDeck, "DeckBuilder");
        }
        if (currentState.equals("CardPlay")) {
            int currentCoin = getIntent().getIntExtra("CurrentCoin", 0);
            int currentScore = getIntent().getIntExtra("CurrentScore", 0);
            gameBackUp = new BackUp(currentCoin, currentScore, "CardPlay");
        }
    }

    void startSaved() {

        if (previousGameState.equals("DeckBuilder")) {

            Intent deckBuilderIntent = new Intent(this, DeckBuilderResultActivity.class);

            deckBuilderIntent.putExtra("UserName", userName);
            deckBuilderIntent.putExtra("Coin", gameBackUp.getCoins());
            deckBuilderIntent.putExtra("Score", gameBackUp.getScore());
            deckBuilderIntent.putParcelableArrayListExtra("Deck", gameBackUp.getCard());
            startActivity(deckBuilderIntent);
        }

        if (previousGameState.equals("CardPlay")) {

            Intent deckIntent = new Intent(this, CardPlayResultActivity.class);

            deckIntent.putExtra("UserName", userName);
            deckIntent.putExtra("Coin", gameBackUp.getCoins());
            deckIntent.putExtra("Score", gameBackUp.getScore());
            startActivity(deckIntent);
        }


    }

    private int findScore() {
        dbl = new DataBaseHelp(this, baseName, null, baseVer);
        dataBase = dbl.getReadableDatabase();
        Cursor cursor = dataBase.query("username", new String[]{"name", "password", "score"},
                "name=?", new String[]{userName},
                null, null, "score");
        cursor.moveToNext();
        return cursor.getInt(cursor.getColumnIndex("score"));

    }
}