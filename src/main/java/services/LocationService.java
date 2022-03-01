package services;

import model.Location;
import services.validators.Validation;
import services.view.UserInterface;

import java.util.UUID;

public class LocationService {

    private final static Validation validation = new Validation();

    public Location getLocation() {

        String longitudeAndLatitude = getLongitudeAndLatitude();
        String city = getCity();
        String region = getRegion();
        String countryName = getCountryName();

        return new Location(UUID.randomUUID(), longitudeAndLatitude, city, region, countryName);

    }

    private static String getLongitudeAndLatitude() {
        System.out.println("Enter coordinates in format: [longitude, latitude]");
        String result = UserInterface.getMessage();
        while (validation.validateTheCoordinates(result)) {
            result = UserInterface.getInformationMessage();
        }
        return result;
    }

    private static String getCity() {
        System.out.println("Enter city name");
        String result = UserInterface.getMessage();
        while (validation.validateIfCityNameIsEmpty(result)) {
            result = UserInterface.getInformationMessage();
        }
        return result;
    }

    private static String getRegion() {
        System.out.println("Enter region (optional)");
        return UserInterface.getMessage();
    }

    private static String getCountryName() {
        System.out.println("Enter country");
        String result = UserInterface.getMessage();
        while (validation.validateIfCountryNameIsEmpty(result)) {
            result = UserInterface.getInformationMessage();
        }
        return result;

    }


}
