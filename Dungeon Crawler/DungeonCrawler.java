import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class DungeonCrawler {


    /*KeyListener listener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            // x for character screen
            if (e.getKeyCode() == KeyEvent.VK_X) {
                Game.ui.characterScreen(Game.pc);
            }
            // i for inventory
            // ? for help
            // WASD for move
            // m for magic screen
            // e for equipment screen
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };*/
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        Game game = new Game(10);
        boolean fighting = false;

        while (!fighting) {
            System.out.println("Enter an action: WASD to move - X to open character sheet");
            System.out.print(game.ui.displayMap(game.map));
            String input = scanner.next();
            switch (input) {
                case "x":
                    System.out.print(game.ui.characterScreen(Game.pc));
                    break;
                // case "m" -> open magic screen and allow for attuning spell
                // case "e" -> open equip screen and allow gear to be equipped
                // case "i" -> open inventory screen and allow for items to be used/equipped
                case "w":
                case "a":
                case "s":
                case "d":
                    game.pc.move(game.map, input);
                    game.enemy.move(game.map);
                    break;
            }
        }
    }
}

