package com.example.cardgame.gamedata;

import com.example.cardgame.cardplay.effects.*;

import com.example.cardgame.R;

import java.util.ArrayList;

/**
 * Create a card collection which is a collection of all cards.
 */
public class CardCollection {

    /**
     * Create a card collection.
     */
    public CardCollection() {
    }


    /**
     * Create cards and add cards to the shop.
     */

    public ArrayList<Card> createShopSet() {

        ArrayList<Card> set = new ArrayList<>();
        Card card;
        card = new Card("Deals 6 damage to the enemy.", "lightningstrike", 6, R.drawable.lightningstrike);
        card.addEffect((new EffectDamage(0, 6)));
        set.add(card);

        card = new Card("Deals 4 damage to the enemy.", "fireball", 3, R.drawable.fireball);
        card.addEffect((new EffectDamage(0, 4)));
        set.add(card);

        card = new Card("Deals 8 damage to the enemy.", "tsunami", 8, R.drawable.tsunami);
        card.addEffect((new EffectDamage(0, 8)));
        set.add(card);

        card = new Card("Deals 30 damage to the enemy.", "goldenswords", 15, R.drawable.goldenswords);
        card.addEffect((new EffectDamage(0, 30)));
        set.add(card);


        card = new Card("Gain 20 health back.", "magicalheal", 5, R.drawable.magicalheal);
        card.addEffect((new EffectHeal(20, 0)));
        set.add(card);

        card = new Card("Deal 15 damage for every card in hand", "chococornet", 5, R.drawable.chococornet);
        card.addEffect((new EffectDamagePerCardInHand(15)));
        set.add(card);

        card = new Card("What will happen if they were Mechanical?", "eyesofcthulu", 4, R.drawable.eyesofcthulu);
        set.add(card);

        card = new Card("Discard all your cards, Draw that many cards", "fullmetalguy", 2, R.drawable.fullmetalguy);
        card.addEffect(new EffectUniqueFullMetalGuy());
        set.add(card);

        card = new Card("Take 10 damage, dael 40 damage, draw 1 card", "getintherobot", 8, R.drawable.getintherobot);
        card.addEffect(new EffectDamage(10, 40));
        card.addEffect(new EffectDrawCards(1));
        set.add(card);

        card = new Card("Surely it must do Something", "mechcthun", 2, R.drawable.mechcthun);
        card.addEffect(new EffectUniqueMechCthun());
        set.add(card);

        card = new Card("if you have more than 20 health draw 3 cards", "nekomimi", 6, R.drawable.nekomimi);
        card.addEffect(new EffectUniqueNekoMimi(20, 3));
        set.add(card);

        card = new Card("discard 1 card, then deal 40 damage", "reallybigfire", 6, R.drawable.reallybigfire);
        card.addEffect(new EffectDiscardCards(1));
        card.addEffect(new EffectDamage(0, 40));
        set.add(card);

        card = new Card("Draw 2 cards", "potofarcaneintellect", 4, R.drawable.potofarcaneintellect);
        card.addEffect(new EffectDrawCards(2));
        set.add(card);

        card = new Card("bring yourself back to max health, bring the enemy back to max health", "endlesseye", 4, R.drawable.endlesseye);
        card.addEffect(new EffectHealToFullEnemy());
        card.addEffect(new EffectHealToFullPlayer());
        set.add(card);

        return set;
    }


}
