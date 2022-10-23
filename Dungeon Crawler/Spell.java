class Spell {
    int range;
    String description;
    double factor;
    int attribute;
    Character target;

    public Spell(int range, String desc, double factor, int attribute) {
        this.range = range;
        this.description = desc;
        this.factor = factor;
        this.attribute = attribute;
    }

    public void setTarget(Character target) {
        this.target = target;
    }

    public void resolve(Character user) {
        target.attributes[attribute].value -= this.factor * user.MV.value; 
    }
}