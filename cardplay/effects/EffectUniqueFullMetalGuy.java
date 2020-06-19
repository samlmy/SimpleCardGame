package com.example.cardgame.cardplay.effects;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.cardgame.cardplay.CardPlayManager;

import java.util.ArrayList;


public class EffectUniqueFullMetalGuy extends Effect implements Parcelable {

    public EffectUniqueFullMetalGuy() {

    }

    /*
     * Discards all cards and Draws that many
     * */
    public void play(CardPlayManager cardPlay) {

        ArrayList hand = cardPlay.getPlayer().getHand();
        int size = hand.size();

        cardPlay.getPlayer().discardAll();

        for (int i = 0; i < size; i++) {
            cardPlay.getPlayer().draw();
        }

    }


    public static final Parcelable.Creator<EffectUniqueFullMetalGuy> CREATOR = new Parcelable.Creator<EffectUniqueFullMetalGuy>() {

        public EffectUniqueFullMetalGuy createFromParcel(Parcel in) {
            return new EffectUniqueFullMetalGuy(in);
        }

        public EffectUniqueFullMetalGuy[] newArray(int size) {
            return new EffectUniqueFullMetalGuy[size];
        }
    };


    private EffectUniqueFullMetalGuy(Parcel in) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {

    }
}
