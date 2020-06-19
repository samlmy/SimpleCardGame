package com.example.cardgame.gamedata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cardgame.R;

import java.util.ArrayList;

public class CardAdapter extends ArrayAdapter {
    /**
     * This class is a try for drop down list, it is not that useful for phase 1.
     */
    public CardAdapter(Context context, ArrayList<Card> cardList) {
        super(context, 0, cardList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.hand_spinner, parent, false
            );
        }

        ImageView cardImageView = convertView.findViewById(R.id.handCard1ImageView);
        TextView cardNameTextView = convertView.findViewById(R.id.handCard1);
        TextView cardDescriptionTextView = convertView.findViewById(R.id.handCardDescription);

        Card currentCard = (Card) getItem(position);

        if (currentCard != null) {
            cardImageView.setImageResource(currentCard.getCardImage());
            cardNameTextView.setText(currentCard.getName());
            cardDescriptionTextView.setText(currentCard.getDescription());
        }

        return convertView;
    }
}
