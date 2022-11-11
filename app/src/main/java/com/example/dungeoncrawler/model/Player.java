package com.example.dungeoncrawler.model;

import android.util.Log;

import java.util.ArrayList;


public class Player extends Character {


    public Player(Race race, Caste caste, int[] attPoints) {

        super(race, caste);
        this.myChar = 'P';

        // add character creation attributes
        for (int i = 0; i < attPoints.length; i++) {
            this.attributes[i].value += attPoints[i];
        }
    }

    public void move(Game game, String input) {
        String ret = "";
        Tile[][] map = game.map;
        Tile currLoc = this.location;
        Tile newLoc = currLoc;

        switch (input) {
            case "a":
                if (currLoc.y > 0 ) {
                    newLoc = map[currLoc.x][currLoc.y - 1];
                    ret += "You moved to the left.";
                } else ret += "You're at the map edge. You cannot move left.";
                break;
            case "w":
                if (currLoc.x > 0) {
                    newLoc = map[currLoc.x - 1][currLoc.y];
                    ret += "You moved up.";
                } else ret += "You are at the map edge. You cannot move up.";
                break;
            case "d":
                if (currLoc.y < map.length - 1) {
                    newLoc = map[currLoc.x][currLoc.y + 1];
                    ret += "You moved to the right."; // move right
                } else ret += "You are at the map edge. You cannot move right.";
                break;
            case "s":
                if (currLoc.x < map.length - 1) {
                    newLoc = map[currLoc.x + 1][currLoc.y];
                    ret += "You moved down."; // move down
                } else ret += "You are at the map edge. You cannot move down.";
                break;
        }

        this.executeMove(newLoc);
        Log.d("Dungeon Crawler", ret);
    }

    public void setAttunedSpell(Spell spell) {
        this.attunedSpell = spell;
    }

    public void equip(Weapon weapon) {
    }

    public ArrayList<Item> search() {
        ArrayList<Item> items = new ArrayList<>(this.location.contents);
        return items;
    }




}