package model;

import dao.LocationDAO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
//import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "weather_data")
public class WeatherData {

    @Id
    @Column(name = "weather_data_id")
    private UUID id;
    @Column(name = "city_name")
    private String cityName;
    @Column(name = "temperatur")
    private double temperature;
    @Column(name = "atmospheric_pressure")
    private long pressure;
    @Column(name = "humidity")
    private long humidity;
    @Column(name = "wind_direction")
    private String windDirection;
    @Column(name = "wind_speed")
    private double windSpeed;
    @Column(name = "date")
    private LocalDate date;

    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    public WeatherData(String cityName, double temperature, long pressure, long humidity, String windDirection, double windSpeed) {
        this.id = UUID.randomUUID();
        this.cityName = cityName;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.date = LocalDate.now();
        this.location = new LocationDAO().findByCity(cityName);
    }

    public WeatherData(String cityName, double temperature, long pressure, long humidity, String windDirection, double windSpeed, LocalDate localDate) {
        this.id = UUID.randomUUID();
        this.cityName = cityName;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.date = localDate;
        this.location = new LocationDAO().findByCity(cityName);

    }

    public WeatherData(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Weather Data for city: " + cityName + "\n" +
                "temperature= " + temperature + "\n"+
                "pressure= " + pressure + "\n"+
                "humidity= " + humidity + "\n"+
                "windDirection= " + windDirection + "\n"+
                "windSpeed= " +  windSpeed + "\n\n";
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        WeatherData that = (WeatherData) o;
//        return id != null && Objects.equals(id, that.id);
//    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
