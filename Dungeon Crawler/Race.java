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
}