import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class Game {
    Tile[][] map;
    char[][] mapDisplay;
    final char avail = 'X';
    final char enemy = 'E';
    final char pc = 'C';
    final char vertWall = '|';
    final char horWall = '—';

    // static arrays of castes and races
    public final static Caste[] castes = {Gladiator(), Urchin(), Woodsman(), Fisherman(), Apprentice(), Clergyman() };
    public final static Race[] races = {Human(), Minotaur(), Spriggan(), Dwarf(), Nymph(), Orc(), Kenku() };

    public Game() {
        TextUI ui = new TextUI();
        createSkills();
        // create a player character
        Player pc = ui.characterCreation();
        // create a map
        createMap(10);

        move(pc);
    }

    public void move(Character subject) {
        Tile curLoc = subject.location;
        Tile loc = curLoc;
        Scanner scanner = new Scanner(System.in);
        String keyCode = scanner.next();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == curLoc) {
                    switch (keyCode) {
                        case "37" -> loc = map[i-1][j]; // if left move left
                        case "38" -> loc = map[i][j-1]; // if up move up
                        case "39" -> loc = map[i+1][j]; // if right move right
                        case "40" -> loc = map[i][j+1]; // if down move down
                    }
                }
            }
        }
        subject.location = loc;
    }

    public void createMap(int size) {
        this.map = new Tile[size][size];
        this.mapDisplay = new char[size][size];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = new Tile(avail);
                /*
                Need to check if the Tile has a Character on it and display the appropriate char
                 */
                mapDisplay[i][j] = map[i][j].display; //create the visual field
            }
        }
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                ret += mapDisplay[i][j] + "  ";
                if (j == map.length - 1) {
                    ret += "\n";
                }
            }
        }
        return ret;
    }

    // SKILLS
    public static ArrayList<Skill> skills = new ArrayList<Skill>();
    public void createSkills() {
        ArrayList<Skill> skills = new ArrayList<Skill>();
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

        skills.add(melee);skills.add(ranged);skills.add(spellcasting);skills.add(shield);skills.add(dodge);skills.add(armor);
        skills.add(invocation);skills.add(faith);skills.add(fireMagic);skills.add(earthMagic);skills.add(airMagic);skills.add(waterMagic);
        this.skills = skills;
    }

    // CASTES
    // Gladiator
    static ArrayList<Skill> gladSkills = new ArrayList<Skill>(4);
    static ArrayList<Item> gladItems = new ArrayList<Item>();
    public static Caste Gladiator() {
        gladSkills.add(skills.get(0)); //melee
        gladSkills.add(skills.get(3)); //shield
        gladSkills.add(skills.get(4)); //dodge
        gladSkills.add(skills.get(5)); //armor

        Caste gladiator = new Caste(gladSkills, gladItems, "Gladiator",
                "The gladiator is a perennial warrior, never wandering far from their weapon.");
        return gladiator;
    }

    // Urchin
    static ArrayList<Skill> urSkills = new ArrayList<Skill>(4);
    static ArrayList<Item> urItems = new ArrayList<Item>();
    public static Caste Urchin() {
        urSkills.add(skills.get(0)); //melee
        urSkills.add(skills.get(1)); //ranged
        urSkills.add(skills.get(4)); //dodge
        urSkills.add(skills.get(6)); //invocation

        Caste urchin = new Caste(urSkills, urItems, "Urchin",
                "An urchin lives life on the streets, clinging to shadows and surviving by sheer luck.");
        return urchin;
    }

    // Woodsman
    static ArrayList<Skill> woodSkills = new ArrayList<Skill>(4);
    static ArrayList<Item> woodItems = new ArrayList<Item>();
    public static Caste Woodsman() {
        woodSkills.add(skills.get(1)); //ranged
        woodSkills.add(skills.get(3)); //dodge
        woodSkills.add(skills.get(7)); //faith
        woodSkills.add(skills.get(9)); //earthMagic

        Caste woodsman = new Caste(woodSkills, woodItems, "Woodsman",
                "A woodsman lives by hunting and foraging in the forest.");
        return woodsman;
    }

    // Fisherman
    static ArrayList<Skill> fishSkills = new ArrayList<Skill>(4);
    static ArrayList<Item> fishItems = new ArrayList<Item>();
    public static Caste Fisherman() {
        fishSkills.add(skills.get(0)); //melee
        fishSkills.add(skills.get(2)); //spellcasting
        fishSkills.add(skills.get(10)); //airMagic
        fishSkills.add(skills.get(11)); //waterMagic

        Caste fisherman = new Caste(fishSkills, fishItems, "Fisherman",
                "A fisherman spends their whole life at sea, becoming one with the winds and waters around them");
        return fisherman;
    }

    // Apprentice
    static ArrayList<Skill> appSkills = new ArrayList<Skill>(4);
    static ArrayList<Item> appItems = new ArrayList<Item>();
    public static Caste Apprentice() {
        appSkills.add(skills.get(2)); //spellcasting
        appSkills.add(skills.get(8)); //fireMagic
        appSkills.add(skills.get(10)); //airMagic
        appSkills.add(skills.get(6)); //invocation

        Caste apprentice = new Caste(appSkills, appItems, "Apprentice",
                "An apprentice of a wizened wizard setting out on their own.");
        return apprentice;
    }

    // Clergyman
    static ArrayList<Skill> clerSkills = new ArrayList<Skill>(4);
    static ArrayList<Item> clerItems = new ArrayList<Item>();
    public static Caste Clergyman() {
        clerSkills.add(skills.get(5)); //armor
        clerSkills.add(skills.get(3)); //shield
        clerSkills.add(skills.get(7)); //faith
        clerSkills.add(skills.get(11)); //waterMagic

        Caste clergyman = new Caste(clerSkills, clerItems, "Clergyman",
                "A clergyman set out on a holy pilgrimage.");
        return clergyman;
    }

    // RACES
    // Human
    static ArrayList<Skill> humSkills = new ArrayList<Skill>();
    static int[] humAtt = {2,2,3,1,9};

    public static Race Human() {
        humSkills.add(skills.get(1)); //ranged
        humSkills.add(skills.get(7)); //faith
        humSkills.add(skills.get(3)); //dodge
        humSkills.add(skills.get(6)); //invocation

        Race human = new Race(humSkills, humAtt, "Human",
                "Humans are unremarkable physically, however, they are rather intelligent despite their weak.");
        return human;
    }

    // Minotaur
    static ArrayList<Skill> minSkills = new ArrayList<Skill>();
    static int[] minAtt = {4,2,1,1,10};

    public static Race Minotaur() {
        minSkills.add(skills.get(0)); //melee
        minSkills.add(skills.get(5)); //armor
        minSkills.add(skills.get(3)); //shield
        minSkills.add(skills.get(8)); //fireMagic

        Race minotaur = new Race(minSkills, minAtt, "Minotaur",
                "Half-man, half-bull, minotaur are ferociously strong, though rather dull of mind");
        return minotaur;
    }

    // Dwarf
    static ArrayList<Skill> dwSkills  = new ArrayList<Skill>();
    static int[] dwAtt = {3,1,2,4,12};

    public static Race Dwarf() {
        dwSkills.add(skills.get(5)); //armor
        dwSkills.add(skills.get(7)); //faith
        dwSkills.add(skills.get(6)); //invocation
        dwSkills.add(skills.get(9)); //earthMagic

        Race dwarf = new Race(dwSkills, dwAtt, "Dwarf",
                "Dwarves are stocky and possess remarkable fortitude of will along with commendable strength");
        return dwarf;
    }

    // Spriggan
    static ArrayList<Skill> sprSkills = new ArrayList<Skill>();
    static int[] sprAtt = {1,4,3,2,7};

    public static Race Spriggan() {
        sprSkills.add(ranged);
        sprSkills.add(spellcasting);
        sprSkills.add(dodge);
        sprSkills.add(earthMagic);

        Race spriggan = new Race(sprSkills, sprAtt, "Spriggan",
                "Spriggans are dexterous and nimble creatures that seem to be more plant than animal.");
        return spriggan;
    }

    // Nymph
    static ArrayList<Skill> nySkills = new ArrayList<Skill>();
    static int[] nyAtt = {1,3,4,2,6};

    public static Race Nymph() {
        nySkills.add(dodge);
        nySkills.add(spellcasting);
        nySkills.add(waterMagic);
        nySkills.add(earthMagic);

        Race nymph = new Race(nySkills, nyAtt, "Nymph",
                "Nymph's are furtive fae who specialize in the arcane.");
        return nymph;
    }

    // Orc
    static ArrayList<Skill> orcSkills = new ArrayList<Skill>();
    static int[] orcAtt = {4,2,1,3,9};

    public static Race Orc() {
        orcSkills.add(melee);
        orcSkills.add(faith);
        orcSkills.add(shield);
        orcSkills.add(armor);

        Race orc = new Race(orcSkills, orcAtt, "Orc",
                "Orcs are incredibly strong, though not terribly hearty, and usual quite devout.");
        return orc;
    }

    // Kenku
    static ArrayList<Skill> kSkills = new ArrayList<Skill>();
    static int[] kAtt = {2,4,2,2,6};

    public static Race Kenku() {
        kSkills.add(ranged);
        kSkills.add(dodge);
        kSkills.add(invocation);
        kSkills.add(airMagic);

        Race kenku = new Race(kSkills, kAtt, "Kenku",
                "Kenku resemble large crows and typically reside in the slums of large cities.");
        return kenku;
    }
}
