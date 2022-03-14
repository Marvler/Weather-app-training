package dao;

import lombok.extern.log4j.Log4j2;
import model.Location;
import org.junit.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import javax.persistence.NoResultException;
import java.util.List;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

@Log4j2
public class LocationDAOTest {

    private static LocationDAO locationDAO = null;

    public LocationDAOTest() {
        this.locationDAO = new LocationDAO();
    }

    @Test(priority = 1)
    public void shouldSaveLocation() {
        Location location = new Location("52N,21E", "Krakow", "mal", "Poland");

        locationDAO.save(location);

        Location addedLocation = locationDAO.findByCity("Krakow");

        Assert.assertEquals(addedLocation.getCity(), "Krakow");
    }

    @Test(priority = 2)
    public void shouldFindLocationByCity() {
        Location foundLocation = locationDAO.findByCity("Krakow");

        Assert.assertEquals(foundLocation.getCity(), "Krakow");
    }

    @Test(priority = 3, expectedExceptions = NoResultException.class)
    public void shouldDeleteLocation() {
        Location locationBeforeDelete = locationDAO.findByCity("Krakow");

        locationDAO.delete(locationBeforeDelete);

        Location locationAfterDelete = locationDAO.findByCity("Krakow");

        Assert.assertNotNull(locationBeforeDelete);
        Assert.assertNull(locationAfterDelete);
    }

    @Test(priority = 4)
    public void shouldUpdateLocation() {
        Location location = new Location("52N,21E", "Poznan", "wlk", "Poland");

        locationDAO.save(location);

        Location locationToUpdate = locationDAO.findByCity("Poznan");
        String previousCity = locationToUpdate.getCity();

        locationToUpdate.setCity("Warsaw");
        locationDAO.update(locationToUpdate);

        Location locationAfterUpdate = locationDAO.findByCity("Warsaw");

        assertNotNull(locationToUpdate);
        assertNotEquals(previousCity, locationAfterUpdate.getCity());

    }

    @Test(priority = 5)
    public void shouldFindAllLocations() {
        List<Location> allLocations = locationDAO.findAllLocations();

        Assert.assertFalse(allLocations.isEmpty());
    }


    @AfterSuite
    static void cleanUp() {
        Location locationToClean = locationDAO.findByCity("Warsaw");
        locationDAO.delete(locationToClean);
    }

}