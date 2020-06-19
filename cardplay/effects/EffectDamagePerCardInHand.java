package com.example.cardgame.cardplay.effects;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.cardgame.cardplay.CardPlayManager;


public class EffectDamagePerCardInHand extends Effect implements Parcelable {

    private int damage;

    public EffectDamagePerCardInHand(int dmg) {
        damage = dmg;
    }


    public void play(CardPlayManager cardPlay) {

        int damageDealt;
        damageDealt = cardPlay.getPlayer().getHand().size() * damage;
        cardPlay.getEnemy().changeHealth(damageDealt * -1);
    }


    public static final Parcelable.Creator<EffectDamagePerCardInHand> CREATOR = new Parcelable.Creator<EffectDamagePerCardInHand>() {

        public EffectDamagePerCardInHand createFromParcel(Parcel in) {
            return new EffectDamagePerCardInHand(in);
        }

        public EffectDamagePerCardInHand[] newArray(int size) {
            return new EffectDamagePerCardInHand[size];
        }
    };


    private EffectDamagePerCardInHand(Parcel in) {
        damage = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(damage);
    }
}
