import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class UserInterface {

    static final String START_MENU_PATH = "src/main/resources/textMenuSchems/legend.txt";
    static Scanner scanner = new Scanner(System.in);
    static Validation validation = new Validation();


    public static void legend() {
        try (Stream<String> stream = Files.lines(Paths.get(START_MENU_PATH))) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("ERROR : Unable to read file");
        }
        chooseOptionInStartMenuSwitch();
    }

    public static void chooseOptionInStartMenuSwitch() {
        int userChoice = scanner.nextInt();
        boolean shouldContinue = true;

        while (shouldContinue) {
            switch (userChoice) {
                case 1 -> {
                    WriterToFile.writeLocationToFile(LocationService.getLocation());
                    legend();
                }
                case 2 -> {
                    ReaderFromFile.readFromFile();
                    legend();
//                case 3 -> Download weather values.
                }
                case 4 -> shouldContinue = false;
                default -> System.out.println("Choose option 1-4");
            }
            break;
        }
    }

    protected static String getInformationMessage() {
        System.out.println("Invalid data provided! Please type again!");
        return validation.validateIfCityNameIsEmpty(scanner.nextLine());

    }

}
