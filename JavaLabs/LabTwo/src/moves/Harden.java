package moves;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.StatusMove;

public class Harden extends StatusMove {
    public Harden() {
        super(Type.NORMAL, 0.0D, 1.0D);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        int currentStat = (int) p.getStat(Stat.DEFENSE);
        if (currentStat < 6) {
            p.setMod(Stat.DEFENSE, ++currentStat);
        }
    }

    @Override
    protected boolean checkAccuracy(Pokemon pokemon, Pokemon pokemon1) {
        return true;
    }

    @Override
    protected String describe() {
        return "использует Harden";
    }
}
