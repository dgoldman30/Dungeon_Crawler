import java.util.ArrayList;

public class NPC extends Character {

    Character target;
    boolean hostile;

    NPC() {}
    NPC(Race race, Caste caste, boolean hostile) {
        super(race, caste);
        this.hostile = hostile;
        if(hostile) {myChar = 'E';} else myChar = 'N';

    }
    public void setTarget(Character target) { this.target = target; }

    @Override
    public Tile[][] move(Tile[][] map) {
        Tile tLoc = this.target.location;
        Tile cLoc = this.location;

        cLoc.occupant = null;

        int xDiff = tLoc.x - cLoc.x;
        int yDiff = tLoc.y - cLoc.y;

        if (Math.abs(xDiff) > Math.abs(yDiff)) {
            if (xDiff > 0) { cLoc = map[cLoc.x+1][cLoc.y]; }
            else { cLoc = map[cLoc.x-1][cLoc.y]; }
        } else if (yDiff > 0) { cLoc = map[cLoc.x][cLoc.y+1]; }
        else { cLoc = map[cLoc.x][cLoc.y-1]; }

        // occupy new tile if it's available
        this.executeMove(cLoc);
        //map[cLoc.x][cLoc.y] = cLoc;
        return map;
    }

    public void occupy(Tile tile) {
        tile.display = this.myChar;
        tile.occupant = this;
        this.location = tile;
        this.location.display();
    }

}
