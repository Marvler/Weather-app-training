package services.writers;

import model.WeatherData;
import services.readers.ReaderFromFile;
import services.readers.ReaderFromUrl;
import services.validators.Validation;

import java.io.IOException;

import java.util.HashSet;

public class WriterAvgData {

    public void writeAverageDataToFile() throws IOException {
        HashSet<String> cities = ReaderFromFile.getAllCities("main", "locations_data.csv");
        WriterToFile writerToFile = new WriterToFile("main", "weather_data.csv");
        Validation validation = new Validation();
        for (String city : cities
        ) {
            if (validation.validateIfCityExists(city))
                writerToFile.writeWeatherDataToFile(getAverageWeatherConditionsForCity(city));
        }
    }

    public WeatherData getAverageWeatherConditionsForCity(String city) throws IOException {
        int NUMBER_OF_WEATHER_SITES = 3;
        WeatherData dataFromOpenWeather = ReaderFromUrl.getCurrentDataFromOpenWeatherMap(city);
        WeatherData dataFromWeatherStack = ReaderFromUrl.getCurrentDataFromWeatherStack(city);
        WeatherData dataFromWeatherBit = ReaderFromUrl.getCurrentDataFromWeatherBit(city);


        double avgTemperature = Math.round(((dataFromOpenWeather.getTemperature() + dataFromWeatherStack.getTemperature() + dataFromWeatherBit.getTemperature()) / NUMBER_OF_WEATHER_SITES) * 10) / 10D;
        double avgWindSpeed = Math.round(((dataFromOpenWeather.getWindSpeed() + dataFromWeatherStack.getWindSpeed()) + dataFromWeatherBit.getWindSpeed() / NUMBER_OF_WEATHER_SITES) * 10) / 10D;


        return new WeatherData(city, avgTemperature,
                ((dataFromOpenWeather.getPressure() + dataFromWeatherStack.getPressure() + dataFromWeatherBit.getPressure()) / NUMBER_OF_WEATHER_SITES),
                ((dataFromOpenWeather.getHumidity() + dataFromWeatherStack.getHumidity() + dataFromWeatherBit.getHumidity()) / NUMBER_OF_WEATHER_SITES),
                dataFromOpenWeather.getWindDirection(),
                avgWindSpeed);
    }

}
