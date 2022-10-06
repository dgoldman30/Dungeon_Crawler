import java.util.ArrayList;
public class Skill {
    float value;
    String name;
    String description;
    boolean toggled = false;

    public Skill(String name, String description) {
        this.name = name;
        this.description = description;
    }

    private void increment() {
        if (this.toggled) {this.value+= 1;} else {this.value+=0.5;}
    }

    private void toggle() {
        this.toggled = !this.toggled;
    }

}