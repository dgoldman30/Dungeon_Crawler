import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

public enum Caste {
    GLADIATOR(Game.gladSkills, Game.gladItems, "Gladiator", "A perrenial warrior, never far from their blade."),
    URCHIN(Game.urSkills, Game.urItems, "Urchin", ""),
    WOODSMAN(Game.woodSkills, Game.woodItems, "Woodsman", ""),
    FISHERMAN(Game.fishSkills, Game.fishItems, "Fisherman", ""),
    APPRENTICE(Game.appSkills, Game.appItems, "Apprentice", ""),
    CLERGYMAN(Game.clerSkills, Game.clerItems, "Clergyman", "");


    public List<Skill> favoredSkills = new ArrayList<>();
    public List<Item> startingItems = new ArrayList<>();
    public String description;
    public String name;


    Caste(Skill[] favoredSkills, Item[] startingItems, String name, String description) {
        for (int i = 0; i < favoredSkills.length; i++) this.favoredSkills.add(favoredSkills[i]);
        for (int i = 0; i < startingItems.length; i++) this.startingItems.add(startingItems[i]);
        this.name = name;
        this.description = description;
    }
}