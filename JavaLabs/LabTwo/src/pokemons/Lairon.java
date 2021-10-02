package pokemons;


import moves.Overheat;

public class Lairon extends Aron {
    public Lairon(String name, int level) {
        super(name, level);
        setStats(60, 90, 140, 50, 50, 40);
        addMove(new Overheat());
    }
}
