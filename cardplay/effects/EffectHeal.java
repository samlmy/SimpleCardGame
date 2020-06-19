package com.example.cardgame.cardplay.effects;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.cardgame.cardplay.CardPlayManager;

public class EffectHeal extends Effect implements Parcelable {
    private int selfHeal;
    private int enemyHeal;

    public EffectHeal(int self, int enemy) {
        selfHeal = self;
        enemyHeal = enemy;
    }


    public void play(CardPlayManager cardPlay) {
        cardPlay.getPlayer().changeHealth(selfHeal);
        cardPlay.getEnemy().changeHealth(enemyHeal);
    }


    public static final Parcelable.Creator<EffectHeal> CREATOR = new Parcelable.Creator<EffectHeal>() {

        public EffectHeal createFromParcel(Parcel in) {
            return new EffectHeal(in);
        }

        public EffectHeal[] newArray(int size) {
            return new EffectHeal[size];
        }
    };


    private EffectHeal(Parcel in) {
        selfHeal = in.readInt();
        enemyHeal = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(selfHeal);
        dest.writeInt(enemyHeal);
    }
}
