class Potion implements Item {
    String description;
    //Effect effect;

    public void drop() {

    }

    public Character drink(Character target) {
        //this.effect.apply(target);
        return target;
    }
}