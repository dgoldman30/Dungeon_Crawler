package com.example.dungeoncrawler.model;

import android.util.Log;

import java.util.ArrayList;


public class Player extends Character {

    public int experience;
    public int nextLevelXp;

    int xpBase = 3;
    double scale = 10;


    public boolean readyForLevel;

    public void xpIncrement() {
        nextLevelXp += (int) (Math.pow((this.level * scale), 1.2)) * xpBase;
    }

    public Player(Race race, Caste caste, int[] attPoints) {

        super(race, caste);
        this.myChar = 'P';
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

    public void setAttunedSpell(Spell spell) {
        this.spell = spell;
    }

    public void equip(Weapon weapon) {
    }

    public ArrayList<Item> search() {
        ArrayList<Item> items = new ArrayList<>(this.location.contents);
        return items;
    }

    public String levelUp() {
        level++;
        String ret = "You've leveled up to level " + level + ".\n" +
                "Your attributes each increase by 1, and you gain " + (level * 3) + " hitpoints.\n" +
                "Your skills gain value according to their aptitude.\n" +
                "View your character sheet to see your stat increases.";
        xpIncrement();
        for (Attribute a : attributes) {
            a.value++;
            if (a.name.equals("hitpoints")) {
                a.value += level * 3;
                maxHP += level * 3;
            }
        }
        for (Skill s : skills.values()) {
            s.value += (INT.value / 3) * s.aptitude;
        }
        return ret;
    }




}