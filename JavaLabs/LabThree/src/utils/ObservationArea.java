package utils;

import java.util.ArrayList;

public interface ObservationArea {
    ArrayList<SpaceObject> getSpaceObjects();
    void addSpaceObject(SpaceObject spaceObject);
    void removeSpaceObject(SpaceObject spaceObject);
}
