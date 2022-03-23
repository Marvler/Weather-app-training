package services.validators;

import view.UserInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public boolean validateIfCityExists(String cityName) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/allCities.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (List<String> line : records) {
            if (line.get(1).equals(cityName) || line.get(3).contains(cityName)) {
                return true;
            }
        }
        return false;
    }

}
