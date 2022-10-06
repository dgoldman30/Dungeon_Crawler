import java.util.ArrayList;

class Player extends Character {
    int level = 1;
    Race race;
    Caste caste ;
    ArrayList<Attribute> attributes = new ArrayList<Attribute>(Character.attributes);
    ArrayList<Skill> playerSkills = new ArrayList<Skill>(Character.skills);
    ArrayList<Item> equipment = new ArrayList<Item>();
    ArrayList<Item> inventory = new ArrayList<Item>();
    Tile location;
    Spell attunedSpell;

    Player(Race race, Caste caste) {
        this.race = race;
        this.caste = caste;

        for (int i = 0; i < this.race.favoredSkills.length; i++) {
            this.playerSkills.add(i, this.race.favoredSkills[i]);
        }
        for (int i = 0; i < this.caste.favoredSkills.length; i++) {
            if (!this.playerSkills.contains(this.caste.favoredSkills[i])) {
                this.playerSkills.add(i, this.caste.favoredSkills[i]);
            }
        }

        for (int i = 0; i < this.caste.startingEquipment.length; i++) {
            this.inventory.add(this.caste.startingEquipment[i]);
        }

    }
}