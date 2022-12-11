package com.example.dungeoncrawler.model;

public class SpellEffect {

    int duration;
    int attribute;
    double factor;
    int rounds;

    public SpellEffect(int dur, double factor, int attribute) {
        this.duration = dur;
        this.factor = factor;
        this.attribute = attribute;
    }

    public void tick(Character character) {
        if (this.rounds == 0) {
            character.attributes[this.attribute].value += this.factor;
        }
        this.rounds++;
        if (this.rounds == this.duration) {
            character.attributes[this.attribute].value -= this.factor;
            character.removeEffect();
        }
    }

    public void setFactor(int mod) {
        if (mod < this.factor) this.factor -= mod;
        else this.factor = 1;
    }
}
