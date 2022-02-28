import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class UserInterface {

    private static final String START_MENU_PATH = "src/main/resources/textMenuSchems/startMenu.txt";
    static Scanner scanner = new Scanner(System.in);

    public static void startMenu() {
        try (Stream<String> stream = Files.lines(Paths.get(START_MENU_PATH))) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("ERROR : Unable to read file");
        }
    }

    public static void chooseOptionInStartMenuSwitch() {
        int userChoice = scanner.nextInt();
        boolean shouldContinue = true;

        while (shouldContinue) {
            switch (userChoice) {
//                case 1 -> Add specific locations to the file.
//                case 2 -> Display currently added locations.
//                case 3 -> Download weather values.
                case 4 -> shouldContinue = false;
                default -> System.out.println("Choose option 1-4");
            }
        }
    }
}
