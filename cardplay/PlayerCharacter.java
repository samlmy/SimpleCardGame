package com.example.cardgame.cardplay;

import com.example.cardgame.gamedata.Card;

import java.util.ArrayList;
import java.util.Random;

public class PlayerCharacter {

    /**
     * The health point of the player.
     */
    int health;
    /*
     * Cards in the reserve.
     */
    private ArrayList<Card> reserve;
    /**
     * Cards in the hand.
     */
    private ArrayList<Card> hand;
    /**
     * Cards that were used.
     */
    private ArrayList<Card> discard;

    /**
     * Create a new player Character.
     */
    private final int maxHealth = 100;

    PlayerCharacter(ArrayList<Card> deck) {
        health = maxHealth;
        reserve = deck;
        discard = new ArrayList<>();
        hand = new ArrayList<>();
    }

    /**
     * Use a card, remove it from the hand and add it to the discard.
     * the card is discarded first then played, this is only done for gameplay reasons and has no
     * effect on the complexity of the code
     */
    void playCard(int selectedCardIndex, CardPlayManager cpm) {
        if (this.hand.size() > 0) {
            Card card = hand.get(selectedCardIndex);
            discard(selectedCardIndex);
            card.play(cpm);
        }
    }

    /**
     * Discard all cards in your hand.
     */
    public void discardAll() {
        discard.addAll(hand);
        hand.clear();
    }

    /**
     * Draw the first card from the reserve.
     */
    public void draw() {
        if (reserve.isEmpty()) {
            reStock();
        }
        // we want to make sure the reserve is not empty even after refilling it
        if (!reserve.isEmpty()) {
            hand.add(reserve.get(0));
            reserve.remove(0);
        }
    }

    private void discard(int index) {
        Card card = hand.get(index);
        discard.add(card);
        hand.remove(card);

    }

    public void discardRandom() {
        if (!hand.isEmpty()) {
            Random rand = new Random();
            int index = rand.nextInt(hand.size());
            discard(index);
        }
    }

    /**
     * Restock the reserve.
     */
    private void reStock() {
        reserve.addAll(discard);
        discard.clear();
    }

    public int getHealth() {
        return health;
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


    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}
