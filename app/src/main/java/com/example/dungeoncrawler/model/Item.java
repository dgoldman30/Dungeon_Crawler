package com.example.dungeoncrawler.model;

public abstract class Item {
    String description;
    public String name;
    char disp;
    abstract Item drop();
    boolean equipable;

    public String getName() { return name; }
}