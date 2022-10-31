package com.example.dungeoncrawler.model;

public class Weapon extends Item {

    double damage;
    double accuracy;
    boolean large;
    double crit;
    public enum Weapons {
        DAGGER(new Weapon("Dagger", "Lightweight Dagger for quick attacks", 0.3, 1.0, 0.4, false)),
        SWORD(new Weapon("Sword", "A basic lightweight sword", 0.7, 0.9, 0.1, false)),
        BRASSKNUCKLES(new Weapon("Brass Knuckles", "Wearable brass knuckles for critical attacks", 0.5, 0.9, 0.5, true)),
        HARPOON(new Weapon("Harpoon", "Are you trying to kill a whale?", 0.7, 0.8, 0.3, true)),
        SABRE(new Weapon("Sabre", "A curved sword built for dueling", 0.7, 0.85, 0.2, false)),
        CLUB(new Weapon("Club", "A random stick built for bruising", 0.9, 0.75, 0.02, false)),
        BALLANDCHAIN(new Weapon("Ball and Chain", "The ole' ball and chain", 0.8, 0.8, 0.15, true)),
        HAMMER(new Weapon("Hammer", "A large hammer for pulverizing your enemies", 1, 0.7, 0.05, true)),
        MACE(new Weapon("Mace", "A spiked mace, perfect for smashing skeletons", 0.85, 0.75, 0.1, false));

        Weapon wn;
        Weapons(Weapon w) {
            wn = w;
        }
    }


    public Weapon(){}
    public Weapon(String name, String description, double dam, double acc, double crit, boolean large) {
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
        if ((Math.random() *  (target.DEX.value / 10)) < (crit * (user.DEX.value / 10) * Math.random())) {
            damage = (this.damage * 10) * (this.crit * 10) + user.STR.value - target.AV.value;
        } else damage = (this.damage * 10) + user.STR.value - target.AV.value;
        target.HP.value -=  damage;
        return damage;
    }

}