import java.util.Scanner;

class Race {

    Skill[] favoredSkills;
    Attribute[] baseAttributes;
    public Race[] races;
    String name;
    Race() {

    }
    // we're going to need to put this in TextUI. That will seperate text based UI and game logic
    public Race PickRace() {
        Race ret;
        System.out.println("Each Race has different set of favored Skills \n Please Select a Race from the list below");
        for (int i = 0; i < races.length; i++) {

            System.out.println(i + ": " + races[i]);
        }
        Scanner scanner = new Scanner(System.in);
        ret =  races[scanner.nextInt()];
        System.out.println("You have selected Race: " + ret);
        return ret;
    }
    Race human = new Race();

    /*
    Human
        Ranged
        Faith
        Dodge
        Invocation
        --
        2 STR
        2 DEX
        3 INT
        1 WILL
        9 HP
     */

    /*
    Minotaur
        Melee
        Armor
        Shield
        Fire magic
        --
        4 STR
        2 DEX
        1 INT
        1 WILL
        10 HP
     */

    /*
    Dwarf
        Armor
        Faith
        Invocation
        Earth magic
        --
        3 STR
        1 DEX
        2 INT
        4 WILL
        12 HP
     */

    /*
    Spriggan
        Ranged
        Spellcasting
        Dodge
        Earth magic
        --
        1 STR
        4 DEX
        3 INT
        2 WILL
        8 HP
     */

    /*
    Nymph
        Dodge
        Spellcasting
        Water magic
        Earth magic
        --
        1 STR
        3 DEX
        4 INT
        2 WILL
        7 HP
     */

    /*
    Orc
        Melee
        Faith
        Shield
        Armor
        --
        4 STR
        2 DEX
        1 INT
        3 WILL
        10 HP
     */

    /*
    Kenku
        Ranged
        Dodge
        Invocation
        Air magic
        --
        2 STR
        4 DEX
        2 INT
        2 WILL
        6 HP
     */
}