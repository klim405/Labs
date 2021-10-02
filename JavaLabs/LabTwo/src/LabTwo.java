import ru.ifmo.se.pokemon.Battle;

import pokemons.Aron;
import pokemons.Druddigon;
import pokemons.Braviary;
import pokemons.Rufflet;
import pokemons.Lairon;
import pokemons.Aggron;

public class LabTwo {
    // 101143
    public static void main(String[] args) {
        Battle b = new Battle();
        b.addAlly(new Druddigon("Druddigon", (int) (Math.random()*10)));
        b.addAlly(new Aron("Aron", (int) (Math.random()*10)));
        b.addAlly(new Braviary("Braviary", (int) (Math.random()*10)));
        b.addFoe(new Rufflet("Rufflet", (int) (Math.random()*10)));
        b.addFoe(new Lairon("Lairon", (int) (Math.random()*10)));
        b.addFoe(new Aggron("Aggron", (int) (Math.random()*10)));
        b.go();
    }
}
