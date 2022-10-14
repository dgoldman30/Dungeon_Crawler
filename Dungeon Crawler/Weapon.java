class Weapon extends Item {

    double damage;
    double accuracy;
    boolean twoHanded;
    String slot;

    public Weapon(String name, String description, double dam, double acc, String slot) {
        this.name = name;
        this.description = description;
        this.damage = dam;
        this.accuracy = acc;
        this.slot = slot;
    }
    @Override
    public Item drop() {
        System.out.println("you have dropped weapon: " + this.name);
        return this;
    }

}