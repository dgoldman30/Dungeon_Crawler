import java.util.ArrayList;
import java.util.Scanner;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;

class Player extends Character {
    int level = 1;
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
    int mental;

    Player(Race race, Caste caste, int[] attPoints) {
        this.race = race;
        this.caste = caste;
        this.myChar = 'P';
        // increment aptitude for favorite caste and race skills
        for (int i = 0; i < this.race.favoredSkills.size(); i++) {
            this.skills.get(this.skills.indexOf(this.race.favoredSkills.get(i))).aptitude++;
        }
        for (int i = 0; i < this.caste.favoredSkills.size(); i++) {
            this.skills.get(this.skills.indexOf(this.caste.favoredSkills.get(i))).aptitude++;
        }

        // add the race attributes
        for (int i = 0; i < attributes.size()-1; i++) {
                this.attributes.get(i).value += (race.attributeAdjustments[i] + attPoints[i]);
        }

        this.dodgeValue += this.attributes.get(1).value + skills.get(4).value;
        this.armorValue += skills.get(5).value;
        this.mental += this.attributes.get(3).value + skills.get(2).value;


       // for (int i = 0; i < this.caste.startingEquipment.length; i++) {
       //     this.inventory.add(this.caste.startingEquipment[i]);
       // }

    }
    public void dropItem(Item item) {
        item.drop();
        inventory.remove(inventory.indexOf(item));
    }

    @Override
    public Tile[][] move(Tile[][] map) {
        Tile currLoc = this.location;
        Tile newLoc = currLoc;
        currLoc.occupant = null; //empty the occupant on the old tile
        Scanner scanner = new Scanner(System.in);
        String keyCode = scanner.next();

        switch (keyCode) {
            case "a":
                if (currLoc.y > 0) {
                    newLoc = map[currLoc.x][currLoc.y - 1];
                    System.out.println("You moved left.");// if left move left
                } else System.out.println("You are at the map edge. You cannot move left.");
                break;
            case "w":
                if (currLoc.x > 0) {
                    newLoc = map[currLoc.x - 1][currLoc.y];
                    System.out.println("You moved up.");// if up move up
                } else System.out.println("You are at the map edge. You cannot move up.");
                break;
            case "d":
                if (currLoc.y < map.length) {
                    newLoc = map[currLoc.x][currLoc.y + 1]; // if right move right
                    System.out.println("You moved right.");
                } else System.out.println("You are at the map edge. You cannot move right.");
                break;
            case "s":
                if (currLoc.x < map.length) {
                    newLoc = map[currLoc.x + 1][currLoc.y]; // if down move down
                    System.out.println("You moved down.");
                } else System.out.println("You are at the map edge. You cannot move down.");
                break;
        }
        // occupy new tile and set that tile in the right place on map
        this.occupy(newLoc);
        //map[newLoc.x][newLoc.y] = newLoc; redundant
        return map;
    }


    public void setAttunedSpell(Spell spell) {
        this.attunedSpell = spell;
    }

    public void occupy(Tile tile) {
        tile.display = this.myChar;
        tile.occupant = this;
        this.location = tile;
        this.location.display();
    }

    public void charScreen() {

    }

    public Tile getLocation() {
        return this.location;
    }
}