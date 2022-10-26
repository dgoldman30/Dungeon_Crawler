import java.awt.image.AreaAveragingScaleFilter;
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


    final List<Skill> favoredSkills = new ArrayList<>();
    final List<Item> startingItems = new ArrayList<>();
    final String description;
    final String name;


    Caste(Skill[] favoredSkills, Item[] startingItems, String name, String description) {
        this.favoredSkills.addAll(Arrays.asList(favoredSkills));
        this.startingItems.addAll(Arrays.asList(startingItems));
        this.name = name;
        this.description = description;
    }
}