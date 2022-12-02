package avajlauncher.aircraft;

import java.io.IOException;

import avajlauncher.AvajLauncher;

import avajlauncher.flyable.Flyable;
import avajlauncher.tower.WeatherTower;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() throws IOException {
        String weather = weatherTower.getWeather(this.coordinates);
        switch (weather) {
            case "SUN":
                this.coordinates = new Coordinates(
                    this.coordinates.getLongitude() + 2,
                    this.coordinates.getLatitude(),
                    this.coordinates.getHeight() + 4 > 100 ? 100 : this.coordinates.getHeight() + 4
                );
                AvajLauncher.writer.write("Baloon#" + this.name + "(" + this.id + "): This is hot.");
                AvajLauncher.writer.newLine();
                break;
            case "RAIN":
                this.coordinates = new Coordinates(
                    this.coordinates.getLongitude(),
                    this.coordinates.getLatitude(),
                    this.coordinates.getHeight() - 5
                );
                AvajLauncher.writer.write("Baloon#" + this.name + "(" + this.id + "): It's raining. Better watch out for lightings.");
                AvajLauncher.writer.newLine();
                break;
            case "FOG":
                this.coordinates = new Coordinates(
                    this.coordinates.getLongitude(),
                    this.coordinates.getLatitude(),
                    this.coordinates.getHeight() - 3
                );
                AvajLauncher.writer.write("Baloon#" + this.name + "(" + this.id + "): It's foggy.");
                AvajLauncher.writer.newLine();
                break;
            case "SNOW":
                this.coordinates = new Coordinates(
                    this.coordinates.getLongitude(),
                    this.coordinates.getLatitude(),
                    this.coordinates.getHeight() - 15
                );
                AvajLauncher.writer.write("Baloon#" + this.name + "(" + this.id + "): It's snowing. We're gonna crash.");
                AvajLauncher.writer.newLine();
                break;
        }
        if (this.coordinates.getHeight() <= 0) {
            AvajLauncher.writer.write("Baloon#" + this.name + "(" + this.id + ") landing.");
            AvajLauncher.writer.newLine();
            this.weatherTower.unregister(this);
            AvajLauncher.writer.write("Tower says: Baloon#" + this.name + "(" + this.id + ") unregistered from weather tower.");
            AvajLauncher.writer.newLine();
        }
    }

    public void registerTower(WeatherTower weatherTower) throws IOException {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        AvajLauncher.writer.write("Tower says: Baloon#" + this.name + "(" + this.id + ") registered to weather tower.");
        AvajLauncher.writer.newLine();
    }
    
}
