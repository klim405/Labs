package utils;

import java.util.ArrayList;

public interface Telescope {
    void setObservationArea(ObservationArea area);
    ArrayList<SpaceObject> getSpaceObjects();
}
