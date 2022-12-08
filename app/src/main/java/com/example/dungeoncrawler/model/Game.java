package com.example.dungeoncrawler.model;

import java.util.Hashtable;
import java.util.Map;

public class Game {

    public int depth;
    public int mapSize = 10;

    public enum GameStates {
        START,
        EXPLORE,
        CLEARED
    }
    public GameStates gameState;

    public int enemiesCleared;

    public Floor floor;

    public Tile[][] map;
    public Player pc;
    public NPC enemy;

    public Game(int size) {
        mapSize = size;
        createSkills();
        depth = 1;
        createMap(size, size);
    }

    public void setCharacters(Player pc, NPC enemy) {
        this.pc = pc;
        this.enemy = enemy;
    }

    public void createMap(int sizeX, int sizeY) {
        this.map = new Tile[sizeX][sizeY];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = new Tile(i, j);
            }
        }
    }

    public boolean checkAdjacent() {
        int x = this.pc.location.x;
        int y = this.pc.location.y;
        int ex = this.enemy.location.x;
        int ey = this.enemy.location.y;
        return Math.abs(x - ex) <= 1 && Math.abs(y - ey) <= 1;
    }




    // SKILLS

    // change to a map indexed by name. Also move to an enum in Skills class
    public static Map<String, Skill> skills = new Hashtable<>();
    public static void createSkills() {
        Skill melee = new Skill("Melee", "Effects the character's accuracy and damage with melee weapons");
        Skill ranged = new Skill("Ranged", "Effects the character's accuracy and damage with ranged weapons");
        Skill spellcasting = new Skill("Spellcasting", "Effects the character's success rate with magical effects");
        Skill shield = new Skill("Shield", "Effects the character's effectiveness blocking attacks");
        Skill dodge = new Skill("Dodge", "Effects the character's dodge value (DV)");
        Skill armor = new Skill("Armor", "Effects the character's armor value (AV) and their ability to wear heavier armors");
        Skill invocation = new Skill("Invocation", "Effects the character's ability to successfully use magical items");
        Skill faith = new Skill("Faith", "Effects the character's divine abilities associated with their deity");
        Skill fireMagic = new Skill("Fire Magic", "Effects the character's ability with fire magic");
        Skill earthMagic = new Skill("Earth Magic", "Effects the character's ability with earth magic");
        Skill airMagic = new Skill("Air Magic", "Effects the character's ability with air magic");
        Skill waterMagic = new Skill("Water Magic", "Effects the character's ability with water magic");
        skills.put(melee.name, melee);
        skills.put(ranged.name, ranged);
        skills.put(spellcasting.name, spellcasting);
        skills.put(shield.name, shield);
        skills.put(dodge.name, dodge);
        skills.put(armor.name, armor);
        skills.put(invocation.name, invocation);
        skills.put(faith.name, faith);
        skills.put(fireMagic.name, fireMagic);
        skills.put(earthMagic.name, earthMagic);
        skills.put(airMagic.name, airMagic);
        skills.put(waterMagic.name, waterMagic);
    }



    // CASTES
    // Gladiator
    public static String[] gladSkills = {"Melee", "Shield", "Dodge", "Armor"};
    public static Item[] gladItems = {Weapon.Weapons.BRASSKNUCKLES.wn, Armor.Armors.CHAIN.armor, Potion.Potions.HEALTH.po, Potion.Potions.STR.po};


    // Urchin
    static String[] urSkills = {"Melee", "Ranged", "Dodge", "Invocation"};
    static Item[] urItems = {Weapon.Weapons.DAGGER.wn, Armor.Armors.RAGS.armor, Potion.Potions.DEX.po}; // sling and rags


    // Woodsman
    static String[] woodSkills = {"Ranged", "Dodge", "Faith", "Earth Magic"};
    static Item[] woodItems = {Weapon.Weapons.SABRE.wn,  Armor.Armors.LEATHER.armor, Potion.Potions.DEX.po, Potion.Potions.HEALTH.po};
    // add bow
    // add leather armor

    // Fisherman
    static String[] fishSkills = {"Melee", "Spellcasting", "Air Magic", "Water Magic"};
    static Item[] fishItems = {Weapon.Weapons.HARPOON.wn, Armor.Armors.CLOTHES.armor, Potion.Potions.INT.po,};
    // air spell
    // water spell
    // clothes

    // Apprentice
    static String[] appSkills = {"Spellcasting", "Fire Magic", "Air Magic", "Invocation"};
    static Item[] appItems = {Weapon.Weapons.CLUB.wn,  Armor.Armors.ROBES.armor, Potion.Potions.INT.po,};
    // fire spell
    // air spell

    // Clergyman
    static String[] clerSkills = {"Armor", "Shield", "Faith", "Water Magic"};
    static Item[] clerItems = {Weapon.Weapons.MACE.wn, Armor.Armors.PLATE.armor, Potion.Potions.WILL.po};
    // metal armor
    // shield
    // religious symbol
    // holy water scroll

    //Test
    static String[] testSkills = clerSkills;
    static Item[] testItems = {Weapon.Weapons.SABRE.wn, Armor.Armors.LEATHER.armor, Potion.Potions.STR.po, Potion.Potions.WILL.po, Potion.Potions.INT.po,
            Potion.Potions.DEX.po, Potion.Potions.HEALTH.po, Armor.Armors.PLATE.armor, Weapon.Weapons.CLUB.wn,
            Armor.Armors.CHAIN.armor};

    // RACE
    // change to enum in Race class
    public static Race[] races = new Race[7];
    // Human
    static String[] humSkills = {"Ranged", "Faith", "Dodge", "Invocation"};
    static int[] humAtt = {2,2,3,1,9};

    // Minotaur
    static String[] minSkills = {"Melee", "Armor", "Shield", "Fire Magic"};
    static int[] minAtt = {4,2,1,1,10};

    // Spriggan
    static String[] sprSkills = {"Ranged", "Spellcasting", "Dodge", "Earth Magic"};
    static int[] sprAtt = {1,4,3,2,7};

    // Dwarf
    static String[] dwSkills  = {"Armor", "Faith", "Invocation", "Earth Magic"};
    static int[] dwAtt = {3,1,2,4,12};

    // Nymph
    static String[] nySkills = {"Dodge", "Spellcasting", "Water Magic", "Earth Magic"};
    static int[] nyAtt = {1,3,4,2,6};

    // Orc
    static String[] orcSkills = {"Melee", "Faith", "Shield", "Armor"};
    static int[] orcAtt = {4,2,1,3,9};

    // Kenku
    static String[] kSkills = {"Ranged", "Dodge", "Invocation", "Air Magic"};
    static int[] kAtt = {2,4,2,2,6};
}
