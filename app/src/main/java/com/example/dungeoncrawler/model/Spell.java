package com.example.dungeoncrawler.model;

public abstract class Spell {

    boolean self;
    String description;
    double factor;
    int attribute;
    int duration;
    int mp;
    Character target;
    SpellEffect effect;

    void resolve(Character target) {}

}