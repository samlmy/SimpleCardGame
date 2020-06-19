package com.example.cardgame.cardflip;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.content.DialogInterface;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.database.Cursor;
import android.app.AlertDialog;
import android.widget.Toast;

import com.example.cardgame.clientpage.ClientActivity;
import com.example.cardgame.R;
import com.example.cardgame.clientpage.DataBaseHelper;
import com.example.cardgame.login.DataBaseHelp;


public class GameOver extends Activity {

    Button close;
    Button saveButton;
    Button uploadButton;
    CardFlipManager CardFlip;
    TextView thirdGameScore;
    TextView finalScore;
    TextView coinsLeft;
    TextView name;
    String userName;
    int coins;
    int score;
    int finalscoreforpassing;
    DataBaseHelp dbl;
    SQLiteDatabase dataBase;
    private static final String baseName = "CardUser";
    private static final int baseVer = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        CardFlip = new CardFlipManager();

        userName = getIntent().getStringExtra("UserName");
        coins = getIntent().getIntExtra("Coin", 0);
        score = getIntent().getIntExtra("Score", 0);

        thirdGameScore = findViewById(R.id.thirdscoreLable);
        String flip = "your CardFlip Score is : " + CardFlip.getScore();
        thirdGameScore.setText(flip);


        name = findViewById(R.id.nameLabel);
        name.setText("Name : " + userName);

        coinsLeft = findViewById(R.id.coinLabel);
        String totalcoinsleft = "Coins left : " + coins;
        coinsLeft.setText(totalcoinsleft);

        finalScore = findViewById(R.id.finalScoreLabel);
        finalscoreforpassing = CardFlip.getScore() + score + (coins * 1000);
        String finalscore = "Final Score = \nCoins Left: " + coins + " * 1000 = "
                + coins * 1000 + " + \n" + flip +
                " + \nPrevious Game Score : " + score + " = \n" + finalscoreforpassing + " POINTS";

        finalScore.setText(finalscore);


        close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });

        saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveScore();
            }
        });

        uploadButton = findViewById(R.id.uploadtoboard);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDialog();

            }
        });

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

    public void restartGame() {
        Intent newGameIntent = new Intent(this, ClientActivity.class);
        startActivity(newGameIntent);
    }

    void saveScore() {
        Intent newGameIntent = new Intent(this, ClientActivity.class);
        newGameIntent.putExtra("FinalScore", finalscoreforpassing);
        newGameIntent.putExtra("FinalCoinLeft", coins);
        checkScore();
        startActivity(newGameIntent);

    }

    public void saveDialog() {
        final EditText et = new EditText(this);
        new AlertDialog.Builder(this).setTitle("Enter your name")
                .setView(et)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = et.getText().toString();
                        uploadToBoard(name);
                    }
                }).show();

    }

    void uploadToBoard(String boardName) {
        DataBaseHelper dh = new DataBaseHelper(this, "LeaderBoard", null, baseVer);
        SQLiteDatabase dataBase = dh.getWritableDatabase();
        String sql = "insert into highscore (name,score) values(?,?)";
        dataBase.execSQL(sql, new Object[]{boardName, finalscoreforpassing});
        Toast.makeText(this, "Result saved to the board.",
                Toast.LENGTH_LONG).show();
    }

    void checkScore() {
        dbl = new DataBaseHelp(this, baseName, null, baseVer);
        dataBase = dbl.getReadableDatabase();
        Cursor cursor = dataBase.query("username", new String[]{"name", "password", "score"},
                "name=?", new String[]{userName},
                null, null, "score");
        while (cursor.moveToNext()) {
            int score = cursor.getInt(cursor.getColumnIndex("score"));
            if (finalscoreforpassing > score) {
                ContentValues values = new ContentValues();
                values.put("score", finalscoreforpassing);
                dataBase.update("username", values, "name=?", new String[]{userName});
                cursor.close();
            }
        }
    }

}
