import java.util.ArrayList;
import java.util.Scanner;

class Race {

    ArrayList<Skill> favoredSkills = new ArrayList<Skill>();
    int[] attributeAdjustments;
    String name;
    String description;
    Race(ArrayList<Skill> favoredSkills, int[] attributeAdjustments, String name, String description) {
        this.favoredSkills = favoredSkills;
        this.attributeAdjustments = attributeAdjustments;
        this.name = name;
        this.description = description;
    }

}