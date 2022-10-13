class Weapon extends Item {

    double damage;
    double accuracy;
    boolean twoHanded;

    public Weapon(String name, String description, double dam, double acc, boolean two) {
        this.name = name;
        this.description = description;
        this.damage = dam;
        this.accuracy = acc;
        this.twoHanded = two;
    }
    @Override
    public Item drop() {
        System.out.println("you have dropped weapon: " + this.name);
        return this;
    }

}