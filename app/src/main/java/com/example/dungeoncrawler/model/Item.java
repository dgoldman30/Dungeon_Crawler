package com.example.dungeoncrawler.model;

abstract class Item {
    String description, name;
    char disp;
    abstract Item drop();
    boolean equipable;

}