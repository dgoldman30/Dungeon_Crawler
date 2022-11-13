package com.example.dungeoncrawler.model;

public enum Race {
    HUMAN(Game.humSkills, Game.humAtt, "Human", "Humans are unremarkable physically, however, they are rather intelligent despite their weak."),
    MINOTAUR(Game.minSkills, Game.minAtt, "Minotaur","Half-man, half-bull, minotaur are ferociously strong, though rather dull of mind."),
    SPRIGGAN(Game.sprSkills, Game.sprAtt, "Spriggan", "Spriggans are dexterous and nimble creatures that seem to be more plant than animal."),
    DWARF(Game.dwSkills, Game.dwAtt, "Dwarf", "Dwarves are stocky and possess remarkable fortitude of will along with commendable strength"),
    NYMPH(Game.nySkills, Game.nyAtt, "nymph", "Nymph's are furtive fae who specialize in the arcane."),
    ORC(Game.orcSkills, Game.orcAtt, "Orc", "Orcs are incredibly strong, though not terribly hearty, and usual quite devout."),
    KENKU(Game.kSkills, Game.kAtt, "Kenku", "Kenku resemble large crows and typically reside in the slums of large cities.");


    public String[] favoredSkills;
    public int[] attributeAdjustments;
    String name;
    String description;
    Race(String[] favoredSkills, int[] attributeAdjustments, String name, String description) {
        this.favoredSkills = favoredSkills;
        this.attributeAdjustments = attributeAdjustments;
        this.name = name;
        this.description = description;
    }

}