import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    Tile[][] map;

    char[][] mapDisplay;
    Tile randEnemy;
    final char avail = 'X';
    final char enemy = 'E';
    final char pc = 'C';

    public Game(int size) {
        TextUI ui = new TextUI();
        createSkills();
        createCastes();
        createRaces();
        // create a player character
        Player pc = ui.characterCreation();
        // create a map
        createMap(size);
        pc.occupy(map[0][0]);
        while (true) {
            System.out.print(ui.displayMap(map));
            move(pc);
        }
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
                        case "65": loc = map[i-1][j]; // if left move left
                            break;
                        case "87": loc = map[i][j-1]; // if up move up
                            break;
                        case "68": loc = map[i+1][j]; // if right move right
                            break;
                        case "83": loc = map[i][j+1]; // if down move down
                            break;
                    }
                }
            }
        }
        subject.location = loc;
    }

    public void createMap(int size) {
        this.map = new Tile[size][size];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = new Tile();
            }
        }
    }



    // SKILLS
    public static ArrayList<Skill> skills = new ArrayList<Skill>(11);
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
        this.skills.add(0, melee);
        this.skills.add(1, ranged);
        this.skills.add(2, spellcasting);
        this.skills.add(3, shield);
        this.skills.add(4, dodge);
        this.skills.add(5, armor);
        this.skills.add(6, invocation);
        this.skills.add(7, faith);
        this.skills.add(8, fireMagic);
        this.skills.add(9, earthMagic);
        this.skills.add(10, airMagic);
        this.skills.add(11, waterMagic);
    }

    // CASTES
    public static Caste[] castes = new Caste[6];
    public void createCastes() {
        this.castes[0] = Gladiator();
        this.castes[1] = Urchin();
        this.castes[2] = Woodsman();
        this.castes[3] = Fisherman();
        this.castes[4] = Apprentice();
        this.castes[5] = Clergyman();
    }

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
        woodSkills.add(skills.get(4)); //dodge
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
    public static Race[] races = new Race[7];
    public void createRaces() {
        this.races[0] = Human();
        this.races[1] = Minotaur();
        this.races[2] = Spriggan();
        this.races[3] = Dwarf();
        this.races[4] = Nymph();
        this.races[5] = Orc();
        this.races[6] = Kenku();
    }
    // Human
    static ArrayList<Skill> humSkills = new ArrayList<Skill>();
    static int[] humAtt = {2,2,3,1,9};

    public static Race Human() {
        humSkills.add(skills.get(1)); //ranged
        humSkills.add(skills.get(7)); //faith
        humSkills.add(skills.get(4)); //dodge
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
        sprSkills.add(skills.get(1)); //ranged
        sprSkills.add(skills.get(2)); //spellcasting
        sprSkills.add(skills.get(4)); //dodge
        sprSkills.add(skills.get(9)); //earthMagic

        Race spriggan = new Race(sprSkills, sprAtt, "Spriggan",
                "Spriggans are dexterous and nimble creatures that seem to be more plant than animal.");
        return spriggan;
    }

    // Nymph
    static ArrayList<Skill> nySkills = new ArrayList<Skill>();
    static int[] nyAtt = {1,3,4,2,6};

    public static Race Nymph() {
        nySkills.add(skills.get(4)); //dodge
        nySkills.add(skills.get(2)); //spellcasting
        nySkills.add(skills.get(11)); //waterMagic
        nySkills.add(skills.get(9)); //earthMagic

        Race nymph = new Race(nySkills, nyAtt, "Nymph",
                "Nymph's are furtive fae who specialize in the arcane.");
        return nymph;
    }

    // Orc
    static ArrayList<Skill> orcSkills = new ArrayList<Skill>();
    static int[] orcAtt = {4,2,1,3,9};

    public static Race Orc() {
        orcSkills.add(skills.get(0)); //melee
        orcSkills.add(skills.get(7)); //faith
        orcSkills.add(skills.get(3)); //shield
        orcSkills.add(skills.get(5)); //armor

        Race orc = new Race(orcSkills, orcAtt, "Orc",
                "Orcs are incredibly strong, though not terribly hearty, and usual quite devout.");
        return orc;
    }

    // Kenku
    static ArrayList<Skill> kSkills = new ArrayList<Skill>();
    static int[] kAtt = {2,4,2,2,6};

    public static Race Kenku() {
        kSkills.add(skills.get(1)); //ranged
        kSkills.add(skills.get(4)); //dodge
        kSkills.add(skills.get(6)); //invocation
        kSkills.add(skills.get(10)); //airMagic

        Race kenku = new Race(kSkills, kAtt, "Kenku",
                "Kenku resemble large crows and typically reside in the slums of large cities.");
        return kenku;
    }
}
