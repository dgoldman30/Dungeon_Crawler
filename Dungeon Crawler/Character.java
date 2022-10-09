import java.util.ArrayList;
abstract class Character {

    int level;
    Race race;
    Caste caste;
    public static ArrayList<Attribute> attributes = new ArrayList<Attribute>(5);
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

        Attribute STR = new Attribute("strength");
        Attribute DEX = new Attribute("dexterity");
        Attribute INT = new Attribute("intelligence");
        Attribute WILL = new Attribute("willpower");
        Attribute HP = new Attribute("hitpoints");

        this.attributes.add(STR);
        this.attributes.add(DEX);
        this.attributes.add(INT);
        this.attributes.add(WILL);
        this.attributes.add(HP);

        // increment aptitude for favorite caste and race skills
        for (int i = 0; i < this.race.favoredSkills.size(); i++) {
            this.skills.get(this.skills.indexOf(this.race.favoredSkills.get(i))).aptitude++;
        }
        for (int i = 0; i < this.caste.favoredSkills.size(); i++) {
            this.skills.get(this.skills.indexOf(this.caste.favoredSkills.get(i))).aptitude++;
        }

        // add the race attributes
        for (int i = 0; i < attributes.size(); i++) {
            this.attributes.get(i).value += race.attributeAdjustments[i];
        }
    }
}