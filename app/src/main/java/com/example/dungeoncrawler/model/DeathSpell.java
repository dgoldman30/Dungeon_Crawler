package com.example.dungeoncrawler.model;

public class DeathSpell extends Spell {

    public enum Death {
        // lower the enemy's strength value
        WEAKNESS(new DeathSpell("Weakness", false, -3, 0, 5, 1)),
        // drain the enemy's HP and heal yourself
        DRAIN(new DeathSpell("Drain",false, -3, 3, 0, 2)),
        // lower the enemy's DV
        SLOW(new DeathSpell("Slow",false, 0, 5, 5, 3)),
        // lower the enemy's toHit
        BLIND(new DeathSpell("Blind",false, 0, 8, 5, 4));

        public DeathSpell spell;
        Death(DeathSpell s) { spell = s; }

    }

    public DeathSpell(String name, boolean self, double factor, int attribute, int duration, int mp) {
        this.self = self;
        this.factor = factor;
        this.attribute = attribute;
        this.duration = duration;
        this.mp = mp;
        this.effect = new SpellEffect(this.duration, this.factor, this.attribute);
    }

    @Override
    public void resolve(Character target) {
        this.effect.factor = this.getFactor(target, 2);
        target.applyEffect(this);
    }

    public int getFactor(Character target, int modifier) {
        if (this.factor == 0) {
            return -((int) target.attributes[this.attribute].value / modifier);
        } else return (int) this.factor;
    }

}
