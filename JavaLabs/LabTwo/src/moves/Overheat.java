package moves;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.SpecialMove;

public class Overheat extends SpecialMove {
    public Overheat() {
        super(Type.FIRE, 130.0D, 0.9D);
    }

    public void applyOppEffects(Pokemon p) {
        int currentStat = (int) p.getStat(Stat.SPECIAL_ATTACK);
        if (currentStat >= -4) {
            p.setMod(Stat.SPECIAL_ATTACK, currentStat-2);
        } else if (currentStat == -5) {
            p.setMod(Stat.SPECIAL_ATTACK, --currentStat);
        }
    }

    @Override
    protected String describe() {
        return "использует Overheat";
    }
}
