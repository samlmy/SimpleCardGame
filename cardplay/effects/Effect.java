package com.example.cardgame.cardplay.effects;

import android.os.Parcelable;

import com.example.cardgame.cardplay.CardPlayManager;

public abstract class Effect implements Parcelable {

    public abstract void play(CardPlayManager cardPlay);
    /*
     * effects will describe what the card can / will do, for example a card can have multiple
     * effects such as draw 1 and deal 5 damage.
     *
     * in the future this organization of card and effects allow us to combine cards, add effects
     * to cards, and to upgrade cards
     * */

}
