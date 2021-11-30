package story;

import utils.ObservationArea;
import utils.SpaceObject;
import utils.Telescope;

import java.util.ArrayList;
import java.util.Objects;

public class DavilonianTelescope implements Telescope {
    ObservationArea observationArea;
    String name;

    public DavilonianTelescope(String name) {
        this.name = name;
    }

    @Override
    public ArrayList<SpaceObject> getSpaceObjects() {
        return this.observationArea.getSpaceObjects();
    }

    @Override
    public void setObservationArea(ObservationArea area) {
        this.observationArea = area;
        System.out.println("Зона наблюдения изменена! Телескоп направлен на " + area);
    }

    @Override
    public String toString() {
        return this.name + " телескоп давилонской абсерватории";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DavilonianTelescope that = (DavilonianTelescope) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
