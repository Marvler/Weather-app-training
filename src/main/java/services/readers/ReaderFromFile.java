package services.readers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReaderFromFile {
    private static final String SEPARATOR = ",";
    private static final int CITY_INDEX_FROM_LINE = 3;

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
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(LOCATION_FILE));
        } catch (IOException e) {
            System.err.println("ERROR: Unable to read the file.");
        }

        assert lines != null;
        Set<String> cities = lines.stream()
                .map(line -> line.split(SEPARATOR))
                .map(array -> array[CITY_INDEX_FROM_LINE])
                .collect(Collectors.toSet());

        return (HashSet<String>) cities;
    }

}
