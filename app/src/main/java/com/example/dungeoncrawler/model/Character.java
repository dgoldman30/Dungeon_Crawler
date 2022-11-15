package com.example.dungeoncrawler.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class Character {

    public int level = 1;
    public Race race;
    public Caste caste;
    char myChar;
    public Tile location;
    Spell attunedSpell;

    public int maxHP;

    public int x;
    public int y;

    //equipment
    public Weapon weapon;
    Item head;
    Item hands;
    Item body;
    Item neck;
    Item feet;

    List<Item> inventory = new ArrayList<>();
    public Attribute STR = new Attribute("strength");
    public Attribute DEX = new Attribute("dexterity");
    public Attribute INT = new Attribute("intelligence");
    public Attribute WILL = new Attribute("willpower");
    public Attribute HP = new Attribute("hitpoints");
    public Attribute DV = new Attribute("dodge-value");
    public Attribute AV = new Attribute("armor-value");
    public Attribute MV = new Attribute("mental-value");
    public Attribute[] attributes = {STR, DEX, INT, WILL, HP, DV, AV, MV};

    public static Map<String, Skill> skills = new HashMap(Game.skills);


    Character(){}
    Character(Race race, Caste caste) {
        this.race = race;
        this.caste = caste;
        this.level = 1;

        // increment aptitude for favorite caste and race skills
        for (int i = 0; i < this.race.favoredSkills.length; i++) {
            this.skills.get(this.race.favoredSkills[i]).aptitude++;
        }
        for (int i = 0; i < this.caste.favoredSkills.length; i++) {
            this.skills.get(this.caste.favoredSkills[i]).aptitude++;
        }

        // add the race attributes
        for (int i = 0; i < 5; i++) {
            attributes[i].value += race.attributeAdjustments[i];
        }

        maxHP = attributes[4].value;

        // dodge value, armor value, mental value
        this.attributes[5].value += attributes[1].value + skills.get("Dodge").value;
        this.attributes[6].value += (int) ((attributes[3].value / 3) + skills.get("Armor").value);
        this.attributes[7].value += (attributes[3].value + (attributes[2].value * 2) + skills.get("Spellcasting").value) / 3;

        for (int i = 0; i < this.caste.startingItems.size(); i++) {
            if (this.caste.startingItems.get(i).getClass() == Weapon.class) {
                this.equipWeapon((Weapon) this.caste.startingItems.get(i));
            }
            if (this.caste.startingItems.get(i).getClass() == Armor.class) this.body = this.caste.startingItems.get(i);
            else this.inventory.add(this.caste.startingItems.get(i));
        }
    }

    public Tile[][] move(Tile[][] map) {return map;}

    public void occupy(Tile tile) {
        tile.display = this.myChar;
        tile.occupant = this;
        tile.available = false;
        this.location = tile;
        this.location.display();

        this.x = tile.x;
        this.y = tile.y;
    }

    public void executeMove(Tile tile) {
        if (tile.available) {
            this.location.occupant = null;
            this.location.available = true;
            this.occupy(tile);
        }
    }

    public void remove(Tile tile) {
        tile.occupant = null;
        tile.available = true;
        tile.display();
        this.location = null;
    }

    public void equipWeapon(Weapon weapon) {
        if (this.weapon != null) {
            inventory.add(this.weapon);
            this.weapon = weapon;
        } else this.weapon = weapon;
    }

    public void drinkPotion(Potion pot) {
        pot.drink(this);
    }
}