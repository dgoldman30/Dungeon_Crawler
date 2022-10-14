import java.util.Scanner;

public class  TextUI {
    Game game;

    TextUI(Game game) { this.game = game; }


    public Player characterCreation() {
        Player pc = new Player(pickRace(), pickCaste(), pickAtt());
        System.out.println("You have created a new " + pc.race.name + " " + pc.caste.name + "! Time to explore! Use the WASD keys to move around");
        return pc;
    }
    public Race pickRace() {
        Race race;
        System.out.println("Each race has different set of favored Skills \n Please Select a Race from the list below");
        for (int i = 0; i < Game.races.length; i++) {

            System.out.println(i + ": " + Game.races[i].name);
        }
        Scanner scanner = new Scanner(System.in);
        race =  Game.races[scanner.nextInt()];
        System.out.println("You have selected Race: " + race.name);
        return race;
    }
    public Caste pickCaste() {
        Caste caste;
        System.out.println("Each caste favored skills and a set of starting equipment \n Please select a caste from the list below");

        for (int i = 0; i < Game.castes.length; i++) {
            System.out.println(i + ": " + Game.castes[i].name);
        }
        Scanner scanner = new Scanner(System.in);
        caste = Game.castes[scanner.nextInt()];
        System.out.println("You have selected " + caste.name);
        return caste;
    }
    public int[] pickAtt() {
        int[] points = new int[5];
        System.out.println("You get to assign 4 additional attribute points to your character \n " +
                "Select 1 for STR, 2 for DEX, 3 for INT, and 4 for WILL");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            int ref = scanner.nextInt()-1;
            points[ref] += 1;
            System.out.println("You have increased your attribute");
        }
        return points;
    }

    public String displayMap(Tile[][] map) {
        String ret = "";
        ret += "- - - - - - - - - -\n";
        char[][] chars = new char[map.length][map.length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                chars[i][j] = map[i][j].display();
                ret += chars[i][j] + " ";
                if (j == map.length - 1) ret += "\n";
            }
        }
        ret += "- - - - - - - - - -\n";
        return ret;
    }

    public void moving(Game game) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an action: WASD to move/attack - X to open character sheet");
        System.out.print(displayMap(game.map));
        String input = scanner.next();

        switch (input) {
            case "exit":
                System.out.println("You have exited the game.");
                break;
            case "x":
                System.out.print(characterScreen(game.pc));
                break;
            // case "m" -> open magic screen and allow for attuning spell
            // case "e" -> open equip screen and allow gear to be equipped
            // case "i" -> open inventory screen and allow for items to be used/equipped
            case "w":
            case "a":
            case "s":
            case "d":
                System.out.print(game.pc.move(game, input));
                break;
        }



    }

    public void combat(Character c1, Character c2) {
        c1.location.display = 'F';
        while (c1.HP.value > 0 && c2.HP.value > 0) {
            // player attacks NPC
            if ((((Math.random() * 10) + c1.DEX.value) >= c2.dodgeValue && (((Math.random() * 10) + c1.STR.value) > c2.armorValue))) { // if attack beats dodge and armor deal damage
                c2.HP.value -= c1.STR.value;
                System.out.println(c1.race.name + " " + c1.caste.name + " hits " + c2.race.name + " " + c2.caste.name + " for " + c1.STR.value + " damage, leaving remaining HP at " + c2.HP.value);
            } else System.out.println("Your attack missed!");
            // NPC attacks player
            if ((((Math.random() * 10) + c2.DEX.value) >= c1.dodgeValue && (((Math.random() * 10) + c2.STR.value) > c1.armorValue))) { // if attack beats dodge and armor deal damage
                c1.HP.value -= c2.STR.value;
                System.out.println(c2.race.name + " " + c2.caste.name + " hits " + c1.race.name + " " + c1.caste.name + " for " + c2.STR.value + " damage, leaving remaining HP at " + c1.HP.value);
            } else System.out.println("The " + c2.race.name + " " + c2.caste.name + " missed you!");
        }

        if (c1.HP.value <= 0) {
            c1.location.display = c2.myChar;
            c1.location = null;
        } else {
            c2.location.display = c1.myChar;
            c2.location = null;
        }
    }

    public String characterScreen(Player pc) {
        String ret = "";
        ret += "-----------\n";

        for (int i = 0; i < pc.attributes.length; i++) {
            ret += i + ": " + pc.attributes[i].name + " - " + pc.attributes[i].value + "\n";
        }
        ret += "-----------\n";
        for (int i = 0; i < Character.skills.size(); i++) {
            ret += i + ": " + Character.skills.get(i).name + " - " + Character.skills.get(i).value + "\n";
        }
        ret += "-----------\n";
        return ret;
    }
}
