package com.example.cardgame.gamedata;

import android.annotation.TargetApi;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.cardgame.cardplay.CardPlayManager;
import com.example.cardgame.cardplay.effects.Effect;

import java.util.ArrayList;

/**
 * Implements parcelable for data transfer in phase 2
 */

public class Card implements Parcelable {
    /**
     * Description of the card.
     */
    private String description;
    /**
     * The name of the card
     */
    private String cardName;
    /**
     * The money cost of the card.
     */
    private int cost;
    /**
     * The damage of the card.
     */
    private int cardImage;

    /**
     * Create a card.
     */

    private ArrayList<Effect> effects;

    public Card(String description, String cardName, int cost, int image) {
        this.description = description;
        this.cardName = cardName;
        this.cost = cost;
        effects = new ArrayList<>();
        this.cardImage = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Card(Parcel in) {
        description = in.readString();
        cardName = in.readString();
        cost = in.readInt();
        cardImage = in.readInt();
        effects = new ArrayList<>();
        ClassLoader loader = Effect.class.getClassLoader();
        in.readList(effects, loader);
    }

    @Override
    @TargetApi(28)
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(cardName);
        dest.writeInt(cost);
        dest.writeInt(cardImage);
        dest.writeList(effects);
    }

    public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {

        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    /**
     * Getter functions.
     */
    public String getName() {
        return this.cardName;
    }

    public int getCost() {
        return this.cost;
    }

    public String getDescription() {
        return this.description;
    }

    // public in case other games need the card image
    int getCardImage() {
        return cardImage;
    }

    // public in case effects need to add effects / effects are added in game
    void addEffect(Effect effect) {
        effects.add(effect);
    }

    /**
     * When a player uses this card in game 2, this method is called
     * it plays all the effects that this card has
     */
    public void play(CardPlayManager cpm) {

        for (int i = 0; i < effects.size(); i++) {
            effects.get(i).play(cpm);
        }
    }
}
