package com.example.cardgame.cardplay.effects;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.cardgame.cardplay.CardPlayManager;


public class EffectHealToFullPlayer extends Effect implements Parcelable {

    public EffectHealToFullPlayer() {

    }

    public void play(CardPlayManager cardPlay) {
        cardPlay.getPlayer().setHealth(cardPlay.getPlayer().getMaxHealth());
    }


    public static final Parcelable.Creator<EffectHealToFullPlayer> CREATOR = new Parcelable.Creator<EffectHealToFullPlayer>() {

        public EffectHealToFullPlayer createFromParcel(Parcel in) {
            return new EffectHealToFullPlayer(in);
        }

        public EffectHealToFullPlayer[] newArray(int size) {
            return new EffectHealToFullPlayer[size];
        }
    };


    private EffectHealToFullPlayer(Parcel in) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {

    }
}
