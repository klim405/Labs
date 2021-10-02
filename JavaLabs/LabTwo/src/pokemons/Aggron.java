package pokemons;


import moves.SmartStrike;

public class Aggron extends Lairon {
    public Aggron(String name, int level) {
        super(name, level);
        setStats(70, 110, 180, 60, 60, 50);
        addMove(new SmartStrike());
    }
}
