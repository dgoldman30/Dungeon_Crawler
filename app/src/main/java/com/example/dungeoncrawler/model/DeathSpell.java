package com.example.dungeoncrawler.model;

public class DeathSpell extends Spell {

    public enum Death {
        // lower the enemy's strength value
        WEAKNESS(new DeathSpell(false, 3, 0, 5, 1)),
        // drain the enemy's HP and heal yourself
        DRAIN(new DeathSpell(false, 3, 3, 0, 2)),
        // lower the enemy's DV
        SLOW(new DeathSpell(false, 2, 5, 5, 3)),
        // lower the enemy's toHit
        BLIND(new DeathSpell(false, 2, 8, 5, 4));

        private DeathSpell spell;
        Death(DeathSpell s) { spell = s; }

    }

    public DeathSpell(boolean self, double factor, int attribute, int duration, int mp) {
        this.self = self;
        this.factor = factor;
        this.attribute = attribute;
        this.duration = duration;
        this.mp = mp;
    }
}
