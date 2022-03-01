import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class ReaderFromFile {
    private static final String SEPARATOR = ",";
    static final String LOCATION_FILE = "src/main/resources/weather_files";

    static void readFromFile() {
        try (Stream<String> stream = Files.lines(Paths.get(LOCATION_FILE))) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("ERROR: Unable to read the file.");
        }
    }

    public static Set<String> getAllCities()  {
        List<String> cityLines = null;
        try {
            cityLines = Files.readAllLines(Paths.get(LOCATION_FILE));
        } catch (IOException e) {
            System.err.println("ERROR: Unable to read the file.");
        }
        Set<String> cities = new HashSet<>();
        for (String line : cityLines) {
            String[] city = line.split(SEPARATOR);
            cities.add(city[3]);
        }
        return cities;
    }

//    static Set<String> getCities() {
//        try (Stream<String> stream = Files.lines(Paths.get(LOCATION_FILE))) {
//            stream.map(o->o.split(","));
//        } catch (IOException e) {
//            System.err.println("ERROR: Unable to read the file.");
//        }
//        return null;
//    }

}
