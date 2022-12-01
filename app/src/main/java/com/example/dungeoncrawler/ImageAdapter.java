package com.example.dungeoncrawler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    //tileValues should identify what kind of button we should display based on what occupant of tile
    private final String[] tileValues;

    public ImageAdapter(Context context, String[] tileValues) {
        this.context = context;
        this.tileValues = tileValues;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.tile, null);


            // set image based on selected text
            ImageButton button = (ImageButton) gridView
                    .findViewById(R.id.image);

            String identity = tileValues[position];

            if (identity.equals("Windows")) {
                button.setImageResource(com.google.android.material.R.drawable.abc_ic_star_black_16dp);
            } else if (identity.equals("iOS")) {
                button.setImageResource(R.drawable.ios_logo);
            } else if (mobile.equals("Blackberry")) {
                button.setImageResource(R.drawable.blackberry_logo);
            } else {
                button.setImageResource(R.drawable.android_logo);
            }

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return mobileValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
