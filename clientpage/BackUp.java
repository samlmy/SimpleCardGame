package com.example.cardgame.clientpage;


import com.example.cardgame.gamedata.Card;

import java.util.ArrayList;

public class BackUp {

    static private int coins;
    static private int score;
    static private ArrayList<Card> card;
    static String currentState;

    BackUp(int currentCoin, int currentScore, ArrayList<Card> cards, String current) {

        /**
         * Storing variables
         */
        coins = currentCoin;
        score = currentScore;
        card = cards;
        currentState = current;
    }

    BackUp(int currentCoin, int currentScore, String current) {
        coins = currentCoin;
        score = currentScore;
        currentState = current;
    }

    public int getCoins() {
        return coins;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Card> getCard() {
        return card;
    }

}
