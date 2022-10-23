public class Weapon extends Item {
    double damage;
    double accuracy;
    boolean large;
    double crit;


    public Weapon() {}
    public Weapon(String name, String description, double dam, double acc, double crit, boolean large) {
        this.name = name;
        this.description = description;
        this.damage = dam;
        this.accuracy = acc;
        this.large = large;
        this.crit = crit;
    }
    public Item drop() {
        System.out.println("You have dropped your " + this.name);
        return this;
    }

    public double strike(Character user, Character target) {
        double damage;
        if ((Math.random() *  (target.DEX.value / 10)) < (crit * (user.DEX.value / 10) * Math.random())) {
            damage = (this.damage * 10) * (this.crit * 10) + user.STR.value - target.armorValue;
        } else damage = (this.damage * 10) + user.STR.value - target.armorValue;
        target.HP.value -=  damage;
        return damage;
    }

}