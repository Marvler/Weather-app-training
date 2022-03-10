package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WeatherData {

    private final String cityName;
    private final double temperature;
    private final long pressure;
    private final long humidity;
    private final String windDirection;
    private final double windSpeed;

    @Override
    public String toString() {
        return "Weather Data for city: " + cityName + "\n" +
                "temperature= " + temperature + "\n"+
                "pressure= " + pressure + "\n"+
                "humidity= " + humidity + "\n"+
                "windDirection= " + windDirection + "\n"+
                "windSpeed= " +  windSpeed + "\n\n";
    }


}
