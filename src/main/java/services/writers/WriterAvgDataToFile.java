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
            WeatherData dataFromWeatherStack = ReaderFromUrl.getCurrentDataFromWeatherStack(city);

            writerToFile.writeLocationToFile(new WeatherData(city,
                    ((dataFromOpenWeather.getTemperature() + dataFromWeatherStack.getTemperature()) / 2),
                    ((dataFromOpenWeather.getPressure() + dataFromWeatherStack.getPressure()) / 2),
                    ((dataFromOpenWeather.getHumidity() + dataFromWeatherStack.getHumidity()) / 2),
                    dataFromOpenWeather.getWindDirection(),
                    ((dataFromOpenWeather.getWindSpeed() + dataFromWeatherStack.getWindSpeed()) / 2)));
        }
    }
}
