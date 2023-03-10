package com.example.dungeoncrawler.model;

public class Potion extends Item {
    public String name;
    int factor;
    int target;

    public enum Potions {
        HEALTH(new Potion("Healing Potion", "Heals most wounds and recovers HP ", 10, 4)),
        DEX(new Potion("Dexterity Potion", "Increases Dexterity", 1, 1)),
        STR(new Potion("Strength Potion", "Increases Strength", 1, 0)),
        INT(new Potion("Intelligence Potion", "Increases brainpower and knowledge", 1, 2)),
        WILL(new Potion("Willpower Potion", "Increases Willpower", 1, 3));

        public Potion po;
        Potions(Potion p) {
            po = p;
        }
    }

    public Potion(String name, String description, int factor, int target) {
        this.name = name;
        this.description = description;
        this.factor = factor;
        this.target = target;
        this.equipable = false;
        this.disp = 'p';
    }

    public Item drop() {
        System.out.println("You have dropped " + name + " Potion");
        return this;
    }

    public String drink(Character target) {
        if (this.target == 4) {
            target.heal(this.factor);
        }
        if (this.target < 4) { target.attributes[this.target].value += this.factor; }
        return "You drank a " + this.name + ", increasing your " + target.attributes[this.target].name + " by " + this.factor;
    }

    @Override
    public String getName() {
        return this.name;
    }
}