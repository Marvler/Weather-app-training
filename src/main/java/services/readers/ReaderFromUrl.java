package services.readers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.WeatherData;
import services.WindConverter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ReaderFromUrl {


    public static WeatherData getCurrentDataFromOpenWeatherMap(String cityName) throws IOException {
        return getWeatherData(cityName, getJsonObject(URLGenerator(cityName, 1)), "main", "wind", "temp",
                "deg", "speed");
    }


    public static WeatherData getCurrentDataFromWeatherStack(String cityName) throws IOException {
        return getWeatherData(cityName, getJsonObject(URLGenerator(cityName, 2)), "current", "current",
                "temperature", "wind_dir", "wind_speed");
    }

    public static WeatherData getCurrentDataFromWeatherBit(String cityName) throws IOException {
        JsonObject rootobj = getJsonObject(URLGenerator(cityName, 3));

        double temp = rootobj.get("data").getAsJsonArray().get(0).getAsJsonObject().get("temp").getAsDouble();
        long humidity = rootobj.get("data").getAsJsonArray().get(0).getAsJsonObject().get("rh").getAsLong();
        long pressure = rootobj.get("data").getAsJsonArray().get(0).getAsJsonObject().get("pres").getAsLong();
        String windDirection = rootobj.get("data").getAsJsonArray().get(0).getAsJsonObject().get("wind_cdir").getAsString();
        double windSpeed = rootobj.get("data").getAsJsonArray().get(0).getAsJsonObject().get("wind_spd").getAsDouble();

        return new WeatherData(cityName, temp, pressure, humidity, windDirection, windSpeed);
    }

    private static WeatherData getWeatherData(String cityName, JsonObject rootobj, String main, String main2, String temp,
                                              String wDirection, String wSpeed) {
        double temperature = rootobj.get(main).getAsJsonObject().get(temp).getAsDouble();
        long pressure = rootobj.get(main).getAsJsonObject().get("pressure").getAsLong();
        long humidity = rootobj.get(main).getAsJsonObject().get("humidity").getAsLong();
        double windSpeed = rootobj.get(main2).getAsJsonObject().get(wSpeed).getAsDouble();
        String windDirection;
        if (wDirection.equals("deg")) {
            windDirection = WindConverter.convertWindDegToDirection(rootobj.get(main2).getAsJsonObject().get(wDirection).getAsLong());
        } else {
            windDirection = rootobj.get(main2).getAsJsonObject().get(wDirection).getAsString();
        }

        return new WeatherData(cityName, temperature, pressure, humidity, windDirection, windSpeed);
    }


    private static JsonObject getJsonObject(String sURL) throws IOException {
        URLConnection request = getUrlConnection(sURL);
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        return root.getAsJsonObject();
    }

    private static URLConnection getUrlConnection(String sURL) throws IOException {
        URL url = new URL(sURL);
        URLConnection request = url.openConnection();
        request.connect();
        return request;
    }

    private static String URLGenerator(String cityName, int weatherWebsite) {
        switch (weatherWebsite) {
            case 1 -> {
                return "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&&units=metric&appid=2e17c40d72b22d8b8202de7c3e7c7358";
            }
            case 2 -> {
                return "http://api.weatherstack.com/current?access_key=da728590cf8879e1c808773661cbfdb6&query=" + cityName + "&units=m";
            }
            case 3 -> {
                return "https://api.weatherbit.io/v2.0/current?city=" + cityName + "&key=93a6237e0a694078804da69e2b755fc5";
            }
        }
        return null;
    }
}
