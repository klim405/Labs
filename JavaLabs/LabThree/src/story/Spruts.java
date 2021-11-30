package story;

import utils.Person;
import utils.PersonAbstract;
import utils.Thing;

import java.util.Objects;

public class Spruts extends PersonAbstract {
    public Spruts(String name) {
        super(name);
    }

    public void offerToWatchTheEarth(Person person, Thing thing) {
        offerThing(person, thing);
        System.out.println("Если оно будет следить за околоземным пространством, и искать космический корабль");
    }

    @Override
    protected boolean isPersonAgree(Thing thing) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spruts spruts = (Spruts) o;
        return Objects.equals(name, spruts.name);
    }
}
