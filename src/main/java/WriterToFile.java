import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WriterToFile {
    static final String LOCATION_FILE = "src/main/resources/weather_files";

    static void writeLocationToFile(Location location) {
        Path path = Paths.get(LOCATION_FILE);
        try {
            Files.write(path, location.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (
                IOException e) {
            System.err.println("Unable to write a file");
        }
    }
}
