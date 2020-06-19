package com.example.cardgame.cardplay.effects;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.cardgame.cardplay.CardPlayManager;

public class EffectDamage extends Effect implements Parcelable {

    private int selfDamage;
    private int enemyDamage;

    public EffectDamage(int self, int enemy) {
        selfDamage = self;
        enemyDamage = enemy;
    }


    public void play(CardPlayManager cardPlay) {
        cardPlay.getPlayer().changeHealth(selfDamage * -1);
        cardPlay.getEnemy().changeHealth(enemyDamage * -1);
    }


    public static final Parcelable.Creator<EffectDamage> CREATOR = new Parcelable.Creator<EffectDamage>() {

        public EffectDamage createFromParcel(Parcel in) {
            return new EffectDamage(in);
        }

        public EffectDamage[] newArray(int size) {
            return new EffectDamage[size];
        }
    };


    private EffectDamage(Parcel in) {
        selfDamage = in.readInt();
        enemyDamage = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(selfDamage);
        dest.writeInt(enemyDamage);
    }

}


