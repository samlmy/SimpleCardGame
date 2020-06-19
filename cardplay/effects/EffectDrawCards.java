package com.example.cardgame.cardplay.effects;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.cardgame.cardplay.CardPlayManager;

public class EffectDrawCards extends Effect implements Parcelable {
    int numCards;

    public EffectDrawCards(int num) {
        numCards = num;
    }


    public void play(CardPlayManager cardPlay) {
        for (int i = 0; i < numCards; i++) {
            cardPlay.getPlayer().draw();
        }
    }


    public static final Parcelable.Creator<EffectDrawCards> CREATOR = new Parcelable.Creator<EffectDrawCards>() {

        public EffectDrawCards createFromParcel(Parcel in) {
            return new EffectDrawCards(in);
        }

        public EffectDrawCards[] newArray(int size) {
            return new EffectDrawCards[size];
        }
    };


    private EffectDrawCards(Parcel in) {
        numCards = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(numCards);
    }
}
