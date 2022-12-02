package avajlauncher.aircraft;

import avajlauncher.flyable.Flyable;
import avajlauncher.exceptions.IllegalAircraftException;


public class AircraftFactory {

    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws IllegalAircraftException {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        switch (type) {
            case "Baloon":
                return new Baloon(name, coordinates);
            case "JetPlane":
                return new JetPlane(name, coordinates);
            case "Helicopter":
                return new Helicopter(name, coordinates);
            default:
                throw new IllegalAircraftException("Invalid aircraft type: " + type);
        }
    }
}