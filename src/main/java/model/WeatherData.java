package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Table(name = "weather_data")
@Entity
@NoArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WeatherData that = (WeatherData) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
