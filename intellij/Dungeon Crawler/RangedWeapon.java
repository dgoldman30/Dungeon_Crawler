public class RangedWeapon extends Weapon {
    int range;
    int ammunition;
    Character target;

    RangedWeapon(String desc, String name, double damage, double acc, double crit, int range, int ammo) {
        this.description = desc;
        this.name = name;
        this.damage = damage;
        this.accuracy = acc;
        this.crit = crit;
        this.range = range;
        this.ammunition = ammo;
        this.large = true;
    }

    public void setTarget(Character target) {this.target = target;}


}
