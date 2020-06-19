package com.example.cardgame.cardplay;

import java.util.Random;

public class EnemyCharacter {


    /**
     * The health point of the enemy character.
     */
    private int health;
    private final int maxHealth = 100;

    /*
     * For transfer data to the battle log.
     */


    // private int damagedeal;

    /**
     * Create a new EnemyCharacter.
     */
    EnemyCharacter() {
        this.health = maxHealth;
    }

    /**
     * Start the play round of the enemy character.
     */
    public void play(CardPlayManager cpm) {

        Random rand = new Random();

        /*
         * enemy takes 1 of 4 actions:
         * deal 1-10 damage to player
         * heal 1-3 damage
         * discard 1 of the player's cards
         * deal 2-20 damage (critical hit)
         * */
        int enemyBehaviour = rand.nextInt(4);

        switch (enemyBehaviour) {
            case 0:
                cpm.getPlayer().changeHealth(-1 * (rand.nextInt(10) + 1));
                break;

            case 1:
                cpm.getPlayer().changeHealth(-2 * (rand.nextInt(10) + 1));
                break;

            case 2:
                changeHealth(rand.nextInt(3) + 1);

            case 3:
                cpm.getPlayer().discardRandom();

        }

    }

    /**
     * Get the health of the enemy character.
     */
    int getHealth() {
        return this.health;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    public void changeHealth(int change) {
        health += change;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    public int getMaxHealth() {
        return maxHealth;
    }


}
