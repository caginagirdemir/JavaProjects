package avajlauncher.tower;

import avajlauncher.aircraft.Coordinates;
import avajlauncher.provider.WeatherProvider;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    void changeWeather() throws Exception {
        this.conditionsChanged();
    }

}