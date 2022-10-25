import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

class Race {

    List<Skill> favoredSkills;
    int[] attributeAdjustments;
    String name;
    String description;
    Race(List<Skill> favoredSkills, int[] attributeAdjustments, String name, String description) {
        this.favoredSkills = favoredSkills;
        this.attributeAdjustments = attributeAdjustments;
        this.name = name;
        this.description = description;
    }

}