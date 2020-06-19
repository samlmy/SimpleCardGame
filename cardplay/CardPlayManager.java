package com.example.cardgame.cardplay;

import com.example.cardgame.gamedata.Card;

import java.util.ArrayList;

public class CardPlayManager {
    /**
     * The deck picked by the player.
     */
    ArrayList<Card> deck;

    /**
     * The player of the game.
     */
    PlayerCharacter player;

    /**
     * The enemy character of the game.6
     */
    private EnemyCharacter enemy;
    //private static int score; @phase 2.


    /**
     * Create a card play manager.
     */
    CardPlayManager(ArrayList<Card> d) {
        deck = d;
        player = new PlayerCharacter(deck);
        enemy = new EnemyCharacter();
    }

    public void play(int selectedCardIndex, CardPlayManager cpm) {
        player.playCard(selectedCardIndex, cpm);
    }

    //does things that should be done at the start of every fight,
    void startEncounter() {

        for (int i = 0; i < 3; i++) {
            player.draw();
        }

    }

    //does things that should be done at the end of every player turn i.e now the enemy does something
    void endTurn() {
        enemy.play(this);
        for (int i = 0; i < 1; i++) {
            player.draw();
        }
    }

    public PlayerCharacter getPlayer() {
        return player;
    }

    public EnemyCharacter getEnemy() {
        return enemy;
    }

    /**
     * Check if the player win the game to go to next level.
     */

    boolean victoryChecker() {
        return this.getEnemy().getHealth() <= 0;
    }

}
