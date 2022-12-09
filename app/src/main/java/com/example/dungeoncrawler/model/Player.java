package com.example.dungeoncrawler.model;

import android.util.Log;

import com.example.dungeoncrawler.R;

import java.util.ArrayList;


public class Player extends Character {

    public int experience;

    public Tile target;

    public boolean readyForLevel;

    public Player(Race race, Caste caste, int[] attPoints) {

        super(race, caste);
        this.myChar = 'P';
        this.sprite = R.drawable.player_large;
        nextLevelXp = (int) (Math.pow((this.level * scale), 1.2)) * xpBase;

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

    public void setTarget(Tile target) { this.target = target; }

    public String move(Tile[][] map) {
        String ret = "";
        Tile tLoc = this.target;
        Tile cLoc = this.location;

        cLoc.occupant = null;

        int xDiff = tLoc.x - cLoc.x;
        int yDiff = tLoc.y - cLoc.y;

        if (Math.abs(xDiff) > Math.abs(yDiff)) {
            if (xDiff > 0) {
                cLoc = map[cLoc.x+1][cLoc.y];
                ret += "s";
            }
            else {
                cLoc = map[cLoc.x-1][cLoc.y];
            ret += "w";
            }
        } else if (yDiff > 0) {
            cLoc = map[cLoc.x][cLoc.y+1];
            ret += "d";
        }
        else {
            cLoc = map[cLoc.x][cLoc.y-1];
            ret += "a";
        }

        // occupy new tile if it's available
        this.executeMove(cLoc);
        //map[cLoc.x][cLoc.y] = cLoc;
        return ret;
    }

    public void setAttunedSpell(Spell spell) {
        this.spell = spell;
    }

}