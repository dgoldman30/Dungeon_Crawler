package com.example.dungeoncrawler.model;

import android.widget.Button;

import com.example.dungeoncrawler.R;

import java.util.ArrayList;

public class Tile
{
    boolean available = true;
    public Character occupant;
    public char display = 'X';
    public int x;
    public int y;

    public int floor = R.drawable.floor_large;
    public int ladder = R.drawable.ladder_large;

    public boolean stairs = false;
    //char display = occupant.sprite; //display the occupants sprite
    ArrayList<Item> contents = new ArrayList<>();
    Button button;

    Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public char display() {
        if (stairs) { display = 'O'; }
        else if (occupant == null) { display = 'X'; }
        return this.display;
    }

    public void toStairs() {
        display = 'O';
        stairs = true;
    }
}
