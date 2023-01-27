package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import utils.geometry.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "HITINFOS")
public class HitInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name="userdata_id", nullable=false)
    private Userdata userdata;

    @NotNull
    private Double coordX;

    @NotNull
    private Double coordY;

    @NotNull
    private Double radius;

    @NotNull
    private Boolean isHit;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public long getId() {
        return id;
    }

    public Boolean getHit() {
        return isHit;
    }

    public void setCurrentDate() {
        time = new Date();
    }

    public String getFormatTime() {
        DateFormat df = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
        return df.format(time);
    }

    public String getStatus() {
        return isHit ? "HIT" : "FAIL";
    }

    public void checkHitAndSetStatus() {
        if (radius != null && coordX != null && coordY != null) {
            CompoundFigure figure;
            if (radius >= 0) {
                figure = new CompoundFigure(
                        new Triangle(
                                new Point(0, 0),
                                new Point(0, -radius),
                                new Point(radius/2, 0)
                        ),
                        new Rectangle(
                                new Point(0, 0),
                                new Point(-radius/2, -radius)
                        ),
                        new Circle(
                                new Point(0,0), radius/2, 0.5*Math.PI, Math.PI
                        )
                );
            } else {
                figure = new CompoundFigure(
                        new Triangle(
                                new Point(0, 0),
                                new Point(0, -radius),
                                new Point(radius/2, 0)
                        ),
                        new Rectangle(
                                new Point(0, 0),
                                new Point(-radius/2, -radius)
                        ),
                        new Circle(
                                new Point(0,0), Math.abs(radius/2), 1.5*Math.PI, 2*Math.PI
                        )
                );
            }
            isHit = figure.checkPointInArea(new Point(coordX, coordY));
        }
    }

    public Userdata getUserdata() {
        return userdata;
    }

    public Double getCoordX() {
        return coordX;
    }

    public Double getCoordY() {
        return coordY;
    }

    public Double getRadius() {
        return radius;
    }

    public void setUserdata(Userdata userdata) {
        this.userdata = userdata;
    }

    public void setCoordX(Double coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(Double coordY) {
        this.coordY = coordY;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }
}
