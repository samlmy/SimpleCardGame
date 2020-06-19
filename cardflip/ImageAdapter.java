package com.example.cardgame.cardflip;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.cardgame.R;


public class ImageAdapter extends BaseAdapter {

    private Context context;

    ImageAdapter(Context context) {
        this.context = context;
    }

    public int getCount() {
        return 16;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //set up the images at the start of the game
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(this.context);
            imageView.setLayoutParams(new GridView.LayoutParams(250, 350));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        } else imageView = (ImageView) convertView;
        imageView.setImageResource(R.drawable.hidden);

        return imageView;
    }


}
