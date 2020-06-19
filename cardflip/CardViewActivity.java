package com.example.cardgame.cardflip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardgame.R;

public class CardViewActivity extends AppCompatActivity {
    ListView list;

    String userName;
    int score;
    int coins;

    String[] maintitle = {
            "choco cornet", "eyes of cthulu",
            "full metal guy", "get in the robot",
            "really big fire", "mechcthun",
            "nekomimi", "pot of arcane intellect",
            "endless eye",
            "tsunami", "lightningstrike",
            "fireball", "goldenswords",
            "magical heal",
    };

    String[] subtitle = {
            "Deal 15 damage for every card in hand",
            "What will happen if they were Mechanical?",
            "Discard all your cards, Draw that many cards",
            "Take 10 damage, dael 40 damage, draw 1 card",
            "discard 1 card, then deal 40 damage",
            "Surely it must do Something",
            "if you have more than 20 health draw 3 cards",
            "Draw 2 cards",
            "bring yourself back to max health, bring the enemy back to max health",
            "Deals 8 damage to the enemy.",
            "Deals 6 damage to the enemy.",
            "Deals 4 damage to the enemy.",
            "Deals 30 damage to the enemy.",
            "Gain 20 health back.",
    };


    Integer[] imgid = new Integer[]{R.drawable.goldenswords, R.drawable.fireball,
            R.drawable.lightningstrike, R.drawable.magicalheal, R.drawable.tsunami,
            R.drawable.chococornet, R.drawable.eyesofcthulu, R.drawable.fullmetalguy,
            R.drawable.getintherobot, R.drawable.mechcthun, R.drawable.nekomimi,
            R.drawable.reallybigfire, R.drawable.potofarcaneintellect, R.drawable.endlesseye};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        final TextView simpleTextView = (TextView) findViewById(R.id.simpleTextView);
        Button changeText = (Button) findViewById(R.id.btnChangeText);
        changeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpleTextView.setText("You can use coins to buy \n flip times and " +
                        "click FLIP! to start flip." + "\n" +
                        "Don't waste your coins!");
            }
        });

        userName = getIntent().getStringExtra("UserName");
        coins = getIntent().getIntExtra("Coin", 0);
        score = getIntent().getIntExtra("Score", 0);

        MyListAdapter adapter = new MyListAdapter(this, maintitle, subtitle, imgid);
        list = findViewById(R.id.list);
        list.setAdapter(adapter);

        Button nextBtn;
        nextBtn = findViewById(R.id.next_button);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Flip();
            }
        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                    Toast.makeText(getApplicationContext(), "Click FLIP! to start flip", Toast.LENGTH_SHORT).show();
                } else if (position == 1) {

                    Toast.makeText(getApplicationContext(), "Click FLIP! to start flip", Toast.LENGTH_SHORT).show();
                } else if (position == 2) {

                    Toast.makeText(getApplicationContext(), "Click FLIP! to start flip", Toast.LENGTH_SHORT).show();
                } else if (position == 3) {

                    Toast.makeText(getApplicationContext(), "Click FLIP! to start flip", Toast.LENGTH_SHORT).show();
                } else if (position == 4) {

                    Toast.makeText(getApplicationContext(), "Click FLIP! to start flip", Toast.LENGTH_SHORT).show();
                } else if (position == 5) {
                    Toast.makeText(getApplicationContext(), "Click FLIP! to start flip", Toast.LENGTH_SHORT).show();
                } else if (position == 6) {
                    Toast.makeText(getApplicationContext(), "Click FLIP! to start flip", Toast.LENGTH_SHORT).show();
                } else if (position == 7) {
                    Toast.makeText(getApplicationContext(), "Click FLIP! to start flip", Toast.LENGTH_SHORT).show();
                } else if (position == 8) {
                    Toast.makeText(getApplicationContext(), "Click FLIP! to start flip", Toast.LENGTH_SHORT).show();
                } else if (position == 9) {
                    Toast.makeText(getApplicationContext(), "Click FLIP! to start flip", Toast.LENGTH_SHORT).show();
                } else if (position == 10) {
                    Toast.makeText(getApplicationContext(), "Click FLIP! to start flip", Toast.LENGTH_SHORT).show();
                } else if (position == 11) {
                    Toast.makeText(getApplicationContext(), "Click FLIP! to start flip", Toast.LENGTH_SHORT).show();
                } else if (position == 12) {
                    Toast.makeText(getApplicationContext(), "Click FLIP! to start flip", Toast.LENGTH_SHORT).show();
                } else if (position == 13) {
                    Toast.makeText(getApplicationContext(), "Click FLIP! to start flip", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }

    public void Flip() {
        Intent flipIntent = new Intent(this, CardFlipActivity.class);

        flipIntent.putExtra("UserName", userName);
        flipIntent.putExtra("Coin", coins);
        flipIntent.putExtra("Score", score);

        startActivity(flipIntent);
    }
}
