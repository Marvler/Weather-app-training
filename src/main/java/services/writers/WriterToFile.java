package services.writers;

import model.Location;
import model.WeatherData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WriterToFile {
    private String LOCATION_FILE;

    public WriterToFile(String mainLocation, String fileName) {
        LOCATION_FILE = "src/" + mainLocation +"/resources/" + fileName;
    }

     public void writeLocationToFile(Location location) {
        Path path = Paths.get(LOCATION_FILE);
        try {
            Files.write(path, location.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (
                IOException e) {
            System.err.println("Unable to write a file");
        }
    }

    public void writeLocationToFile(WeatherData weatherData) {
        Path path = Paths.get(LOCATION_FILE);
        try {
            Files.write(path, weatherData.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (
                IOException e) {
            System.err.println("Unable to write a file");
        }
    }


}
