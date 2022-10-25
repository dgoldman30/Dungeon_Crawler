class Potion extends Item {
    int factor;
    int target;

    public Potion(String name, String description, int factor, int target) {
        this.name = name;
        this.description = description;
        this.factor = factor;
        this.target = target;
        this.equipable = false;
    }

    public Item drop() {
        System.out.println("You have dropped " + name + " Potion");
        return this;
    }

    public void drink(Character target) {
        target.attributes[this.target].value += this.factor;
    }

    public void use() {};
}