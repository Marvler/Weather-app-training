package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "location")
public class Location {

    @Id
    @Column(name = "uuid_id")
    private UUID id;

    @Column(name = "long_lat")
    private String longitudeAndLatitude;

    @Column(name = "city")
    private String city;

    @Column(name = "region")
    private String region;

    @Column(name = "country_name")
    private String countryName;

    public Location(String longitudeAndLatitude, String city, String region, String countryName) {
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
