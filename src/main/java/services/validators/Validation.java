package services.validators;

import services.view.UserInterface;

public class Validation {

    public boolean validateTheCoordinates(String coordinates) {
        return (coordinates.matches("^([1-8]?[0-9]|90)(N|S),\\s*(180|(1[0-7][0-9])|[1-9][0-9]|[0-9])(E|W)$"));
    }

    public boolean validateIfCityNameIsEmpty(String city) {
        return city == null || city.length() < 1;
    }

    public boolean validateIfCountryNameIsEmpty(String country) {
        return country == null || country.length() < 1;

    }

    public static String returnIfNotNullOrEmpty(String input) {
        if (input.isEmpty() || input.isBlank()) {
            input = UserInterface.getInformationMessage();
        }
        return input;
    }

}
