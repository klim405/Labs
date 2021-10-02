package moves;
import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Status;
import ru.ifmo.se.pokemon.Pokemon;

public class Facade extends PhysicalMove {
    public Facade() {
        super(Type.NORMAL, 70.0D, 1.0D);
    }

    @Override
    protected double calcBaseDamage(Pokemon att, Pokemon def) {
        Status status = def.getCondition();
        if (status == Status.BURN || status == Status.POISON || status == Status.PARALYZE) {
            return super.calcBaseDamage(att, def) * 2;
        } else {
            return super.calcBaseDamage(att, def);
        }
    }

    @Override
    protected String describe() {
        return "использует Facade";
    }
}
