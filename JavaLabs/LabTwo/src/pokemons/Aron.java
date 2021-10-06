package pokemons;

import ru.ifmo.se.pokemon.Pokemon;
import moves.DoubleTeam;
import moves.Flamethrower;
import ru.ifmo.se.pokemon.Type;

public class Aron extends Pokemon {
    public Aron(String name, int level) {
        super(name, level);
        setStats(50, 70, 100, 40, 40, 30);
        setType(Type.STEEL, Type.ROCK);
        addMove(new Flamethrower());
        addMove(new DoubleTeam());
    }
}
