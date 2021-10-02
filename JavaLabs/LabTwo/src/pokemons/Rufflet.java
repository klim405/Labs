package pokemons;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

import moves.Confide;
import moves.Facade;
import moves.Screech;

public class Rufflet extends Pokemon {
    public Rufflet(String name, int level) {
        super(name, level);
        setStats(70, 83, 50, 37, 50, 60);
        setType(Type.NORMAL, Type.FLYING);
        addMove(new Confide());
        addMove(new Facade());
        addMove(new Screech());
    }
}
