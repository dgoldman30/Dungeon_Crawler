import java.lang.reflect.Array;
import java.util.ArrayList;


class Player extends Character {


    Player(Race race, Caste caste, int[] attPoints) {
        this.race = race;
        this.caste = caste;
        this.myChar = 'P';

        //setSlots();
        // increment aptitude for favorite caste and race skills
        for (int i = 0; i < this.race.favoredSkills.size(); i++) {
            skills.get(skills.indexOf(this.race.favoredSkills.get(i))).aptitude++;
        }
        for (int i = 0; i < this.caste.favoredSkills.size(); i++) {
            skills.get(skills.indexOf(this.caste.favoredSkills.get(i))).aptitude++;
        }

        // race attributes
        for (int i = 0; i < 5; i++) {
            attributes[i].value += race.attributeAdjustments[i];
        }
        // dodge value, armor value, mental value
        this.attributes[5].value += attributes[1].value + skills.get(4).value;
        this.attributes[6].value += (int) ((attributes[3].value / 3) + skills.get(5).value);
        this.attributes[7].value += (attributes[3].value + (attributes[2].value * 2) + skills.get(2).value) / 3;

        // caste items; equip any weapons
        for (int i = 0; i < this.caste.startingItems.size(); i++) {
            if (this.caste.startingItems.get(i).getClass() == Weapon.class) {
                this.equipWeapon((Weapon) this.caste.startingItems.get(i));
            } else this.inventory.add(this.caste.startingItems.get(i));
        }

    }

    public String move(Game game, String input) {
        String ret = "";
        Tile[][] map = game.map;
        Tile currLoc = this.location;
        Tile newLoc = currLoc;

        switch (input) {
            case "a":
                if (currLoc.y > 0 ) {
                    newLoc = map[currLoc.x][currLoc.y - 1];
                    ret += "You moved to the left.";
                } else ret += "You're at the map edge. You cannot move left.";
                break;
            case "w":
                if (currLoc.x > 0) {
                    newLoc = map[currLoc.x - 1][currLoc.y];
                    ret += "You moved up.";
                } else ret += "You are at the map edge. You cannot move up.";
                break;
            case "d":
                if (currLoc.y < map.length - 1) {
                    newLoc = map[currLoc.x][currLoc.y + 1];
                    ret += "You moved to the right."; // move right
                } else ret += "You are at the map edge. You cannot move right.";
                break;
            case "s":
                if (currLoc.x < map.length - 1) {
                    newLoc = map[currLoc.x + 1][currLoc.y];
                    ret += "You moved down."; // move down
                } else ret += "You are at the map edge. You cannot move down.";
                break;
        }

        this.executeMove(newLoc);

        return ret;
    }

    public void setAttunedSpell(Spell spell) {
        this.attunedSpell = spell;
    }

    public void equip(Weapon weapon) {
    }

    public ArrayList<Item> search() {
        ArrayList<Item> items = new ArrayList<>(this.location.contents);
        return items;
    }



}