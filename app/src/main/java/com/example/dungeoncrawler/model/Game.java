package com.example.dungeoncrawler.model;

import java.util.Hashtable;
import java.util.Map;

public class Game {
    int gameState; // default state

    public enum GameStates {
        START,
        EXPLORE,
        COMBAT
    }
    public Tile[][] map;
    public static Player pc;
    public NPC enemy;
    public Game(int size) {
        createSkills();
        createMap(size);
    }

    public static void main(String[] args) {
        Game game = new Game(10);

        // create and place the pc
        //game.pc = ui.characterCreation();
        game.pc.occupy(game.map[0][0]);

        // create an enemy and place them on a random square
        game.enemy = new NPC(Race.values()[(int) Math.random()*7], Caste.GLADIATOR, true);
        game.enemy.occupy(game.map[(int) (Math.random() * game.map.length)][(int) (Math.random() * game.map.length)]);

        game.enemy.setTarget(game.pc);

        game.gameState++; // set game state to 1 - moving state

        /*while (true) {
            switch (game.gameState) {
                case 1:
                    ui.displayMap(game.map);
                    if (game.checkAdjacent()) { // if the enemy is in any adjacent tiles
                        game.gameState++; // set game state to 2 - combat state
                        break;
                    } else { ui.moving(game); }
                    break;
                case 2:
                    ui.displayMap(game.map);
                    ui.combat(game.pc, game.enemy);
                    break;
                case 3:
                    game.enemy = new NPC(Race.values()[(int) Math.random()*7], Caste.GLADIATOR, true);
                    game.enemy.occupy(game.map[(int) (Math.random() * game.map.length)][(int) (Math.random() * game.map.length)]);
                    game.enemy.setTarget(game.pc);
                    game.gameState = 1;
                    ui.displayMap(game.map);
                    break;
                case 10:
                    System.exit(0);
            }
        }*/

    }

    public boolean checkAdjacent() {
        int x = this.pc.location.x;
        int y = this.pc.location.y;
        int ex = this.enemy.location.x;
        int ey = this.enemy.location.y;
        if (Math.abs(x - ex) <= 1 && Math.abs(y - ey) <= 1) {
            return true;
        } else return false;
    }


    public void createMap(int size) {
        this.map = new Tile[size][size];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = new Tile(i, j);
            }
        }
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
    public static Item[] gladItems = {Weapon.Weapons.BRASSKNUCKLES.wn, Potion.Potions.HEALTH.po, Potion.Potions.STR.po, Armor.Armors.CHAIN.armor};


    // Urchin
    static String[] urSkills = {"Melee", "Ranged", "Dodge", "Invocation"};
    static Item[] urItems = {Weapon.Weapons.DAGGER.wn, Potion.Potions.DEX.po, Armor.Armors.RAGS.armor}; // sling and rags


    // Woodsman
    static String[] woodSkills = {"Ranged", "Dodge", "Faith", "Earth Magic"};
    static Item[] woodItems = {Weapon.Weapons.SABRE.wn, Potion.Potions.DEX.po, Potion.Potions.HEALTH.po, Armor.Armors.LEATHER.armor};
    // add bow
    // add leather armor

    // Fisherman
    static String[] fishSkills = {"Melee", "Spellcasting", "Air Magic", "Water Magic"};
    static Item[] fishItems = {Weapon.Weapons.HARPOON.wn, Potion.Potions.INT.po, Armor.Armors.CLOTHES.armor};
    // air spell
    // water spell
    // clothes

    // Apprentice
    static String[] appSkills = {"Spellcasting", "Fire Magic", "Air Magic", "Invocation"};
    static Item[] appItems = {Weapon.Weapons.CLUB.wn, Potion.Potions.INT.po, Armor.Armors.ROBES.armor};
    // fire spell
    // air spell

    // Clergyman
    static String[] clerSkills = {"Armor", "Shield", "Faith", "Water Magic"};
    static Item[] clerItems = {Weapon.Weapons.MACE.wn, Potion.Potions.WILL.po, Armor.Armors.PLATE.armor};
    // metal armor
    // shield
    // religious symbol
    // holy water scroll
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
    static String[] dwSkills  = {"Armor", "Faith", "Invocation", "Earth  Magic"};
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
