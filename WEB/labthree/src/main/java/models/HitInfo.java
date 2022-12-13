package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import utils.geometry.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class HitInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private Double coordX;

    @NotNull
    private Double coordY;

    @NotNull
    @Positive
    @DecimalMin(value = "1")
    @DecimalMax(value = "5")
    private Double radius;

    @NotNull
    private Boolean status;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public void setCurrentDate() {
        time = new Date();
    }

    public String getFormatTime() {
        DateFormat df = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
        return df.format(time);
    }

    public String getStatusAsText() {
        return status ? "HIT" : "FAIL";
    }

    public void checkHitAndSetStatus() {
        if (radius != null && coordX != null && coordY != null) {
            // todo set figure
            CompoundFigure figure = new CompoundFigure(
                    new Triangle(
                            new Point(0, 0),
                            new Point(0, radius),
                            new Point(radius, 0)
                    ),
                    new Rectangle(
                            new Point(0, 0),
                            new Point(-radius, radius/2)
                    ),
                    new Circle(
                            new Point(0,0), radius/2, 1.5*Math.PI, 2*Math.PI
                    )
            );
            status = figure.checkPointInArea(new Point(coordX, coordY));
        }
    }

    public long getId() {
        return id;
    }

    public double getCoordX() {
        return coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public double getRadius() {
        return radius;
    }

    public boolean isStatus() {
        return status;
    }

    public Date getTime() {
        return time;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
