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

    public Attribute STR = new Attribute("strength");
    public Attribute DEX = new Attribute("dexterity");
    public Attribute INT = new Attribute("intelligence");
    public Attribute WILL = new Attribute("willpower");
    public Attribute HP = new Attribute("hitpoints");

    public Skill melee = new Skill("Melee", "Effects the character's accuracy and damage with melee weapons");
    public Skill ranged = new Skill("Ranged", "Effects the character's accuracy and damage with ranged weapons");
    public Skill spellcasting = new Skill("Spellcasting", "Effects the character's success rate with magical effects");
    public Skill shield = new Skill("Shield", "Effects the character's effectiveness blocking attacks");
    public Skill dodge = new Skill("Dodge", "Effects the character's dodge value (DV)");
    public Skill armor = new Skill("Armor", "Effects the character's armor value (AV) and their ability to wear heavier armors");
    public Skill invocation = new Skill("Invocation", "Effects the character's ability to successfully use magical items");
    public Skill faith = new Skill("Faith", "Effects the character's divine abilities associated with their deity");
    public Skill fireMagic = new Skill("Fire Magic", "Effects the character's ability with fire magic");
    public Skill earthMagic = new Skill("Earth Magic", "Effects the character's ability with earth magic");
    public Skill airMagic = new Skill("Air Magic", "Effects the character's ability with air magic");
    public Skill waterMagic = new Skill("Water Magic", "Effects the character's ability with water magic");

    Character(){}
    Character(Race race, Caste caste) {
        level = 0;

        this.race = race;
        this.caste = caste;

        this.skills.add(melee);
        this.skills.add(ranged);
        this.skills.add(spellcasting);
        this.skills.add(shield);
        this.skills.add(dodge);
        this.skills.add(armor);
        this.skills.add(invocation);
        this.skills.add(faith);
        this.skills.add(fireMagic);
        this.skills.add(earthMagic);
        this.skills.add(airMagic);
        this.skills.add(waterMagic);

        this.attributes.add(STR);
        this.attributes.add(DEX);
        this.attributes.add(INT);
        this.attributes.add(WILL);
        this.attributes.add(HP);
    }
}