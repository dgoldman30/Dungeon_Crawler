package com.example.dungeoncrawler.model;

import com.example.dungeoncrawler.model.*;

public class LifeSpell extends Spell {

    public enum Life {
        // instant heal
        HEAL(new LifeSpell(true, 4, 4, 0, 2)),
        // regeneration heal
        REGENERATE(new LifeSpell(true, 1, 4, 10, 4)),
        // strength buff
        STRENGTHEN(new LifeSpell(true, 2, 0, 5, 5)),
        // block buff
        STONEWALL(new LifeSpell(true, 3, 9, 5, 4)),
        // intelligence buff
        ENLIGHTEN(new LifeSpell(true, 2, 2, 6, 4)),
        ;

        LifeSpell spell;
        Life(LifeSpell l) { spell = l; }
    }


    public LifeSpell(boolean self, double factor, int attribute, int duration, int mp) {
        this.self = self;
        this.factor = factor;
        this.attribute = attribute;
        this.duration = duration;
        this.mp = mp;
    }

    public void resolve(Character target) {
        int originalValue = target.attributes[this.attribute].value;
        target.attributes[this.attribute].value += this.factor;
        while (duration > 0) {

        }
    }
}
