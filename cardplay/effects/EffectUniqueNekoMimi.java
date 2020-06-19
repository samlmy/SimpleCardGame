package com.example.cardgame.cardplay.effects;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.cardgame.cardplay.CardPlayManager;


public class EffectUniqueNekoMimi extends Effect implements Parcelable {


    private int healthRequired;
    private int cardsDrawn;

    public EffectUniqueNekoMimi(int healthRequired, int cardsDrawn) {
        this.healthRequired = healthRequired;
        this.cardsDrawn = cardsDrawn;
    }

    /*
     * checks if eyes of cthulu is in hand,
     * if so reduce enemy's health to 0
     *
     * this action is hidden as the card does not say that it does this
     * */
    public void play(CardPlayManager cardPlay) {

        if (cardPlay.getPlayer().getHealth() >= healthRequired) {

            for (int i = 0; i < cardsDrawn; i++) {
                cardPlay.getPlayer().draw();
            }
        }
    }


    public static final Parcelable.Creator<EffectUniqueNekoMimi> CREATOR = new Parcelable.Creator<EffectUniqueNekoMimi>() {

        public EffectUniqueNekoMimi createFromParcel(Parcel in) {
            return new EffectUniqueNekoMimi(in);
        }

        public EffectUniqueNekoMimi[] newArray(int size) {
            return new EffectUniqueNekoMimi[size];
        }
    };


    private EffectUniqueNekoMimi(Parcel in) {
        healthRequired = in.readInt();
        cardsDrawn = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(healthRequired);
        dest.writeInt(cardsDrawn);
    }
}
