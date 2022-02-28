import java.util.Scanner;
import java.util.UUID;

public class LocationService {

    private final static Validation validation = new Validation();

    private final static Scanner scanner = new Scanner(System.in);

    public static Location getLocation() {

        String longitudeAndLatitude = getLongitudeAndLatitude();
        String city = getCity();
        String region = getRegion();
        String countryName = getCountryName();

        return new Location(UUID.randomUUID(), longitudeAndLatitude, city, region, countryName);

    }

    private static String getLongitudeAndLatitude() {
        System.out.println("Enter coordinates in format: [longitude, latitude]");
        return validation.validateTheCoordinates(scanner.nextLine());
    }

    private static String getCity() {
        System.out.println("Enter city name");
        return validation.validateIfCityNameIsEmpty(scanner.nextLine());
    }

    private static String getRegion() {
        System.out.println("Enter region (optional)");
        return scanner.nextLine();
    }

    private static String getCountryName() {
        System.out.println("Enter country");
        return validation.validateIfCountryNameIsEmpty(scanner.nextLine());
    }


}
