import java.util.ArrayList;
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
    ArrayList<Item> equipment = new ArrayList<Item>();
    ArrayList<Item> inventory = new ArrayList<Item>();
    Attribute STR = new Attribute("strength");
    Attribute DEX = new Attribute("dexterity");
    Attribute INT = new Attribute("intelligence");
    Attribute WILL = new Attribute("willpower");
    Attribute HP = new Attribute("hitpoints");
    public Attribute[] attributes = {STR, DEX, INT, WILL, HP};

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
        for (int i = 0; i < attributes.length; i++) {
            attributes[i].value += race.attributeAdjustments[i];
        }

        this.dodgeValue += attributes[1].value + skills.get(4).value;
        this.armorValue += skills.get(5).value;
        this.mental += attributes[5].value + skills.get(2).value;

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

}