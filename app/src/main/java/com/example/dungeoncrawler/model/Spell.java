package com.example.dungeoncrawler.model;

public abstract class Spell {

    public boolean self;
    public String name;
    double factor;
    int attribute;
    int duration;
    int mp;
    Character target;
    public SpellEffect effect;

    void resolve(Character target) {}

}