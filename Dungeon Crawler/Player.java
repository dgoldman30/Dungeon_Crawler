import java.util.ArrayList;
import java.util.Scanner;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;

class Player extends Character {


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
       // Tile[][] nMap = map;
        Tile currLoc = this.location;
        Tile newLoc = currLoc;

        switch (input) {
            case "a":
                if (currLoc.y > 0 ) {
                    newLoc = map[currLoc.x][currLoc.y - 1];
                } else System.out.println("You are at the map edge. You cannot move left.");
                break;
            case "w":
                if (currLoc.x > 0) {
                    newLoc = map[currLoc.x - 1][currLoc.y];
                } else System.out.println("You are at the map edge. You cannot move up.");
                break;
            case "d":
                if (currLoc.y < map.length) {
                    newLoc = map[currLoc.x][currLoc.y + 1]; // move right
                } else System.out.println("You are at the map edge. You cannot move right.");
                break;
            case "s":
                if (currLoc.x < map.length) {
                    newLoc = map[currLoc.x + 1][currLoc.y]; // move down
                } else System.out.println("You are at the map edge. You cannot move down.");
                break;
        }

        this.executeMove(newLoc);
        return map;
    }


    public void setAttunedSpell(Spell spell) {
        this.attunedSpell = spell;
    }

}