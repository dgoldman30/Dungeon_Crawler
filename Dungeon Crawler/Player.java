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

        // add the race attributes
        for (int i = 0; i < attributes.length; i++) {
                attributes[i].value += (race.attributeAdjustments[i] + attPoints[i]);
        }

        this.dodgeValue += attributes[1].value + skills.get(4).value;
        this.armorValue += skills.get(5).value;
        this.mental += attributes[3].value + skills.get(2).value;


        for (int i = 0; i < this.caste.startingItems.size(); i++) {
            this.inventory.add(this.caste.startingItems.get(i));
        }

    }
//    public void dropItem(Item item) {
//        item.drop();
//        inventory.remove(item);
//    }


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

}