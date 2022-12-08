package com.example.dungeoncrawler.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Character {

    public int level = 1;
    public Race race;
    public Caste caste;
    char myChar;
    public Tile location;

    public int sprite;

    public int maxHP;

    public int x;
    public int y;

    //equipment
    public Weapon weapon;
    public Armor body;
    public Potion potion;
    Spell spell;

    public List<Item> inventory = new ArrayList<>();
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

    public String name;

    Character(){}
    Character(Race race, Caste caste) {
        this.race = race;
        this.caste = caste;
        this.name = race.name() + " " + caste.name();
        this.level = 1;

        // increment aptitude for favorite caste and race skills
        for (int i = 0; i < this.race.favoredSkills.length; i++) {
            skills.get(this.race.favoredSkills[i]).aptitude++;
            skills.get(this.race.favoredSkills[i]).value++;
        }
        for (int i = 0; i < this.caste.favoredSkills.length; i++) {
            skills.get(this.caste.favoredSkills[i]).aptitude++;
            skills.get(this.caste.favoredSkills[i]).value++;
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

        // equip starting weapon armor potion
        this.equip((Weapon) this.caste.startingItems.get(0));
        this.equip((Armor) this.caste.startingItems.get(1));
        this.equip((Potion) this.caste.startingItems.get(2));

        for (int i = 3; i < caste.startingItems.size(); i++) { this.inventory.add(this.caste.startingItems.get(i)); }


    }

    public void occupy(Tile tile) {
        tile.display = this.myChar;
        tile.occupant = this;
        tile.available = false;
        this.location = tile;

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

    public void equip(Item item) {
        // placeholder
        Item ret = Weapon.Weapons.BRASSKNUCKLES.wn;
        if (item instanceof Weapon) {
            ret = this.weapon;
            this.inventory.remove(item);
            this.weapon = (Weapon) item;
        }
        if (item instanceof Armor) {
            ret = this.body;
            this.inventory.remove(item);
            this.body = (Armor) item;
        }
        if (item instanceof Potion) {
            ret = this.potion;
            this.inventory.remove(item);
            this.potion = (Potion) item;
        }
        if (ret != null) { this.inventory.add(ret); }
    }

    public void drinkPotion(Potion pot) {
        pot.drink(this);
    }
}