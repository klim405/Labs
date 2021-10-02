package moves;
import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Effect;

public class CrossPoison extends PhysicalMove {
    public CrossPoison() {
        super(Type.POISON, 70.0D, 1.0D);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.1 && !(p.hasType(Type.STEEL) || p.hasType(Type.POISON))) {
            Effect.poison(p);
        }
    }

    @Override
    protected double calcCriticalHit(Pokemon att, Pokemon def) {
        if (Math.random() < 0.125) {
            return 2.0D;
        } else {
            return 1.0D;
        }
    }

    @Override
    protected String describe() {
        return "использует Cross Poison";
    }
}
