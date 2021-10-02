package moves;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.StatusMove;

public class Screech extends StatusMove {
    public Screech() {
        super(Type.NORMAL, 0.0D, 0.85D);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        int currentStat = (int) p.getStat(Stat.DEFENSE);
        if (currentStat >= -4) {
            p.setMod(Stat.DEFENSE, currentStat-2);
        } else if (currentStat == -5) {
            p.setMod(Stat.DEFENSE, --currentStat);
        }
    }
}
