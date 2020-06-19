package com.example.cardgame.clientpage;


public class ClientManager {
    private static int highestScore;
    private static int highestCoin;
    private static String username;


    int getHighestScore() {
        return highestScore;
    }

    void setHighestScore(int score) {
        highestScore = score;
    }

    int getHighestCoin() {
        return highestCoin;
    }

    void setHighestCoin(int coin) {
        highestCoin = coin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        username = name;
    }
}
