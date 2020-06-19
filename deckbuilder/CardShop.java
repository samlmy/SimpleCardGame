package com.example.cardgame.deckbuilder;

import com.example.cardgame.gamedata.Card;
import com.example.cardgame.gamedata.CardCollection;

import java.util.ArrayList;
import java.util.Random;

class CardShop {

    /**
     * All cards that the shop can offer.
     */
    private ArrayList<Card> allOffers;
    /**
     * Cards that the shop is offering.
     */
    private ArrayList<Card> currentOffers;

    private CardCollection cardCollection;
    private Random rand;

    /**
     * Create a new card shop
     */
    CardShop() {
        cardCollection = new CardCollection();
        this.allOffers = cardCollection.createShopSet();
        this.currentOffers = new ArrayList<>();
        rand = new Random();
    }

    /**
     * Generate 4 random offers from allOffers to currentOffers
     */
    void generateOffers() {
        /* Reset currentOffers */
        currentOffers.clear();
        int len = allOffers.size();

        for (int i = 0; i < 4; i++) {

            currentOffers.add(allOffers.get(rand.nextInt(len)));
        }
    }

    /**
     * Get CurrentOffers.
     */
    ArrayList<Card> getCurrent() {
        return currentOffers;
    }

}
