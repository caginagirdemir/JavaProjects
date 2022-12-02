package avajlauncher.flyable;

import avajlauncher.tower.WeatherTower;

public interface Flyable{
    public void updateConditions() throws Exception; //should override in every flyable objects
    public void registerTower(WeatherTower weatherTower) throws Exception; //should override in every flyable objects
}