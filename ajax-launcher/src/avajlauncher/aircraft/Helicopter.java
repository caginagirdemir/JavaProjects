package avajlauncher.aircraft;

import java.io.IOException;

import avajlauncher.AvajLauncher;

import avajlauncher.flyable.Flyable;
import avajlauncher.tower.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() throws IOException {
        String weather = weatherTower.getWeather(this.coordinates);
        switch (weather) {
            case "SUN":
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude() + 10,
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() + 2 > 100 ? 100 : this.coordinates.getHeight() + 2
                );
                AvajLauncher.writer.write("Helicopter#" + this.name + "(" + this.id + "): This is hot.");
                AvajLauncher.writer.newLine();
                break;
            case "RAIN":
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude() + 5,
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight()
                );
                AvajLauncher.writer.write("Helicopter#" + this.name + "(" + this.id + "): It's raining. Better watch out for lightings.");
                AvajLauncher.writer.newLine();
                break;
            case "FOG":
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude() + 1,
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight()
                );
                AvajLauncher.writer.write("Helicopter#" + this.name + "(" + this.id + "): It's foggy.");
                AvajLauncher.writer.newLine();
                break;
            case "SNOW":
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() - 12
                );
                AvajLauncher.writer.write("Helicopter#" + this.name + "(" + this.id + "): It's snowing. We're gonna crash.");
                AvajLauncher.writer.newLine();
                break;
        }
        if (this.coordinates.getHeight() <= 0) {
            AvajLauncher.writer.write("Helicopter#" + this.name + "(" + this.id + ") landing.");
            AvajLauncher.writer.newLine();
            AvajLauncher.writer.write("Tower says: Helicopter#" + this.name + "(" + this.id + ") unregistered from weather tower.");
            AvajLauncher.writer.newLine();
            this.weatherTower.unregister(this);
        }
    }

    public void registerTower(WeatherTower weatherTower) throws IOException {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        AvajLauncher.writer.write("Tower says: Helicopter#" + this.name + "(" + this.id + ") registered to weather tower.");
        AvajLauncher.writer.newLine();
    }
    
}