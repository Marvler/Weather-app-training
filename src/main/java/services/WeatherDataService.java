package services;

import model.WeatherData;
import services.validators.Validation;
import view.UserInterface;

import java.time.LocalDate;

public class WeatherDataService {

    public WeatherData getWeatherData() {

        String date = dateService();

        return new WeatherData(LocalDate.parse(date));
    }

    public static String dateService() {
        String result = UserInterface.getDateInformation();
        while (!Validation.isDateValid(result)) {
            result = UserInterface.getInformationMessage();
        }
        return result;
    }
}
