import java.util.ArrayList;
import java.awt.event.KeyEvent;
abstract class Character {

    int level;
    Race race;
    Caste caste;
    char myChar;
    public static ArrayList<Attribute> attributes = new ArrayList<Attribute>(5);
    static Attribute STR = new Attribute("strength");
    static Attribute DEX = new Attribute("dexterity");
    static Attribute INT = new Attribute("intelligence");
    static Attribute WILL = new Attribute("willpower");
    static Attribute HP = new Attribute("hitpoints");
    static {
        attributes.add(STR);
        attributes.add(DEX);
        attributes.add(INT);
        attributes.add(WILL);
        attributes.add(HP);
    }
    public static ArrayList<Skill> skills = new ArrayList<Skill>(Game.skills);
    ArrayList<Item> equipment = new ArrayList<Item>();
    ArrayList<Item> inventory = new ArrayList<Item>();
    Tile location;
    Spell attunedSpell;

    int dodgeValue;
    int armorValue;
    int mental;


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
        for (int i = 0; i < attributes.size(); i++) {
            attributes.get(i).value += race.attributeAdjustments[i];
        }

        this.dodgeValue += attributes.get(1).value + skills.get(4).value;
        this.armorValue += skills.get(5).value;
        this.mental += attributes.get(3).value + skills.get(2).value;

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
    public void attack(Character target) {
        if (((Math.random() * 10) + attributes.get(0).value)  > target.armorValue) {
            attributes.get(4).value -= attributes.get(0).value;
            System.out.println("Damage: " + attributes.get(4).value);
        }
    }

}