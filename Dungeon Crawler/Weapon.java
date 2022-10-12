class Weapon extends Item {
    public Weapon(String name, String description) {
        this.name = name;
        this.description = description;
    }
    @Override
    public Item drop() {
        System.out.println("you have dropped weapon: " + this.name);
        return this;
    }

}