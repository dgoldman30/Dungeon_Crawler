import java.util.ArrayList;
public class Tile {
    boolean available = true;
    Character occupant;
    char display;
    //char display = occupant.sprite; //display the occupants sprite
    ArrayList<Item> contents = new ArrayList<>();

    Tile(char display) {
        this.display = display;
    }
}
