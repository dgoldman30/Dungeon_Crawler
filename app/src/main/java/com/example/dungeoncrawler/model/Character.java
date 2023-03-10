package com.example.dungeoncrawler.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
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

    public int manaPoints;
    public int maxMP;

    //equipment
    public Weapon weapon;
    public Armor body;
    public Potion potion;
    public Spell spell;
    public SpellEffect activeEffect;

    public ArrayList<Spell> knownSpells = new ArrayList<Spell>();

    public int nextLevelXp;
    int xpBase = 3;
    double scale = 10;

    public List<Item> inventory = new ArrayList<>();
    public Attribute STR = new Attribute("strength");
    public Attribute DEX = new Attribute("dexterity");
    public Attribute INT = new Attribute("intelligence");
    public Attribute WILL = new Attribute("willpower");
    public Attribute HP = new Attribute("hitpoints");
    public Attribute DV = new Attribute("dodge-value");
    public Attribute AV = new Attribute("armor-value");
    public Attribute MV = new Attribute("mental-value");
    public Attribute toHit = new Attribute("to-hit");
    public Attribute toBlock = new Attribute("to-block");
    public Attribute[] attributes = {STR, DEX, INT, WILL, HP, DV, AV, MV, toHit, toBlock};

    public Map<String, Skill> skills = new Hashtable<>();

    public String name;

    Character(){}
    Character(Race race, Caste caste) {
        this.race = race;
        this.caste = caste;
        this.name = race.name() + " " + caste.name();
        this.level = 1;

        // increment aptitude for favorite caste and race skills
        for (Map.Entry<String, Skill> entry : Game.skills.entrySet()) {
            this.skills.put(entry.getKey(), entry.getValue());
            for (String s : this.race.favoredSkills) {
                if (entry.getKey().equals(s)) {
                    this.skills.get(s).aptitude++;
                    this.skills.get(s).value++;
                }
            }
            for (String s : this.caste.favoredSkills) {
                if (entry.getKey().equals(s)) {
                    this.skills.get(s).aptitude++;
                    this.skills.get(s).value++;
                }
            }
        }

        calcStats();

        // add the race attributes
        for (int i = 0; i < 5; i++) {
            attributes[i].value += race.attributeAdjustments[i];
        }

        maxHP = attributes[4].value;



        // equip starting weapon armor potion
        this.equip((Weapon) this.caste.startingItems.get(0));
        this.equip((Armor) this.caste.startingItems.get(1));
        this.equip((Potion) this.caste.startingItems.get(2));

        for (int i = 3; i < caste.startingItems.size(); i++) { this.inventory.add(this.caste.startingItems.get(i)); }


    }

    public void calcStats() {
        // dodge value, armor value, mental value
        this.attributes[5].value += (int) attributes[1].value + (skills.get("Dodge").value / 3) ;
        this.attributes[6].value += (int) ((attributes[3].value / 2) + (skills.get("Armor").value / 3));
        this.attributes[7].value += (int) (attributes[3].value + (attributes[2].value * 2) + skills.get("Spellcasting").value) / 3;
        this.maxMP = this.attributes[7].value + this.level;
        this.manaPoints = maxMP;
    }

    public void xpIncrement() {
        nextLevelXp += (int) (Math.pow((this.level * scale), 1.2)) * xpBase;
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
            s.value += (int) (INT.value / 2) + s.aptitude;
        }
        maxMP += 1;
        manaPoints = maxMP;
        this.calcStats();
        return ret;
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

    public void setToHit() {
        attributes[8].value = (int) ((int) 1000 * Math.random() * (this.DEX.value + (this.INT.value / 2)));
    }

    public void setToBlock() {
        attributes[9].value = (int) ((int) 1000 * Math.random() * (this.skills.get("Shield").value + this.DEX.value + this.WILL.value));
    }

    public int toHit() { return attributes[8].value; }
    public int toBlock() { return attributes[9].value; }


    public boolean toDodge(double toHit) {
        if (toHit < (this.DV.value * (Math.random() * 10))) { return true; }
        return false;
    }

    public boolean toPenetrate(Character target) {
        double toPenetrate = 1000 * Math.random() * (this.STR.value + (this.skills.get("Melee").value / 3));
        double toResist = 1000 * Math.random() * (target.AV.value + target.body.AV);
        if (toPenetrate > toResist) { return true; }
        return false;
    }

    public void heal(int hp) {
        this.HP.value += hp;
        if (this.HP.value > this.maxHP) this.HP.value = this.maxHP;
    }

    public String drinkPotion() {
        String ret = "";
        if (this.potion != null) {
            ret += this.potion.drink(this);
        } else return "You don't have a potion equipped!";
        this.potion = null;
        return ret;
    }

    public void applyEffect(Spell spell) {
        activeEffect = spell.effect;
    }

    public void removeEffect() {
        this.activeEffect = null;
    }

    public String castSpell(Character target) {
        String ret = "";
        if (this.manaPoints > this.spell.mp) {
            this.manaPoints -= this.spell.mp;
            if (this.spell.self) {
                this.spell.resolve(this);
                ret += "You cast a life spell on yourself, increasing your " + this.attributes[this.spell.attribute].name + " by " + this.spell.effect.factor;
            } else if (this.MV.value + this.spell.mp > target.MV.value) {
                this.spell.resolve(target);
                ret += "You cast a death spell on the " + target.name + ", decreasing their " + target.attributes[this.spell.attribute].name + " by " + this.spell.effect.factor;
            } else ret += "Your spell missed the " + target.name;
        } else ret += "You don't have enough mana to cast this spell.";
        return ret;
    }

    public Attribute[] getAttributes() { return attributes; }
}