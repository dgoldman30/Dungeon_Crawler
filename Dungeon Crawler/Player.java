import java.util.ArrayList;
import java.util.Scanner;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;

class Player extends Character {
    /*int level = 1;
    Race race;
    Caste caste ;
    ArrayList<Attribute> attributes = new ArrayList<Attribute>(Character.attributes);
    ArrayList<Skill> skills = new ArrayList<Skill>(Game.skills);
    ArrayList<Item> equipment = new ArrayList<Item>();
    ArrayList<Item> inventory = new ArrayList<Item>();
    Tile location;
    Spell attunedSpell;

    int dodgeValue;
    int armorValue;
    int mental;*/

    Player(Race race, Caste caste, int[] attPoints) {
        this.race = race;
        this.caste = caste;
        this.myChar = 'P';
        attributes = new ArrayList<>(Character.attributes);

        // increment aptitude for favorite caste and race skills
        for (int i = 0; i < this.race.favoredSkills.size(); i++) {
            skills.get(skills.indexOf(this.race.favoredSkills.get(i))).aptitude++;
        }
        for (int i = 0; i < this.caste.favoredSkills.size(); i++) {
            skills.get(skills.indexOf(this.caste.favoredSkills.get(i))).aptitude++;
        }

        // add the race attributes
        for (int i = 0; i < attributes.size(); i++) {
                attributes.get(i).value += (race.attributeAdjustments[i] + attPoints[i]);
        }

        this.dodgeValue += attributes.get(1).value + skills.get(4).value;
        this.armorValue += skills.get(5).value;
        this.mental += attributes.get(3).value + skills.get(2).value;


        for (int i = 0; i < this.caste.startingItems.size(); i++) {
            this.inventory.add(this.caste.startingItems.get(i));
        }

    }
    public void dropItem(Item item) {
        item.drop();
        inventory.remove(item);
    }


    public Tile[][] move(Tile[][] map, String input) {
        Tile[][] nMap = map;
        Tile currLoc = this.location;
        Tile newLoc = currLoc;
        currLoc.occupant = null; //empty the occupant on the old tile

        switch (input) {
            case "a":
                if (currLoc.y > 0 ) {
                    newLoc = nMap[currLoc.x][currLoc.y - 1];
                    System.out.println("You moved left.");// move left
                } else System.out.println("You are at the map edge. You cannot move left.");
                break;
            case "w":
                if (currLoc.x > 0) {
                    newLoc = nMap[currLoc.x - 1][currLoc.y];
                    System.out.println("You moved up.");// move up
                } else System.out.println("You are at the map edge. You cannot move up.");
                break;
            case "d":
                if (currLoc.y < map.length) {
                    newLoc = nMap[currLoc.x][currLoc.y + 1]; // move right
                    System.out.println("You moved right.");
                } else System.out.println("You are at the map edge. You cannot move right.");
                break;
            case "s":
                if (currLoc.x < map.length) {
                    newLoc = nMap[currLoc.x + 1][currLoc.y]; // move down
                    System.out.println("You moved down.");
                } else System.out.println("You are at the map edge. You cannot move down.");
                break;
        }
        // occupy new tile and set that tile in the right place on map
        if (newLoc.available) { this.occupy(newLoc); }
        else {
            this.attack(newLoc.occupant);
            return map;
        }
        //map[newLoc.x][newLoc.y] = newLoc; redundant
        return nMap;
    }


    public void setAttunedSpell(Spell spell) {
        this.attunedSpell = spell;
    }


    public void charScreen() {

    }

}