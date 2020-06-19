package com.example.cardgame.deckbuilder;

import com.example.cardgame.gamedata.Card;

import java.util.ArrayList;

public class DeckBuilderManager {

    /**
     * The deck the player is building.
     */
    private static ArrayList<Card> deck = new ArrayList<>();

    /**
     * Original money which is owned by the player.
     */
    private final static int startingMoney = 200;
    private static int money = startingMoney;

    /**
     * The card shop.
     */
    CardShop shop;

    /**
     * The score of the deck.
     */
    private static int score;

    /**
     * Create a new DeckBuilderManager.
     */
    DeckBuilderManager() {
        shop = new CardShop();
        shop.generateOffers();
    }

    /**
     * Buy a new card from the shop current offering.
     */
    void buyCard(int card) {
        System.out.printf("buyCard: card=%d\n", card);
        if (money >= shop.getCurrent().get(card).getCost()) {
            money -= shop.getCurrent().get(card).getCost();
            deck.add(shop.getCurrent().get(card));
            score += shop.getCurrent().get(card).getCost() * 1000;
        }
    }

    /**
     * Get the deck.
     */
    ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Get money.
     */
    int getMoney() {
        return money;
    }


    /**
     * Get Score
     */

    public int getScore() {
        return score;
    }

    /**
     * Refresh the current offers.
     */
    void refresh() {
        shop.generateOffers();
        money -= 2;
    }

    void restart() {
        money = startingMoney;
        deck = new ArrayList<>();
        score = 0;
    }

}