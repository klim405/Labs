package moves;
import ru.ifmo.se.pokemon.*;

public class Rest extends StatusMove {
    public Rest() {
        super(Type.PSYCHIC, 0.0D, 1.0D);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        Effect e = new Effect().attack(0.0D).turns(2);
        p.setMod(Stat.HP, (int) (p.getHP() - p.getStat(Stat.HP)));
        p.addEffect(e);
    }

    @Override
    protected boolean checkAccuracy(Pokemon pokemon, Pokemon pokemon1) {
        return true;
    }

    @Override
    protected String describe() {
        return "использует Rest";
    }
}
