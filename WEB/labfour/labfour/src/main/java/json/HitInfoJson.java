package json;


public class HitInfoJson {
    Long id;
    Double coordX;
    Double coordY;
    Long radius;
    Boolean isHit;
    String time;

    public HitInfoJson(Long id, Double coordX, Double coordY, Long radius, Boolean isHit, String time) {
        this.id = id;
        this.coordX = coordX;
        this.coordY = coordY;
        this.radius = radius;
        this.isHit = isHit;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public Double getCoordX() {
        return coordX;
    }

    public Double getCoordY() {
        return coordY;
    }

    public Long getRadius() {
        return radius;
    }

    public Boolean getIsHit() {
        return isHit;
    }

    public String getTime() {
        return time;
    }
}
