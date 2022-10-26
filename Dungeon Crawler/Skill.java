import java.util.ArrayList;
public class Skill {
    float value = 0;
    String name;
    String description;
    boolean toggled = false;
    int aptitude = 0;

    public Skill(String name, String description) {
        this.name = name;
        this.description = description;
    }

   /* public enum Skills {
        MELEE("Melee", "Effects the character's accuracy and damage with melee weapons"),
        RANGED(),
        SPELLCASTING(),
        SHIELD(),
        DODGE(),
        ARMOR(),
        INVOCATION(),
        FAITH(),
        FIREMAGIC(),
        EARTHMAGIC(),
        AIRMAGIC(),
        WATERMAGIC()
    }*/

    private void increment() {
        if (this.toggled) {
            this.value += (aptitude);
        } else { this.value += 0.5 * aptitude; }

    }

    private void toggle() {
        this.toggled = !this.toggled;
    }

}