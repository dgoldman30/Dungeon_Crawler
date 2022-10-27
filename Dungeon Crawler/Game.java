import java.sql.Array;
import java.util.*;

public class Game {
    int gameState; // default state
    Tile[][] map;
    static Player pc;
    NPC enemy;
    public Game(int size) {
        createSkills();
        createWeapons();
        createPotions();
        createMap(size);
    }

    public static void main(String[] args) {
        Game game = new Game(10);
        TextUI ui = new TextUI(game);

        // create and place the pc
        game.pc = ui.characterCreation();
        game.pc.occupy(game.map[0][0]);

        // create an enemy and place them on a random square
        game.enemy = new NPC(races[0], Caste.GLADIATOR, true);
        game.enemy.occupy(game.map[(int) (Math.random() * game.map.length)][(int) (Math.random() * game.map.length)]);

        game.enemy.setTarget(game.pc);

        game.gameState++; // set game state to 1 - moving state

        while (true) {
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
                    game.enemy = new NPC(races[0], Caste.GLADIATOR, true);
                    game.enemy.occupy(game.map[(int) (Math.random() * game.map.length)][(int) (Math.random() * game.map.length)]);
                    game.enemy.setTarget(game.pc);
                    game.gameState = 1;
                    ui.displayMap(game.map);
                    break;
                case 10:
                    System.exit(0);
            }
        }

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

    //WEAPONS
    // move to enum in Weapon class
    public static Weapon[] weapons = new Weapon[9];
    public void createWeapons() {
        Weapon knife = new Weapon("Knife", "High-grade damascus blade perfect for slicing enemies!", 0.4, 0.9,0.2, false);
        Weapon sword = new Weapon("Sword", "Basic lightweight sword", 0.7, 0.9, 0.1, false);
        Weapon hammer = new Weapon("Hammer", "Perfectly weighted Hammer, best for heavy attacks", 1, 0.7, 0.05,  true);
        Weapon ballAndChain = new Weapon("Ball and Chain", "The ole' ball and chain", 0.7, 0.8, 0.15, true);
        Weapon club = new Weapon("Club", "Heavy club", 0.9, 0.8, 0.02, false);
        Weapon dagger = new Weapon("Dagger", "Lightweight Dagger for quick attacks", 0.3, 1.0, 0.4, false);
        Weapon sabre = new Weapon("Sabre", "Fence your enemy to the death!", 0.7, 0.85, 0.2, false);
        Weapon harpoon = new Weapon("Harpoon", "Are you trying to kill a whale?", 0.7, 0.8, 0.3, true);
        Weapon brassKnuckles = new Weapon("Brass Knuckles", "Wearable Brass Knuckles for critical attacks", 0.5, 0.9, 0.5, true);
        weapons[0] = knife;//0
        weapons[1] = sword;//1
        weapons[2] = hammer;//2
        weapons[3] = ballAndChain;//3
        weapons[4] = club;//4
        weapons[5] = dagger;//5
        weapons[6] = sabre;//6
        weapons[7] = harpoon;//7
        weapons[8] = brassKnuckles;//8
    }

    // POTIONS
    // change to Enum in Potion class
    public static Potion[] potions = new Potion[5];
    private void createPotions() {
        Potion healPo = new Potion("Healing Potion", "Heals most wounds and recovers HP ", 10, 4);
        Potion dexPo = new Potion("Dexerity Potion", "Increases Dexterity", 1, 1);
        Potion strPo = new Potion("Strength Potion", "Increases Strength", 1, 0);
        Potion intPo = new Potion("Intelligence Potion", "Increases brainpower and knowledge", 1, 2);
        Potion willPo = new Potion("Willpower Potion", "Increases Willpower", 1, 3);
        potions[0] = healPo;
        potions[1] = dexPo;
        potions[2] = strPo;
        potions[3] = intPo;
        potions[4] = willPo;
    }

    // CASTES
    // Gladiator
    public static String[] gladSkills = {"Melee", "Shield", "Dodge", "Armor"};
    public static Item[] gladItems = {Game.weapons[1], Game.potions[0], Game.potions[2]};


    // Urchin
    static String[] urSkills = {"Melee", "Ranged", "Dodge", "Invocation"};
    static Item[] urItems = {Game.weapons[0], Game.potions[1]}; // sling and rags


    // Woodsman
    static String[] woodSkills = {"Ranged", "Dodge", "Faith", "Earth Magic"};
    static Item[] woodItems = {Game.weapons[0], Game.potions[0], Game.potions[1]};
    // add bow
    // add leather armor

    // Fisherman
    static String[] fishSkills = {"Melee", "Spellcasting", "Air Magic", "Water Magic"};
    static Item[] fishItems = {Game.weapons[7], Game.potions[3]};
    // air spell
    // water spell
    // clothes

    // Apprentice
    static String[] appSkills = {"Spellcasting", "Fire Magic", "Air Magic", "Invocation"};
    static Item[] appItems = {Game.weapons[0], Game.potions[3]};
    // robes
    // fire spell
    // air spell

    // Clergyman
    static String[] clerSkills = {"Armor", "Shield", "Faith", "Water Magic"};
    static Item[] clerItems = {Game.weapons[2]};
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
