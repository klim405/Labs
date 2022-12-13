package beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import models.HitInfo;

import java.io.Serializable;

@Named
@RequestScoped
public class HitForm implements Serializable {
    private String coordX;
    private String coordY;
    private String radius;

    public HitInfo getHitInfo() {
        try {
            HitInfo hitInfo = new HitInfo();
            hitInfo.setCoordX(Double.parseDouble(coordX));
            hitInfo.setCoordY(Double.parseDouble(coordY));
            hitInfo.setRadius(Double.parseDouble(radius));
            hitInfo.checkHitAndSetStatus();
            hitInfo.setCurrentDate();
            return hitInfo;
        } catch (NullPointerException | NumberFormatException ignore) {
            return null;
        }
    }

    public void doHit() {
        System.out.println(coordX);
        System.out.println(coordY);
        System.out.println(radius);
    }

    public String getCoordX() {
        return coordX;
    }

    public void setCoordX(String coordX) {
        this.coordX = coordX;
    }

    public String getCoordY() {
        return coordY;
    }

    public void setCoordY(String coordY) {
        this.coordY = coordY;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }
}
