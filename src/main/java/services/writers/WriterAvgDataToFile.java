package services.writers;

import model.WeatherData;
import services.readers.ReaderFromFile;
import services.readers.ReaderFromUrl;

import java.io.IOException;
import java.util.HashSet;

public class WriterAvgDataToFile {

    public void writeAverageDataToFile() throws IOException {
        HashSet<String> cities = ReaderFromFile.getAllCities("main", "locations_data.csv");
        WriterToFile writerToFile = new WriterToFile("main", "weather_data.csv");
        for (String city : cities
        ) {
            WeatherData dataFromOpenWeather = ReaderFromUrl.getCurrentDataFromOpenWeatherMap(city);
            System.out.println(dataFromOpenWeather);
            WeatherData dataFromWeatherStack = ReaderFromUrl.getCurrentDataFromWeatherStack(city);
            System.out.println(dataFromWeatherStack);
            WeatherData dataFromWeatherBit = ReaderFromUrl.getCurrentDataFromWeatherBit(city);
            System.out.println(dataFromWeatherBit);


            int NUMBER_OF_WEATHER_SITES = 3;
            writerToFile.writeLocationToFile(new WeatherData(city,
                    ((dataFromOpenWeather.getTemperature() + dataFromWeatherStack.getTemperature() + dataFromWeatherBit.getTemperature()) / NUMBER_OF_WEATHER_SITES),
                    ((dataFromOpenWeather.getPressure() + dataFromWeatherStack.getPressure() + dataFromWeatherBit.getPressure()) / NUMBER_OF_WEATHER_SITES),
                    ((dataFromOpenWeather.getHumidity() + dataFromWeatherStack.getHumidity() + dataFromWeatherBit.getHumidity()) / NUMBER_OF_WEATHER_SITES),
                    dataFromOpenWeather.getWindDirection(),
                    ((dataFromOpenWeather.getWindSpeed() + dataFromWeatherStack.getWindSpeed()) + dataFromWeatherBit.getWindSpeed() / NUMBER_OF_WEATHER_SITES)));
        }
    }
}
