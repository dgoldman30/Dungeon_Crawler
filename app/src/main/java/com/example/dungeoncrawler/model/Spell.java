package com.example.dungeoncrawler.model;

abstract class Spell {

    boolean self;
    String description;
    double factor;
    int attribute;
    Character target;

    void resolve(Character target) {}

}