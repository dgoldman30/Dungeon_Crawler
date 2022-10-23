import java.util.*;
import java.awt.event.KeyEvent;

abstract class Character {

    int level;
    Race race;
    Caste caste;
    char myChar;
    Tile location;
    Spell attunedSpell;

    //equipment
    Item leftHand;
    Item rightHand;
    Item head;
    Item hands;
    Item body;
    Item neck;
    Item feet;

    ArrayList<Item> inventory = new ArrayList<>();
    Attribute STR = new Attribute("strength");
    Attribute DEX = new Attribute("dexterity");
    Attribute INT = new Attribute("intelligence");
    Attribute WILL = new Attribute("willpower");
    Attribute HP = new Attribute("hitpoints");
    Attribute DV = new Attribute("dodge-value");
    Attribute AV = new Attribute("armor-value");
    Attribute MV = new Attribute("mental-value");
    public Attribute[] attributes = {STR, DEX, INT, WILL, HP, DV, AV, MV};

    public static ArrayList<Skill> skills = new ArrayList<Skill>(Game.skills);



    Character(){}
    Character(Race race, Caste caste) {
        level = 0;

        this.race = race;
        this.caste = caste;

        // increment aptitude for favorite caste and race skills
        for (int i = 0; i < this.race.favoredSkills.size(); i++) {
            skills.get(skills.indexOf(this.race.favoredSkills.get(i))).aptitude++;
        }
        for (int i = 0; i < this.caste.favoredSkills.size(); i++) {
            skills.get(skills.indexOf(this.caste.favoredSkills.get(i))).aptitude++;
        }

        // add the race attributes
        for (int i = 0; i < 5; i++) {
            attributes[i].value += race.attributeAdjustments[i];
        }
        this.attributes[5].value += attributes[1].value + skills.get(4).value;
        this.attributes[6].value += (int) ((attributes[3].value / 3) + skills.get(5).value);
        this.attributes[7].value += (attributes[3].value + (attributes[2].value * 2) + skills.get(2).value) / 3;

        for (int i = 0; i < this.caste.startingItems.size(); i++) {
            if (this.caste.startingItems.get(i).getClass() == Weapon.class) {
                this.equipWeapon((Weapon) this.caste.startingItems.get(i));
            } else this.inventory.add(this.caste.startingItems.get(i));
        }
    }

    public Tile[][] move(Tile[][] map) {return map;}

    public void occupy(Tile tile) {
        tile.display = this.myChar;
        tile.occupant = this;
        tile.available = false;
        this.location = tile;
        this.location.display();
    }

    public void executeMove(Tile tile) {
        if (tile.available) {
            this.location.occupant = null;
            this.location.available = true;
            this.occupy(tile);
        }
    }

    public void equipWeapon(Weapon weapon) {
        if (weapon.large) {
            if (leftHand != null) inventory.add(leftHand);
            if (rightHand != null) inventory.add(rightHand);
            leftHand = weapon;
            rightHand = weapon;
        } else if (leftHand == null) {
            leftHand = weapon;
        }
        else if (rightHand == null) {
            rightHand = weapon;
        } else {
            inventory.add(leftHand);
            leftHand = weapon;
        }
    }

    public void drinkPotion(Potion pot) {
        pot.drink(this);
    }
}