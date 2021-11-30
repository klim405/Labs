package story;

import utils.SpaceObject;
import utils.SpaceObjectType;

import java.util.Objects;

public class Planet implements SpaceObject {
    protected String name;

    public Planet(String name) {
        this.name = name;
    }

    @Override
    public SpaceObjectType getType() {
        return SpaceObjectType.PLANET;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(name, planet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
