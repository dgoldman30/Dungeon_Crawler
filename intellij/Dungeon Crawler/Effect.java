public class Effect {

    int duration;
    int attribute;
    double factor;
    double potency;

    Character target;

    public Effect(int dur, int att, double factor, double pot) {
        this.duration = dur;
        this.attribute = att;
        this.factor = factor;
        this.potency = pot;
    }

}
