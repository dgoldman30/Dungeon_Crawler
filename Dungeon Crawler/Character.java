import java.util.ArrayList;
abstract class Character {

    int level;
    Race race;
    Caste caste;
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
    public static ArrayList<Skill> skills = new ArrayList<Skill>();
    ArrayList<Item> equipment = new ArrayList<Item>();
    ArrayList<Item> inventory = new ArrayList<Item>();
    Tile location;
    Spell attunedSpell;

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
    }
}