package com.example.cardgame.clientpage;

public class Leader {
    private String name;
    private int score;

    Leader(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    String makeString() {
        return name + " , " + score;
    }
}
