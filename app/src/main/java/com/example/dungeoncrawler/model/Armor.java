package com.example.dungeoncrawler.model;

public class Armor extends Item {

    double encumberance;
    double AV;

    public enum Armors {
        CHAIN(new Armor("Chain shirt", "This light metal armor is made of thousands of chain links", 2, 5)),
        LEATHER(new Armor("Leather armor", "A well-made leather cuirass", 1, 4)),
        PLATE(new Armor("Metal plate", "A heavy suit of metal plate armor", 5, 8)),
        ROBES(new Armor("Robes", "Fine linen robes", 0.5, 2)),
        RAGS(new Armor("Dirty rags", "A decrepit set of rags", 0, 1)),
        CLOTHES(new Armor("Clothes", "A set of peasant's clothes", 0.5, 1.5));

        Armor armor;
        Armors(Armor a) {
            armor = a;
        }
    }

    Armor(String name, String desc, double e, double av) {
        this.name = name;
        this.description = desc;
        this.encumberance = e;
        this.AV = av;
        this.disp = 'a';
        this.equipable = true;
    }

    @Override
    Item drop() {
        return null;
    }
}
