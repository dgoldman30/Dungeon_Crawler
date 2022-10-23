import java.util.*;
import java.awt.event.KeyEvent;

abstract class Character {

    int level;
    Race race;
    Caste caste;
    char myChar;
    Tile location;
    Spell attunedSpell;
    int dodgeValue;
    int armorValue;
    int mental;
    Hashtable<String, Item> equipment = new Hashtable<String, Item>(7);

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
            this.inventory.add(this.caste.startingItems.get(i));
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
//    public void attack(Character target) {
//        this.location.display = 'F';
//        if (((Math.random() * 10) + attributes[0].value)  > target.armorValue) {
//            attributes[4].value -= attributes[0].value;
//            System.out.println("Damage: " + attributes[4].value);
//        }
//    }

    public void executeMove(Tile tile) {
        if (tile.available) {
            this.location.occupant = null;
            this.location.available = true;
            this.occupy(tile);
        }
    }

    public void setSlots() {
        equipment.put("head", null);
        equipment.put("body", null);
        equipment.put("hands", null);
        equipment.put("feet", null);
        equipment.put("neck", null);
        equipment.put("left", null);
        equipment.put("right", null);
    }

    public void autoEquip() {
        Iterator<Item> itr = inventory.iterator();

    }

    public void equipWeapon(Weapon weapon) {
        if (weapon.large) {
            equipment.put("left", weapon);
            equipment.put("right", weapon);
        } else if (equipment.get("left") == null) { equipment.put("left", weapon); }
        else equipment.put("right", weapon);
    }

    public void drinkPotion(Potion pot) {
        pot.drink(this);
    }
}