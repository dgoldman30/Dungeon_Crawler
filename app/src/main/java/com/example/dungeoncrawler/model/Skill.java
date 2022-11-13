package com.example.dungeoncrawler.model;

public class Skill {
    float value = 0;
    String name;
    String description;
    boolean toggled = false;
    public int aptitude = 0;

    public Skill(String name, String description) {
        this.name = name;
        this.description = description;
    }



    private void increment() {
        if (this.toggled) {
            this.value += (aptitude);
        } else { this.value += 0.5 * aptitude; }

    }

    private void toggle() {
        this.toggled = !this.toggled;
    }

}