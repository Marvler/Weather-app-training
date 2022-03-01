package services.view;

import services.LocationService;
import services.readers.ReaderFromFile;
import services.validators.Validation;
import services.writers.WriterAvgDataToFile;
import services.writers.WriterToFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class UserInterface {

    static final String START_MENU_PATH = "src/main/resources/textMenuSchems/legend.txt";
    static Scanner scanner = new Scanner(System.in);
    static WriterToFile writer = new WriterToFile("main","locations_data.csv");
    static LocationService locServ = new LocationService();


    public static void legend() throws IOException
    {
        try (Stream<String> stream = Files.lines(Paths.get(START_MENU_PATH))) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("ERROR : Unable to read file");
        }
        chooseOptionInStartMenuSwitch();
    }

    public static void chooseOptionInStartMenuSwitch() throws IOException{
        int userChoice = scanner.nextInt();
        boolean shouldContinue = true;

        while (shouldContinue) {
            switch (userChoice) {
                case 1 -> {
                    writer.writeLocationToFile(locServ.getLocation());
                    legend();
                }
                case 2 -> {
                    ReaderFromFile.readFromFile("main","locations_data.csv");
                    legend();
                }
                case 3 -> {
                    WriterAvgDataToFile writerAvgDataToFile = new WriterAvgDataToFile();
                    writerAvgDataToFile.writeAvarageDataToFile();
                    ReaderFromFile.readFromFile("main", "weather_data.csv");
                }

                case 4 -> shouldContinue = false;
                default -> System.out.println("Choose option 1-4");
            }
            break;
        }
    }

    public static String getInformationMessage() {
        System.out.println("Invalid data provided! Please type again!");
        return Validation.returnIfNotNullOrEmpty(scanner.next());
    }

    public static String getMessage() {
        return Validation.returnIfNotNullOrEmpty(scanner.next());
    }


}
