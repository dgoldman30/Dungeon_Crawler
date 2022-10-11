import java.util.ArrayList;

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
    char myChar;

    Player(Race race, Caste caste, int[] attPoints) {
        this.race = race;
        this.caste = caste;
        myChar = 'P';

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

       // for (int i = 0; i < this.caste.startingEquipment.length; i++) {
       //     this.inventory.add(this.caste.startingEquipment[i]);
       // }


    }

    public void move(Tile newLoc) {
        this.location = newLoc;
    }

    public void setAttunedSpell(Spell spell) {
        this.attunedSpell = spell;
    }

}