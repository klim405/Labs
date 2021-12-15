package story;

import story.events.SpaceObjectEnteredInArea;
import story.events.SpaceObjectEscapedArea;
import utils.EventPublisher;
import utils.ObservationArea;
import utils.SpaceObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class PlanetArea extends EventPublisher implements ObservationArea {
    protected ArrayList<SpaceObject> spaceObjects = new ArrayList<>();
    protected String areaName;

    public PlanetArea(SpaceObject planet) {
        areaName = "Территория планеты " + planet;
        spaceObjects.add(planet);
    }

    public PlanetArea(SpaceObject planet, SpaceObject[] moons) {
        areaName = "Территория планеты " + planet;
        spaceObjects.addAll(Arrays.asList(moons));
    }

    @Override
    public void removeSpaceObject(SpaceObject spaceObject) {
        this.spaceObjects.remove(spaceObject);
        publishEvent(new SpaceObjectEscapedArea(spaceObject));
    }

    @Override
    public ArrayList<SpaceObject> getSpaceObjects() {
        return this.spaceObjects;
    }

    @Override
    public void addSpaceObject(SpaceObject spaceObject) {
        switch (spaceObject.getType()) {
            case PLANET:
                throw new RuntimeException("В области наблюдения не может быть две планеты");
            case STAR:
                throw new RuntimeException("В области наблюдения не может находится звезда");
            case BLACK_HOLE:
                throw new RuntimeException("В области наблюдения не может находится сингулярность");
            default:
                publishEvent(new SpaceObjectEnteredInArea(spaceObject));
                this.spaceObjects.add(spaceObject);
        }
    }

    @Override
    public String toString() {
        return areaName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanetArea that = (PlanetArea) o;
        return spaceObjects.equals(that.spaceObjects) && areaName.equals(that.areaName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spaceObjects, areaName);
    }
}
