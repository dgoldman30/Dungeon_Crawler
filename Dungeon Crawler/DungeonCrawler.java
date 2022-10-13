import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DungeonCrawler {


    KeyListener listener = new KeyListener() {
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
    };

    public static void main(String[] args) {
        Game game = new Game(10);
    }
}