package model;

import java.util.UUID;

public class Location {

    private UUID id;
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

    public UUID getId() {
        return id;
    }

    public String getLongitudeAndLatitude() {
        return longitudeAndLatitude;
    }

    public void setLongitudeAndLatitude(String longitudeAndLatitude) {
        this.longitudeAndLatitude = longitudeAndLatitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return id + "," + longitudeAndLatitude + "," + city + "," + region + "," + countryName + "\n";
    }
}
