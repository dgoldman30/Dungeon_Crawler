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
        createRaces();
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
    public void createSkills() {
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
    public static Skill[] gladSkills = {Game.skills.get("Melee"), Game.skills.get("Shield"), Game.skills.get("Dodge"), Game.skills.get("Armor")};
    public static Item[] gladItems = {Game.weapons[1], Game.potions[0], Game.potions[2]};


    // Urchin
    static Skill[] urSkills = {Game.skills.get("Melee"), Game.skills.get("Ranged"), Game.skills.get("Dodge"), Game.skills.get("Invocation")};
    static Item[] urItems = {Game.weapons[0], Game.potions[1]}; // sling and rags


    // Woodsman
    static Skill[] woodSkills = {Game.skills.get("Ranged"), Game.skills.get("Dodge"), Game.skills.get("Faith"), Game.skills.get("Earth Magic")};
    static Item[] woodItems = {Game.weapons[0], Game.potions[0], Game.potions[1]};
    // add bow
    // add leather armor

    // Fisherman
    static Skill[] fishSkills = {Game.skills.get("Melee"), Game.skills.get("Spellcasting"), Game.skills.get("Air Magic"), Game.skills.get("Water Magic")};
    static Item[] fishItems = {Game.weapons[7], Game.potions[3]};
    // air spell
    // water spell
    // clothes

    // Apprentice
    static Skill[] appSkills = {Game.skills.get("Spellcasting"), Game.skills.get("Fire Magic"), Game.skills.get("Air Magic"), Game.skills.get("Invocation")};
    static Item[] appItems = {Game.weapons[0], Game.potions[3]};
    // robes
    // fire spell
    // air spell

    // Clergyman
    static Skill[] clerSkills = {Game.skills.get("Armor"), Game.skills.get("Shield"), Game.skills.get("Faith"), Game.skills.get("Water Magic")};
    static Item[] clerItems = {Game.weapons[2]};
    // metal armor
    // shield
    // religious symbol
    // holy water scroll
    // RACE
    // change to enum in Race class
    public static Race[] races = new Race[7];
    public void createRaces() {
        races[0] = Human();
        races[1] = Minotaur();
        races[2] = Spriggan();
        races[3] = Dwarf();
        races[4] = Nymph();
        races[5] = Orc();
        races[6] = Kenku();
    }
    // Human
    static List<Skill> humSkills = new ArrayList<Skill>();
    static int[] humAtt = {2,2,3,1,9};

    public static Race Human() {
        humSkills.add(skills.get("Ranged")); //ranged
        humSkills.add(skills.get("Faith")); //faith
        humSkills.add(skills.get("Dodge")); //dodge
        humSkills.add(skills.get("Invocation")); //invocation

        Race human = new Race(humSkills, humAtt, "Human",
                "Humans are unremarkable physically, however, they are rather intelligent despite their weak.");
        return human;
    }

    // Minotaur
    static List<Skill> minSkills = new ArrayList<Skill>();
    static int[] minAtt = {4,2,1,1,10};

    public static Race Minotaur() {
        minSkills.add(skills.get("Melee")); //melee
        minSkills.add(skills.get("Armor")); //armor
        minSkills.add(skills.get("Shield")); //shield
        minSkills.add(skills.get("Fire Magic")); //fireMagic

        Race minotaur = new Race(minSkills, minAtt, "Minotaur",
                "Half-man, half-bull, minotaur are ferociously strong, though rather dull of mind");
        return minotaur;
    }

    // Dwarf
    static List<Skill> dwSkills  = new ArrayList<Skill>();
    static int[] dwAtt = {3,1,2,4,12};

    public static Race Dwarf() {
        dwSkills.add(skills.get("Armor")); //armor
        dwSkills.add(skills.get("Faith")); //faith
        dwSkills.add(skills.get("Invocation")); //invocation
        dwSkills.add(skills.get("Earth Magic")); //earthMagic

        Race dwarf = new Race(dwSkills, dwAtt, "Dwarf",
                "Dwarves are stocky and possess remarkable fortitude of will along with commendable strength");
        return dwarf;
    }

    // Spriggan
    static List<Skill> sprSkills = new ArrayList<Skill>();
    static int[] sprAtt = {1,4,3,2,7};

    public static Race Spriggan() {
        sprSkills.add(skills.get("Ranged")); //ranged
        sprSkills.add(skills.get("Spellcasting")); //spellcasting
        sprSkills.add(skills.get("Dodge")); //dodge
        sprSkills.add(skills.get("Earth Magic")); //earthMagic

        Race spriggan = new Race(sprSkills, sprAtt, "Spriggan",
                "Spriggans are dexterous and nimble creatures that seem to be more plant than animal.");
        return spriggan;
    }

    // Nymph
    static List<Skill> nySkills = new ArrayList<Skill>();
    static int[] nyAtt = {1,3,4,2,6};

    public static Race Nymph() {
        nySkills.add(skills.get("Dodge")); //dodge
        nySkills.add(skills.get("Spellcasting")); //spellcasting
        nySkills.add(skills.get("Water Magic")); //waterMagic
        nySkills.add(skills.get("Earth Magic")); //earthMagic

        Race nymph = new Race(nySkills, nyAtt, "Nymph",
                "Nymph's are furtive fae who specialize in the arcane.");
        return nymph;
    }

    // Orc
    static List<Skill> orcSkills = new ArrayList<Skill>();
    static int[] orcAtt = {4,2,1,3,9};

    public static Race Orc() {
        orcSkills.add(skills.get("Melee")); //melee
        orcSkills.add(skills.get("Faith")); //faith
        orcSkills.add(skills.get("Shield")); //shield
        orcSkills.add(skills.get("Armor")); //armor

        Race orc = new Race(orcSkills, orcAtt, "Orc",
                "Orcs are incredibly strong, though not terribly hearty, and usual quite devout.");
        return orc;
    }

    // Kenku
    static List<Skill> kSkills = new ArrayList<Skill>();
    static int[] kAtt = {2,4,2,2,6};

    public static Race Kenku() {
        kSkills.add(skills.get("Ranged")); //ranged
        kSkills.add(skills.get("Dodge")); //dodge
        kSkills.add(skills.get("Invocation")); //invocation
        kSkills.add(skills.get("Air Magic")); //airMagic

        Race kenku = new Race(kSkills, kAtt, "Kenku",
                "Kenku resemble large crows and typically reside in the slums of large cities.");
        return kenku;
    }
}
