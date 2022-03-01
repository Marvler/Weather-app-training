package services.validators;

import services.view.UserInterface;

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

    public static String returnIfNotNullOrEmpty(String input){
        if(input.isEmpty() || input.isBlank()){
            input = UserInterface.getInformationMessage();
        }
        return input;
    }

}
