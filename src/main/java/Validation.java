public class Validation {


    public boolean validateTheCoordinates(String country) {
        return country.matches("^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$");
    }

    public boolean validateIfCityNameIsEmpty(String city) {
        return city == null || city.length() == 0;
    }

    public boolean validateIfCountryNameIsEmpty(String country) {
        return country == null || country.length() == 0;
    }

}
