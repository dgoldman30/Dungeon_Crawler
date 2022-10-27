import java.util.Arrays;
import java.util.Iterator;
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
        while (true) {
            int input;

            int i = 0;
            System.out.println("Each race has different set of favored Skills \n Please Select a Race from the list below");
            for (Race r : Race.values()) {
                System.out.println(i + ": " + r.name);
                i++;
            }
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
            }
            else input = -1;

            if (input >= 0 && input < Race.values().length) {
                race =  Race.values()[input];
                System.out.println("You have selected Race: " + race.name);
                return race;
            }
            else
                System.out.println("Invalid input, Please try again");
        }


    }
    public Caste pickCaste() {
        Caste caste;
        while (true) {
            int input;
            System.out.println("Each caste favored skills and a set of starting equipment \n Please select a caste from the list below");

            int itr = 0;
            for (Caste c : Caste.values()) {
                System.out.println(itr + ": " + c.name);
                itr++;
            }

            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
            } else input = -1;
            if (input >= 0 && input < Caste.values().length) {
              caste = Caste.values()[input];
                System.out.println("You have selected " + caste.name);
                return caste;
            }
            else System.out.println("Invalid input, Please try again");

        }
    }
    public int[] pickAtt() {
        int[] points = new int[5];
        int ref;
            while (true) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("You get to assign 4 additional attribute points to your character \n " +
                        "Select 1 for STR, 2 for DEX, 3 for INT, and 4 for WILL");
                if (scanner.hasNextInt()) {
                    ref = scanner.nextInt() - 1;
                    if (ref < 4 && ref >= 0) {
                        points[ref] += 1;
                        System.out.println("You have increased your attribute");
                        if (Arrays.stream(points).sum() == 4) {
                            return points;
                        }
                    }
                    else System.out.println("Invalid input, try again");
                } else System.out.println("Invalid input, try again");
            }
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
        System.out.println("Enter an action: \n - W, A, S, D to move/attack \n - X to open character sheet \n - \"exit\" to exit game");
        System.out.print(displayMap(game.map));
        String input = scanner.next().toLowerCase();

        switch (input) {
            case "exit":
                System.out.println("You have exited the game.");
                System.exit(1);
                break;
            case "x":
                System.out.print(characterScreen(game.pc));
                break;
            // case "m" -> open magic screen and allow for attuning spell
            // case "e" -> open equip screen and allow gear to be equipped
            case "i":
                System.out.print(inventoryScreen(game.pc));
                break;
            case "w":
            case "a":
            case "s":
            case "d":
                System.out.println(game.pc.move(game, input));
                game.enemy.move(game.map);
                break;
        }



    }

    public void combat(Character c1, Character c2) {
        System.out.println("You have entered combat. Enter an input to attack the enemy.");
        Scanner scanner = new Scanner(System.in);
        while (c1.HP.value > 0 && c2.HP.value > 0) {
            scanner.next();
            // player attacks NPC
            if ((((Math.random() * 10) + c1.DEX.value) >= c2.DV.value && (((Math.random() * 10) + c1.STR.value) > c2.DV.value))) { // if attack beats dodge and armor deal damage
                c2.HP.value -= c1.STR.value;
                if (c2.HP.value > 0) System.out.println(c1.race.name + " " + c1.caste.name + " hits " + c2.race.name + " " + c2.caste.name + " for " + c1.STR.value + " damage, leaving remaining HP at " + c2.HP.value);
                else {
                    System.out.println(c1.race.name + " " + c1.caste.name + " hits " + c2.race.name + " " + c2.caste.name + " for " + c1.STR.value + " damage, killing them." );
                    c2.location.occupant = null;
                    c2.location = null;
                    game.gameState = 3; // set game state to 3 - generate enemy
                    System.out.println("All enemies are dead. Generating a new enemy.");
                    break;
                }
            } else System.out.println("Your attack missed the " + c2.race.name + " " + c2.caste.name);

            // NPC attacks player
            if ((((Math.random() * 10) + c2.DEX.value) >= c1.DV.value && (((Math.random() * 10) + c2.STR.value) > c1.DV.value))) { // if attack beats dodge and armor deal damage
                c1.HP.value -= c2.STR.value;
                if (c1.HP.value > 0) System.out.println(c2.race.name + " " + c2.caste.name + " hits " + c1.race.name + " " + c1.caste.name + " for " + c2.STR.value + " damage, leaving remaining HP at " + c1.HP.value);
                else {
                    System.out.println(c2.race.name + " " + c2.caste.name + " hits you for " + c2.STR.value + " damage, killing you");
                    game.gameState = 10; // game over state
                    break;
                }
            } else System.out.println("The " + c2.race.name + " " + c2.caste.name + " missed you!");
        }
    }

    public String characterScreen(Player pc) {
        final String[] ret = {""};
        ret[0] += "-----------\nExperience: " + pc.experience + " " + pc.race.name + " " + pc.caste.name + "\n----------\nAttributes: \n";
        for (int i = 0; i < pc.attributes.length; i++) {
            ret[0] += i + ": " + pc.attributes[i].name + " - " + pc.attributes[i].value + "\n";
        }
        ret[0] += "-----------\nSkills \n";

        pc.skills.forEach((k,v) -> ret[0] += k + " - " + pc.skills.get(k).value + "\n");

        ret[0] += "-----------\n";
        return ret[0];
    }

    public String inventoryScreen(Player pc) {
        final String[] ret = {"Inventory: \n"};
        ret[0] += "---------\n";

        int index = 1;
        for (Item i : pc.inventory) {
            ret[0] += index + ": " + i.name + "\n\n";
            index++;
        }

        return ret[0];
    }
}
