package com.example.dungeoncrawler.model;

abstract class Spell {

    boolean self;
    String description;
    double factor;
    int attribute;
    int duration;
    int mp;
    Character target;

    void resolve(Character target) {}

}