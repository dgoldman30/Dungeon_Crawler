package com.example.dungeoncrawler.model;

public class DeathSpell extends Spell {

    /*public enum Death {
        WEAKNESS(new DeathSpell(1, 3, 0)),

        DeathSpell spell;
        Death() {spell = s};
    }*/

    public DeathSpell(int range, double factor, int attribute) {
        this.range = range;
        this.factor = factor;
        this.attribute = attribute;
    }

}
