package com.example.dungeoncrawler.model;

public class SpellEffect {

    int duration;
    int attribute;
    double factor;

    public SpellEffect(int dur, double factor, int attribute) {
        this.duration = dur;
        this.factor = factor;
        this.attribute = attribute;
    }

    public void tick() {
        this.duration--;
    }

}
