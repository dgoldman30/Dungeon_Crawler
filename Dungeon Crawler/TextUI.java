import java.util.Scanner;

public class TextUI {

    public Player characterCreation() {
        Player pc = new Player(pickRace(), pickCaste(), pickAtt());
        System.out.println("You have created a new " + pc.race.name + pc.caste.name + "! Time to explore!");
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
        int[] points = new int[4];
        System.out.println("You get to assign 4 additional attribute points to your character \n " +
                "Select 1 for STR, 2 for DEX, 3 for INT, and 4 for WILL");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            int ref = scanner.nextInt()-1;
            points[ref] += 1;
            System.out.println("You have increased your " + Character.attributes.get(ref).name);

        }
        return points;
    }



}
