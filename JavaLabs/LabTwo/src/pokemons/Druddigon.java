package pokemons;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

import moves.Facade;
import moves.Harden;
import moves.IceFang;
import moves.Rest;

public class Druddigon extends Pokemon {
    public Druddigon(String name, int level) {
        super(name, level);
        setStats(77, 120, 90, 60, 90, 48);
        setType(Type.DRAGON);
        addMove(new Facade());
        addMove(new Harden());
        addMove(new Rest());
        addMove(new IceFang());
    }
}
