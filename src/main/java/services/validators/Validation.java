package services.validators;

public class Validation {


    public boolean validateTheCoordinates(String coordinates) {
        return (!coordinates.matches("^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?)(N|S)?,\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)(E|W)?$"));
    }

    public boolean validateIfCityNameIsEmpty(String city) {
        return city == null || city.length() < 1;
    }

    public boolean validateIfCountryNameIsEmpty(String country) {
        return country == null || country.length() < 1;

    }

    public static boolean validateIfNotNullOrEmpty(String input){
        return input.isEmpty() || input.isBlank();
    }

}
