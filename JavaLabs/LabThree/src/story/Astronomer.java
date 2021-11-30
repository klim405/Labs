package story;


import utils.*;

import java.util.ArrayList;
import java.util.Objects;

public class Astronomer extends PersonAbstract {
    public Astronomer(String name) {
        super("Астроном " + name);
    }

    public void aimTelescopeAtTarget(Telescope telescope, ObservationArea target) {
        System.out.println(name + " направляет " + telescope + " на \"" + target + "\"");
        telescope.setObservationArea(target);
    }

    public void trackSpaceObjects(Telescope telescope, SpaceObjectType type) {
        System.out.println(name + " пытается найти обьект \"" + type + "\"");
        ArrayList<SpaceObject> objects = telescope.getSpaceObjects();
        boolean flg = true;

        for (SpaceObject obj : objects) {
            if (obj.getType() == type) {
                System.out.println(name + " нашел что-то интересное: "+obj);
                flg = false;
            }
        }

        if (flg) {
            System.out.println(name + " не нашёл " + type.toString());
        }
    }

    protected boolean isPersonAgree(Thing thing) {
        if (thing.getClass() == Money.class) {
            Money money = (Money) thing;
            return money.getAmount() > 10000000;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Astronomer that = (Astronomer) o;
        return Objects.equals(name, that.name);
    }
}
