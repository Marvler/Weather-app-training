package services.view;

import dao.LocationDAO;
import dao.WeatherDAO;
import dao.validators.Validation;
import services.LocationService;
import services.readers.ReaderFromFile;
import services.writers.WriterAvgDataToFile;
import services.writers.WriterToFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.stream.Stream;

public class UserInterface {

    static final String START_MENU_PATH = "src/main/resources/textMenuSchemas/startMenu.txt";
    static final String FILES_MENU_PATH = "src/main/resources/textMenuSchemas/filesMenu.txt";
    static final String DB_START_MENU_PATH = "src/main/resources/textMenuSchemas/databaseStartMenu.txt";
    static final String DB_LOC_MENU_PATH = "src/main/resources/textMenuSchemas/databaseLocationMenu.txt";
    static final String DB_WD_MENU_PATH = "src/main/resources/textMenuSchemas/databaseWeatherDataMenu.txt";
    static Scanner scanner = new Scanner(System.in);
    static WriterToFile writer = new WriterToFile("main", "locations_data.csv");
    static LocationService locServ = new LocationService();
    static LocationDAO locationDAO = new LocationDAO();
    static WeatherDAO weatherDAO = new WeatherDAO();

    private static void readMenuFile(String menuPath) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(menuPath))) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("ERROR : Unable to read file");
        }
    }


    public static void startMenu() throws IOException {
        readMenuFile(START_MENU_PATH);
        startMenuSwitch();
    }

    public static void filesMenu() throws IOException {
        readMenuFile(FILES_MENU_PATH);
        filesMenuSwitch();
    }

    public static void databaseStartMenu() throws IOException {
        readMenuFile(DB_START_MENU_PATH);
        databaseStartMenuSwitch();
    }

    public static void databaseLocationMenu() throws IOException {
        readMenuFile(DB_LOC_MENU_PATH);
        databaseLocationMenuSwitch();
    }

    public static void databaseWeatherDataMenu() throws IOException {
        readMenuFile(DB_WD_MENU_PATH);
        databaseWeatherDataMenuSwitch();
    }


    public static void startMenuSwitch() throws IOException {
        int userChoice = scanner.nextInt();
        boolean shouldContinue = true;

        while (shouldContinue) {
            switch (userChoice) {
                case 1 -> filesMenu();
                case 2 -> databaseStartMenu();
                case 3 -> shouldContinue = false;
                default -> System.out.println("Choose option 1-3");
            }
            break;
        }
    }

    public static void filesMenuSwitch() throws IOException {
        int userChoice = scanner.nextInt();
        boolean shouldContinue = true;

        while (shouldContinue) {
            switch (userChoice) {
                case 1 -> {
                    writer.writeLocationToFile(locServ.getLocation());
                    startMenu();
                }
                case 2 -> {
                    ReaderFromFile.readFromFile("main", "locations_data.csv");
                    startMenu();
                }
                case 3 -> {
                    WriterAvgDataToFile writerAvgDataToFile = new WriterAvgDataToFile();
                    writerAvgDataToFile.writeAverageDataToFile();
                    ReaderFromFile.readFromFile("main", "weather_data.csv");
                }

                case 4 -> startMenu();
                case 5 -> shouldContinue = false;
                default -> System.out.println("Choose option 1-4");
            }
            break;
        }
    }

    public static void databaseStartMenuSwitch() throws IOException {
        int userChoice = scanner.nextInt();
        boolean shouldContinue = true;

        while (shouldContinue) {
            switch (userChoice) {
                case 1 -> databaseLocationMenu();
                case 2 -> databaseWeatherDataMenu();
                case 3 -> startMenu();
                case 4 -> shouldContinue = false;
                default -> System.out.println("Choose option 1-4");
            }
            break;
        }
    }

    public static void databaseLocationMenuSwitch() throws IOException {
        int userChoice = scanner.nextInt();
        boolean shouldContinue = true;

        while (shouldContinue) {
            switch (userChoice) {
                case 1 -> {
                    locationDAO.save(locServ.getLocation());
                    databaseLocationMenu();
                }
                case 2 -> {
                    locationDAO.deleteByCity(getCityData());
                    databaseLocationMenu();
                }
                case 3 -> {
                    locationDAO.updateByCity(getCityData());
                    databaseLocationMenu();
                }
                case 4 -> {
                    System.out.println(locationDAO.findAllLocations());
                    databaseLocationMenu();
                }
                case 5 -> {
                    System.out.println(locationDAO.findByCity(getCityData()));
                    databaseLocationMenu();
                }
                case 6 -> databaseStartMenu();
                case 7 -> shouldContinue = false;
                default -> System.out.println("Choose option 1-7");
            }
            break;
        }
    }

    public static void databaseWeatherDataMenuSwitch() throws IOException {
        int userChoice = scanner.nextInt();
        boolean shouldContinue = true;

        while (shouldContinue) {
            switch (userChoice) {
                case 1 -> {
                    weatherDAO.findByCity(getCityData());
                    databaseWeatherDataMenu();
                }
                case 2 -> {
                    weatherDAO.findByDate(LocalDate.now());
                    databaseWeatherDataMenu();
                }
                case 3 -> {
                    weatherDAO.deleteAllRecordsByCity(getCityData());
                    databaseWeatherDataMenu();
                }
                case 4 -> {
                    weatherDAO.deleteAllRecordsByDate(LocalDate.now());
                    databaseWeatherDataMenu();
                }
                case 5 -> {
                    weatherDAO.deleteAllRecordsByCityAndDate(getCityData(), LocalDate.now());
                    databaseWeatherDataMenu();
                }
                case 6 -> databaseStartMenu();
                case 7 -> shouldContinue = false;
                default -> System.out.println("Choose option 1-7");
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

    public static String getCoordinatesInformation() {
        System.out.println("Enter coordinates in format: [longitude, latitude]");
        return UserInterface.getMessage();
    }

    public static String getCityData() {
        System.out.println("Enter city name");
        return UserInterface.getMessage();
    }

    public static String getRegionData() {
        System.out.println("Enter region ");
        return UserInterface.getMessage();
    }

    public static String getCountryData() {
        System.out.println("Enter country");
        return UserInterface.getMessage();
    }

}
