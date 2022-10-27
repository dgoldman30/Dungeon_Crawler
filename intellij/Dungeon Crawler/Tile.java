import java.util.ArrayList;
public class Tile {
    boolean available = true;
    Character occupant;
    char display = 'X';
    int x;
    int y;
    //char display = occupant.sprite; //display the occupants sprite
    ArrayList<Item> contents = new ArrayList<>();

    Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public char display() {
        if (occupant == null) {
            display = 'X';
        }

        return this.display;
    }
}
