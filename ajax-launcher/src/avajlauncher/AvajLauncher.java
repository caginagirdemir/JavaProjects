package avajlauncher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import avajlauncher.aircraft.AircraftFactory;
import avajlauncher.exceptions.InvalidLineException;
import avajlauncher.flyable.Flyable;
import avajlauncher.tower.WeatherTower;
import avajlauncher.tower.WeatherUpdater;

public class AvajLauncher {

    public static BufferedWriter writer = null;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java AvajLauncher <scenario>");
            System.exit(1);
        }

        WeatherTower weatherTower = new WeatherTower();
        WeatherUpdater weatherUpdater = new WeatherUpdater(weatherTower); // because weatherTower.conditionsChanged() is package private, I did a wrapper class
        int simulationsCount = 0;
        List<Flyable> flyables = new ArrayList<>();

        try {
            File file = new File(args[0]);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            AircraftFactory aircraftFactory = new AircraftFactory();

            String line = reader.readLine();

            if (line == null) {
                reader.close();
                throw new InvalidLineException("Invalid scenario file");
            }

            simulationsCount = Integer.parseInt(line);

            while ((line = reader.readLine()) != null) {
                String[] lineSplit = line.split(" ");
                
                if (lineSplit.length != 5)
                {
                    reader.close();
                    throw new InvalidLineException("Invalid line: " + line);
                }

                int latitude = Integer.parseInt(lineSplit[2]);
                int longitude = Integer.parseInt(lineSplit[3]);
                int height = Integer.parseInt(lineSplit[4]);

                if (latitude < 0 || longitude < 0 || height < 0) {
                    reader.close();
                    throw new InvalidLineException("Invalid coordinates on line: " + line);
                }

                flyables.add(aircraftFactory.newAircraft(lineSplit[0], lineSplit[1], latitude, longitude, height));
            }

            reader.close();

            writer = new BufferedWriter(new FileWriter("simulation.txt"));

        } catch (Exception e) {
            System.out.println("Error while parsing: " + e.getMessage());
            System.exit(1);
        }

        try
        {
            for (Flyable flyable : flyables) {
                flyable.registerTower(weatherTower);
            }
    
            for (int i = 1; i <= simulationsCount; i++) {
                weatherUpdater.updateWeather();
            }
        }
        catch (Exception e)
        {
            System.out.println("Error while running simulation: " + e.getMessage());
            System.exit(1);
        }

        try
        {
            writer.flush();
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println("Error while closing simulation.txt: " + e.getMessage());
            System.exit(1);
        }

    }

}