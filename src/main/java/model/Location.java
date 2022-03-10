package model;

import lombok.Data;
import java.util.UUID;

@Data
public class Location {

    private final UUID id;
    private String longitudeAndLatitude;
    private String city;
    private String region;
    private String countryName;

    public Location(UUID id, String longitudeAndLatitude, String city, String region, String countryName) {
        this.id = UUID.randomUUID();
        this.longitudeAndLatitude = longitudeAndLatitude;
        this.city = city;
        this.region = region;
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return id + "," + longitudeAndLatitude + "," + city + "," + region + "," + countryName + "\n";
    }
}
