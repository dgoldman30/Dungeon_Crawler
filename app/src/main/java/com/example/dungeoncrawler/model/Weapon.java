package com.example.dungeoncrawler.model;

public class Weapon extends Item {

    double damage;
    double accuracy;
    boolean large;
    double crit;
    public enum Weapons {
        DAGGER(new Weapon("Dagger",  0.3, 1.0, 0.4, false, "Lightweight Dagger for quick attacks")),
        SWORD(new Weapon("Sword",  0.7, 0.9, 0.1, false, "A basic lightweight sword")),
        BRASSKNUCKLES(new Weapon("Brass Knuckles",  0.5, 0.8, 0.4, true, "Wearable brass knuckles for critical attacks")),
        HARPOON(new Weapon("Harpoon",  0.7, 0.8, 0.3, true, "Are you trying to kill a whale?")),
        SABRE(new Weapon("Sabre",  0.7, 0.85, 0.2, false, "A curved sword built for dueling")),
        CLUB(new Weapon("Club",  0.9, 0.75, 0.02, false, "A random stick built for bruising")),
        BALLANDCHAIN(new Weapon("Ball and Chain",  0.8, 0.8, 0.15, true, "The ole' ball and chain")),
        HAMMER(new Weapon("Hammer", 1, 0.7, 0.05, true, "A large hammer for pulverizing your enemies")),
        MACE(new Weapon("Mace",  0.85, 0.75, 0.1, false, "A spiked mace, perfect for smashing skeletons"));

        Weapon wn;
        Weapons(Weapon w) {
            wn = w;
        }
    }


    public Weapon(){}
    public Weapon(String name,  double dam, double acc, double crit, boolean large, String description) {
        this.name = name;
        this.description = description;
        this.damage = dam;
        this.accuracy = acc;
        this.large = large;
        this.crit = crit;
        this.equipable = true;
        this.disp = 'w';
    }
    public Item drop() {
        System.out.println("You have dropped your " + this.name);
        return this;
    }

    public double strike(Character user, Character target) {
        double damage;
        if (crit * user.DEX.value > target.WILL.value) {
            damage = ((this.damage * 10) * (this.crit * 10) + user.STR.value) / 3;
        } else damage = ((this.damage * 10) + user.STR.value) / 3;

        if (damage > 0) {
            target.HP.value -=  damage;
            return damage;
        } return 0;
    }

}