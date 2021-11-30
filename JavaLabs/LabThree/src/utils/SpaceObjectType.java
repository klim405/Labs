package utils;

public enum SpaceObjectType {
    PLANET("Планета"),
    STAR("Звезда"),
    UFO("НЛО"),
    SPACESHIP("Космический корабль"),
    SATELLITE("Спутник"),
    COMET("Комета"),
    METEORITE("Метеорит"),
    BLACK_HOLE("Черная дыра"),
    MOON("Естественный спутник планеты");

    final String name;

    SpaceObjectType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
