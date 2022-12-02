package avajlauncher.tower;

public class WeatherUpdater {
    private WeatherTower tower;

    public WeatherUpdater(WeatherTower tower) {
        this.tower = tower;
    }

    public void updateWeather() throws Exception {
        tower.conditionsChanged();
    }
}
