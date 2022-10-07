import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
class Caste {
    ArrayList<Skill> favoredSkills = new ArrayList<Skill>();
    ArrayList<Item> startingItems = new ArrayList<Item>();
    String description;

    Caste(ArrayList<Skill> favoredSkills, ArrayList<Item> startingItems, String description) {
        this.favoredSkills = favoredSkills;
        this.startingItems = startingItems;
        this.description = description;
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