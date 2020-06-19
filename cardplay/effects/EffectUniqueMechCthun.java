package com.example.cardgame.cardplay.effects;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.cardgame.cardplay.CardPlayManager;
import com.example.cardgame.gamedata.Card;
import java.util.ArrayList;


public class EffectUniqueMechCthun extends Effect implements Parcelable {


    public EffectUniqueMechCthun(){

    }
    /*
    * checks if eyes of cthulu is in hand,
    * if so reduce enemy's health to 0
    *
    * this action is hidden as the card does not say that it does this
    * */
    public void play(CardPlayManager cardPlay){
        ArrayList hand = cardPlay.getPlayer().getHand();
        boolean haveEyes = false;

        for(int i = 0; i < hand.size(); i++){
            if (((Card)hand.get(i)).getName().equals("eyesofcthulu")){
            haveEyes = true;
            }

        }

        if (haveEyes){
            cardPlay.getEnemy().setHealth(0);
        }
    }


    public static final Parcelable.Creator<EffectUniqueMechCthun> CREATOR = new Parcelable.Creator<EffectUniqueMechCthun>() {

        public EffectUniqueMechCthun createFromParcel(Parcel in) {
            return new EffectUniqueMechCthun(in);
        }

        public EffectUniqueMechCthun[] newArray(int size) {
            return new EffectUniqueMechCthun[size];
        }
    };


    private EffectUniqueMechCthun(Parcel in){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {

    }
}
