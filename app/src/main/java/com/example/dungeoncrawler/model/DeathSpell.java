package com.example.dungeoncrawler.model;

public class DeathSpell extends Spell {

    /*public enum Death {
        WEAKNESS(new DeathSpell(, 3, 0)),

        DeathSpell spell;
        Death() {spell = s};
    }*/

    public DeathSpell(boolean self, double factor, int attribute) {
        this.self = self;
        this.factor = factor;
        this.attribute = attribute;
    }

}
