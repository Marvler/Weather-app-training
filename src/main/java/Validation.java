public class Validation {


    public String validateTheCoordinates(String coordinates) {
        if (!coordinates.matches("^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$")) {
            coordinates = UserInterface.getInformationMessage();
        }
        return coordinates;
    }

    public String validateIfCityNameIsEmpty(String city) {
        if (city == null || city.length() == 0) {
            city = UserInterface.getInformationMessage();
        }
        return city;
    }

    public String validateIfCountryNameIsEmpty(String country) {
        if (country == null || country.length() == 0) {
            country = UserInterface.getInformationMessage();
        }
        return country;
    }

}
