package com.example.dungeoncrawler.model;

abstract class Spell {

    int range;
    String description;
    double factor;
    int attribute;
    Character target;

    void resolve(Character target) {}

}