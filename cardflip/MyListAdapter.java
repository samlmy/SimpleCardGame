package com.example.cardgame.cardflip;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cardgame.R;

public class MyListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] mainTitle;
    private final String[] subtitle;
    private final Integer[] imgId;

    public MyListAdapter(Activity context, String[] mainTitle, String[] subtitle, Integer[] imgId) {
        super(context, R.layout.card_list, mainTitle);

        this.context = context;
        this.mainTitle = mainTitle;
        this.subtitle = subtitle;
        this.imgId = imgId;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.card_list, null, true);

        TextView titleText = rowView.findViewById(R.id.title);
        ImageView imageView = rowView.findViewById(R.id.icon);
        TextView subtitleText = rowView.findViewById(R.id.subtitle);

        titleText.setText(mainTitle[position]);
        imageView.setImageResource(imgId[position]);
        subtitleText.setText(subtitle[position]);

        return rowView;

    }
}

