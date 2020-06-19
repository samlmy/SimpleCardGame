package com.example.cardgame.cardplay.effects;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.cardgame.cardplay.CardPlayManager;

public class EffectDiscardCards extends Effect implements Parcelable {
    private int numCards;

    public EffectDiscardCards(int num) {
        numCards = num;
    }


    public void play(CardPlayManager cardPlay) {
        for (int i = 0; i < numCards; i++) {
            cardPlay.getPlayer().discardRandom();
        }
    }


    public static final Parcelable.Creator<EffectDiscardCards> CREATOR = new Parcelable.Creator<EffectDiscardCards>() {

        public EffectDiscardCards createFromParcel(Parcel in) {
            return new EffectDiscardCards(in);
        }

        public EffectDiscardCards[] newArray(int size) {
            return new EffectDiscardCards[size];
        }
    };


    private EffectDiscardCards(Parcel in) {
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
