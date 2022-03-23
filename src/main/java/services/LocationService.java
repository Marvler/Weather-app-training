package services;

import model.Location;
import services.validators.Validation;
import view.UserInterface;


public class LocationService {

    private final static Validation validation = new Validation();

    public Location getLocation() {

        String longitudeAndLatitude = coordinatesService();
        String city = cityService();
        String region = UserInterface.getRegionData();
        String countryName = countryService();

        return new Location(longitudeAndLatitude, city, region, countryName);
    }

    private static String coordinatesService() {
        String result = UserInterface.getCoordinatesInformation();
        while (!validation.validateTheCoordinates(result)) {
            result = UserInterface.getInformationMessage();
        }
        return result;
    }

    private static String cityService() {
        String result = UserInterface.getCityData();
        while (validation.validateIfCityNameIsEmpty(result) && validation.validateIfCityExists(result)) {
            result = UserInterface.getInformationMessage();
        }
        return result;
    }

    private static String countryService() {
        String result = UserInterface.getCountryData();
        while (validation.validateIfCountryNameIsEmpty(result)) {
            result = UserInterface.getInformationMessage();
        }
        return result;
    }


}
