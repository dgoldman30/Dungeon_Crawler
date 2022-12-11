package com.example.dungeoncrawler.model;

import com.example.dungeoncrawler.model.*;

public class LifeSpell extends Spell {

    public enum Life {
        // instant heal
        HEAL(new LifeSpell("Heal",true, 4, 4, 0, 2)),
        // regeneration heal
        REGENERATE(new LifeSpell("Regenerate",true, 1, 4, 10, 4)),
        // strength buff
        STRENGTHEN(new LifeSpell("Strengthen",true, 2, 0, 5, 5)),
        // block buff
        STONEWALL(new LifeSpell("Stonewall",true, 3, 9, 5, 4)),
        // intelligence buff
        ENLIGHTEN(new LifeSpell("Enlighten",true, 2, 2, 6, 4)),
        ;

        public LifeSpell spell;
        Life(LifeSpell l) { spell = l; }
    }


    public LifeSpell(String name, boolean self, double factor, int attribute, int duration, int mp) {
        this.self = self;
        this.factor = factor;
        this.attribute = attribute;
        this.duration = duration;
        this.mp = mp;
        this.name = name;
    }

    @Override
    public void resolve(Character target) {
        int originalValue = target.attributes[this.attribute].value;
        target.attributes[this.attribute].value += this.factor;
        while (duration > 0) {

        }
    }
}
