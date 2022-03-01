package services.readers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class ReaderFromFile {
    private static final String SEPARATOR = ",";

    public static void readFromFile(String location, String fileName) {
        String LOCATION_FILE = "src/" + location + "/resources/" + fileName;
        try (Stream<String> stream = Files.lines(Paths.get(LOCATION_FILE))) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("ERROR: Unable to read the file.");
        }
    }

    public static HashSet<String> getAllCities(String location, String fileName) {
        String LOCATION_FILE = "src/" + location + "/resources/" + fileName;
        List<String> cityLines = null;
        try {
            cityLines = Files.readAllLines(Paths.get(LOCATION_FILE));
        } catch (IOException e) {
            System.err.println("ERROR: Unable to read the file.");
        }
        HashSet<String> cities = new HashSet<>();
        for (String line : cityLines) {
            String[] city = line.split(SEPARATOR);
            cities.add(city[3]);
        }
        return cities;
    }

}
