package utils;

import java.util.Objects;

public abstract class PersonAbstract implements Person {
    protected String name;

    public PersonAbstract(String name) {
        this.name = name;
    }

    abstract protected boolean isPersonAgree(Thing thing);

    @Override
    public void agreeWith(Person person, Thing thing) {
        if (isPersonAgree(thing)) {
            System.out.println(name + " согласился(-лась)");
        } else {
            System.out.println(name + " отказался(-лась)");
        }
    }

    @Override
    public void offerThing(Person person, Thing thing) {
        System.out.println(name + " пообещал существу " + person + " предмет \"" + thing.getName() + "\"");
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
