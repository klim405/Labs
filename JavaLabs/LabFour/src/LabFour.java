import story.*;
import utils.SpaceObject;
import utils.SpaceObjectType;


public class LabFour {
    public static void main(String[] args) {
        // 10456443.7
        // прилетевшего корабля. Пообещав лунным астрономам значительную сумму денег,
        // господин Спрутс велел им вести беспрерывное наблюдение за планетой Землей и,
        // если в межпланетном пространстве будет обнаружено какое-нибудь подозрительное
        // тело вроде космического корабля, сейчас же доложить ему. С тех пор самый усовершенствованный
        // гравитонный телескоп давилонской обсерватории был направлен в сторону ближайшей к Луне планеты,
        // то есть, попросту говоря, в сторону Земли.
        Astronomer astronomer = new Astronomer("");
        Spruts spruts = new Spruts("Спрутс");
        DavilonianTelescope telescope = new DavilonianTelescope("гравитонный");
        PlanetArea observationArea = new PlanetArea(new Planet("Земля"));
        observationArea.addSpaceObject(new Moon("Луна"));
        Money money = new Money(20000000);

        spruts.offerToWatchTheEarth(astronomer, money);
        astronomer.agreeWith(spruts, money);
        astronomer.aimTelescopeAtTarget(telescope, observationArea);
        astronomer.trackSpaceObjectsForContract(telescope, SpaceObjectType.SPACESHIP);

        observationArea.addSpaceObject(new SpaceObject() {
            @Override
            public SpaceObjectType getType() {
                return SpaceObjectType.METEORITE;
            }
        });
        observationArea.addSpaceObject(new SpaceObject() {
            @Override
            public SpaceObjectType getType() {
                return SpaceObjectType.COMET;
            }
        });
        observationArea.addSpaceObject(new SpaceObject() {
            @Override
            public SpaceObjectType getType() {
                return SpaceObjectType.SPACESHIP;
            }
        });
    }
}
