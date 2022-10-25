import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

final class Caste {

    List<Skill> favoredSkills;

    List<Item> startingItems;
    String description;
    String name;

    Caste(List<Skill> favoredSkills, List<Item> startingItems, String name, String description) {
        this.favoredSkills = favoredSkills;
        this.startingItems = new ArrayList<>(startingItems);
        this.name = name;
        this.description = description;
    }

    enum Castes {

    }

    /*
    Gladiator:
        Melee
        Shield
        Armor
        Dodge
        --
        Iron sword
        Wooden shield
        Chain armor
        Leather helmet
        Potion of Strength
     */

    /*
    Urchin
        Melee
        Ranged
        Dodge
        Invocation
        --
        Shiv
        Sling
        Sling bullets x 30
        Wand of magic missle
        Dirty rags
     */

     /*
    Woodsman
        Ranged
        Dodge
        Faith
        Earth magic
        --
        Long bow
        Short sword
        Wooden arrow x 30
        Cloak
        Leather armor
     */

    /*
    Fisherman
        Melee
        Spellcasting
        Water magic
        Air magic
        --
        Harpoon
        Scroll of icicle
        Scroll of control wind
        Clothes
        Salted cod
     */

    /*
    Apprentice
        Spellcasting
        Fire magic
        Earth Magic
        Invocation
        --
        Staff
        Robes
        Scroll of firebolt
        Scroll of entrench
        Mana potion
     */

    /*
    Clergyman
        Armor
        Shield
        Faith
        Water magic
        --
        Cudgel
        Robes
        Talisman
        Scroll of holy water
        Leather helmet
     */
}