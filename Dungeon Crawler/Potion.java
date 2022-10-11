class Potion implements Item {
    String description;
    //Effect effect;

    public Item drop() {
        System.out.println("You have dropped " + name + " Potion");
        return this;
    }

    public Character drink(Character target) {
        //this.effect.apply(target);
        return target;
    }
}