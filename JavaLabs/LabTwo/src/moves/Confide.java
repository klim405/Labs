package moves;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Stat;

public class Confide extends StatusMove {
    public Confide() {
        super(Type.NORMAL, 0.0D, 1.0D);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        int currentStat = (int) p.getStat(Stat.SPECIAL_ATTACK);
        if (currentStat > -6) {
            p.setMod(Stat.SPECIAL_ATTACK, --currentStat);
        }
    }

    @Override
    protected boolean checkAccuracy(Pokemon pokemon, Pokemon pokemon1) {
        return true;
    }

    @Override
    protected String describe() {
        return "использует Confide";
    }
}
