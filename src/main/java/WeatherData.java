public class WeatherData {

    private final String cityName;
    private final double temperature;
    private final long pressure;
    private final long humidity;
    private final String windDirection;
    private final double windSpeed;

    public WeatherData(String cityName, double temperature, long pressure, long humidity, String windDirection, double windSpeed) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
    }

    @Override
    public String toString() {
        return "Weather Data for city: " + cityName + "\n" +
                "temperature=" + temperature + "\n"+
                "pressure=" + pressure + "\n"+
                "humidity=" + humidity + "\n"+
                "windDirection=" + windDirection + "\n"+
                "windSpeed=" + windSpeed;
    }
}
