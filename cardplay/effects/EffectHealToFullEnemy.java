package com.example.cardgame.cardplay.effects;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.cardgame.cardplay.CardPlayManager;


public class EffectHealToFullEnemy extends Effect implements Parcelable {

    public EffectHealToFullEnemy() {

    }

    public void play(CardPlayManager cardPlay) {
        cardPlay.getEnemy().setHealth(cardPlay.getEnemy().getMaxHealth());
    }


    public static final Parcelable.Creator<EffectHealToFullEnemy> CREATOR = new Parcelable.Creator<EffectHealToFullEnemy>() {

        public EffectHealToFullEnemy createFromParcel(Parcel in) {
            return new EffectHealToFullEnemy(in);
        }

        public EffectHealToFullEnemy[] newArray(int size) {
            return new EffectHealToFullEnemy[size];
        }
    };


    private EffectHealToFullEnemy(Parcel in) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {

    }
}
