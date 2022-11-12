package com.example.dungeoncrawler.model;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.example.dungeoncrawler.view.ExploreFragment;

import java.util.ArrayList;

public class Tile
{
    boolean available = true;
    public Character occupant;
    public char display = 'X';
    public int x;
    public int y;
    //char display = occupant.sprite; //display the occupants sprite
    ArrayList<Item> contents = new ArrayList<>();
    Button button;

    Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public char display() {
        if (occupant == null) {
            display = 'X';
        } return this.display;
    }
}
