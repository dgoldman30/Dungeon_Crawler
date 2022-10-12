import java.util.ArrayList;

public class NPC extends Character {
    int level = 1;
    Race race;
    Caste caste ;
    ArrayList<Attribute> attributes = new ArrayList<Attribute>(Character.attributes);
    ArrayList<Skill> skills = new ArrayList<Skill>(Game.skills);
    ArrayList<Item> equipment = new ArrayList<Item>();
    ArrayList<Item> inventory = new ArrayList<Item>();
    Tile location;
    Spell attunedSpell;
    boolean hostile;
    char myChar;
    Character target;

    NPC() {}
    NPC(Race race, Caste caste, boolean hostile) {
        this.race = race;
        this.caste = caste;
        this.hostile = hostile;

        if(hostile) {myChar = 'E';} else myChar = 'N';

        // increment aptitude for favorite caste and race skills
        for (int i = 0; i < this.race.favoredSkills.size(); i++) {
            this.skills.get(this.skills.indexOf(this.race.favoredSkills.get(i))).aptitude++;
        }
        for (int i = 0; i < this.caste.favoredSkills.size(); i++) {
            this.skills.get(this.skills.indexOf(this.caste.favoredSkills.get(i))).aptitude++;
        }

        // add the race attributes and any extra attribute modifiers
       // for (int i = 0; i < attributes.size()-1; i++) {
       //     this.attributes.get(i).value += (race.attributeAdjustments[i] + attPoints[i]);
       // }
    }
    public void setTarget(Character target) {
        this.target = target;
    }
    @Override
    public Tile[][] move(Tile[][] map) {
        Tile tLoc = this.target.location;
        Tile cLoc = this.location;

        int xDiff = tLoc.x - cLoc.x;
        int yDiff = tLoc.y - cLoc.y;

        if (Math.abs(xDiff) > Math.abs(yDiff)) {
            if (xDiff > 0) { cLoc = map[cLoc.x+1][cLoc.y]; }
            else { cLoc = map[cLoc.x-1][cLoc.y]; }
        } else if (yDiff > 0) { cLoc = map[cLoc.x][cLoc.y+1]; }
        else { cLoc = map[cLoc.x][cLoc.y-1]; }

        // occupy new tile and set that tile in the right place on map
        this.occupy(cLoc);
        map[cLoc.x][cLoc.y] = cLoc;
        return map;
    }

    public void occupy(Tile tile) {
        tile.display = this.myChar;
        tile.occupant = this;
        this.location.display();
        this.location = tile;
    }
}
