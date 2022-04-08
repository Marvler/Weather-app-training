package view;

import dao.LocationDAO;
import dao.WeatherDAO;
import services.WeatherDataService;
import services.validators.Validation;
import services.LocationService;
import services.readers.ReaderFromFile;
import services.writers.WriterAvgData;
import services.writers.WriterToFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
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
    static WeatherDataService weatherDataService = new WeatherDataService();

    private static void readMenuFile(String menuPath){
        try (Stream<String> stream = Files.lines(Paths.get(menuPath))) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("ERROR : Unable to read file");
        }
    }

    public static void startMenu() throws IOException {
        while (true) {
            readMenuFile(START_MENU_PATH);
            int userChoice = Integer.parseInt(Validation.isUserChoiceValid(bufferedReader.readLine()));
            switch (userChoice) {
                case 1 -> filesMenu();
                case 2 -> databaseStartMenu();
                case 3 -> {
                    System.out.println("Have a good day!");
                    System.exit(0);
                }
                default -> System.out.println("Choose option 1-3");
            }
        }
    }

    public static void filesMenu() throws IOException {
        while (true) {
            readMenuFile(FILES_MENU_PATH);
            int userChoice = Integer.parseInt(Validation.isUserChoiceValid(bufferedReader.readLine()));
            switch (userChoice) {
                case 1 -> writer.writeLocationToFile(locServ.getLocation());
                case 2 -> ReaderFromFile.readFromFile("main", "locations_data.csv");
                case 3 -> {
                    WriterAvgData writerAvgDataToFile = new WriterAvgData();
                    writerAvgDataToFile.writeAverageDataToFile();
                    ReaderFromFile.readFromFile("main", "weather_data.csv");
                }
                case 4 -> {return;}
                case 5 -> System.exit(0);
                default -> System.out.println("Choose option 1-5");
            }
        }
    }

    public static void databaseStartMenu() throws IOException {
        while (true) {
            readMenuFile(DB_START_MENU_PATH);
            int userChoice = Integer.parseInt(Validation.isUserChoiceValid(bufferedReader.readLine()));
            switch (userChoice) {
                case 1 -> databaseLocationMenu();
                case 2 -> databaseWeatherDataMenu();
                case 3 -> {
                    return;
                }
                case 4 -> System.exit(0);
                default -> System.out.println("Choose option 1-4");
            }
        }
    }

    public static void databaseLocationMenu() throws IOException {
        while (true) {
            readMenuFile(DB_LOC_MENU_PATH);
            int userChoice = Integer.parseInt(Validation.isUserChoiceValid(bufferedReader.readLine()));
            switch (userChoice) {
                case 1 -> locationDAO.save(locServ.getLocation());
                case 2 -> locationDAO.deleteByCity(getCityData());
                case 3 -> locationDAO.updateByCity(getCityData());
                case 4 -> displayResults(locationDAO.findAllLocations());
                case 5 -> locationDAO.findByCity(getCityData());
                case 6 -> {return;}
                case 7 -> System.exit(0);
                default -> System.out.println("Choose option 1-7");
            }
        }
    }

    public static void databaseWeatherDataMenu() throws IOException, DateTimeParseException {
        while (true) {
            readMenuFile(DB_WD_MENU_PATH);
            int userChoice = Integer.parseInt(Validation.isUserChoiceValid(bufferedReader.readLine()));
            switch (userChoice) {
                case 1 -> displayResults(weatherDAO.findByCity(getCityData()));
                case 2 -> displayResults(weatherDAO.findByDate(weatherDataService.getWeatherData().getDate()));
                case 3 -> weatherDAO.deleteAllRecordsByCity(getCityData());
                case 4 -> weatherDAO.deleteAllRecordsByDate(LocalDate.now());
                case 5 -> weatherDAO.deleteAllRecordsByCityAndDate(getCityData(), LocalDate.now());
                case 6 -> weatherDAO.save(new WriterAvgData().getAverageWeatherConditionsForCity(LocationService.cityService()));
                case 7 -> {return;}
                case 8 -> System.exit(0);
                default -> System.out.println("Choose option 1-8");
            }
        }
    }

    public static <T> void displayResults(List<T> listOfResults) {
        for (T result : listOfResults) {
            System.out.println(result);
        }
    }

    public static String getWrongInputMessage() {
        System.out.println("Invalid data provided! Please type again!");
        try {
            return Validation.returnIfNotNullOrEmpty(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getWrongMessageForIntegerInput() {
        System.out.println("Invalid data provided! Please type again!");
        try {
            return Validation.isUserChoiceValid(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    public static String getDateInformation() {
        System.out.println("Enter date [yyyy-mm-dd]");
        return UserInterface.getMessage();
    }
}
