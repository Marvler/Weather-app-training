import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ReaderFromUrl {

    public static WeatherData getCurrentDataFromOpenWeatherMap(String cityName) throws IOException {


        String sURL = "https://api.openweathermap.org/data/2.5/weather?q="+cityName+"&&units=metric&appid=2e17c40d72b22d8b8202de7c3e7c7358";

        JsonObject rootobj = getJsonObject(sURL);
        double temp = rootobj.get("main").getAsJsonObject().get("temp").getAsDouble();
        long pressure = rootobj.get("main").getAsJsonObject().get("pressure").getAsLong();
        long humidity = rootobj.get("main").getAsJsonObject().get("humidity").getAsLong();
        long windDirection = rootobj.get("wind").getAsJsonObject().get("deg").getAsLong();
        double windSpeed = rootobj.get("wind").getAsJsonObject().get("speed").getAsDouble();

        return new WeatherData(cityName,temp,pressure,humidity,WindConverter.convertWindDegToDirection(windDirection),windSpeed);


    }

    public static WeatherData getCurrentDataFromWeatherStack(String cityName) throws IOException {
        String sURL = "http://api.weatherstack.com/current?access_key=da728590cf8879e1c808773661cbfdb6&query="+cityName+"&units=m";
        JsonObject rootobj = getJsonObject(sURL);

        double temp = rootobj.get("current").getAsJsonObject().get("temperature").getAsDouble();
        long humidity = rootobj.get("current").getAsJsonObject().get("humidity").getAsLong();
        long pressure = rootobj.get("current").getAsJsonObject().get("pressure").getAsLong();
        String windDirection = rootobj.get("current").getAsJsonObject().get("wind_dir").getAsString();
        double windSpeed = rootobj.get("current").getAsJsonObject().get("wind_speed").getAsDouble();

        return new WeatherData(cityName,temp,pressure,humidity,windDirection,windSpeed);
    }

    private static JsonObject getJsonObject(String sURL) throws IOException {
        URLConnection request = getUrlConnection(sURL);
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootobj = root.getAsJsonObject();
        return rootobj;
    }

    private static URLConnection getUrlConnection(String sURL) throws IOException {
        URL url = new URL(sURL);
        URLConnection request = url.openConnection();
        request.connect();
        return request;
    }
}
