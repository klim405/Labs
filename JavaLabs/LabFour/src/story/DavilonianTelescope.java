package story;

import utils.*;

import java.util.ArrayList;
import java.util.Objects;

public class DavilonianTelescope extends EventPublisher implements Telescope {
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
        if (area instanceof EventPublisher) {
            ((EventPublisher) area).addEventListener(new ObservationAreaListener());
        }

        System.out.println("Зона наблюдения изменена! Телескоп направлен на " + area);
    }

    class ObservationAreaListener implements EventListener {
        @Override
        public void actionPerformed(Event event) {
            System.out.println(DavilonianTelescope.this + " : Обнаружен объект " + ((SpaceObject) event.getSource()).getType() + " в области наблюдения");
            publishEvent(event);
        }
    }


    @Override
    public String toString() {
        return this.name + " телескоп давилонской обсерватории";
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
