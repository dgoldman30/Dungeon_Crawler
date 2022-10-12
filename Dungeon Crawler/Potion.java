class Potion extends Item {
    String description;

    public Potion(String name, String description) {
        this.name = name;
        this.description = description;
    }
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