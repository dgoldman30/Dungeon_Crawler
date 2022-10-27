import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Caste {
    GLADIATOR(Game.gladSkills, Game.gladItems, "Gladiator", "A perrenial warrior, never far from their blade."),
    URCHIN(Game.urSkills, Game.urItems, "Urchin", ""),
    WOODSMAN(Game.woodSkills, Game.woodItems, "Woodsman", ""),
    FISHERMAN(Game.fishSkills, Game.fishItems, "Fisherman", ""),
    APPRENTICE(Game.appSkills, Game.appItems, "Apprentice", ""),
    CLERGYMAN(Game.clerSkills, Game.clerItems, "Clergyman", "");


    String[] favoredSkills;
    List<Item> startingItems = new ArrayList<>();
    String description;
    String name;


    Caste(String[] favoredSkills, Item[] startingItems, String name, String description) {
        this.favoredSkills = favoredSkills;
        this.startingItems.addAll(Arrays.asList(startingItems));
        this.name = name;
        this.description = description;
    }
}