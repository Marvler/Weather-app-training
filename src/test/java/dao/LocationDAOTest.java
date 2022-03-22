package dao;

import lombok.extern.log4j.Log4j2;
import model.Location;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
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
        locationDAO = new LocationDAO();
    }

    @Test(priority = 1)
    public void shouldSaveLocation() {
        Location location = new Location("52N,21E", "Poznan", "wlk", "Poland");
        locationDAO.save(location);

        Location addedLocation = locationDAO.findByCity("Poznan");

        Assert.assertEquals(addedLocation.getCity(), "Poznan");
        Assertions.assertEquals(location.getCity(), "Poznan");
    }

    @Test(priority = 2)
    public void shouldFindLocationByCity() {
        Location foundLocation = locationDAO.findByCity("Poznan");

        Assert.assertEquals(foundLocation.getCity(), "Poznan");
    }

    @Test(priority = 3)
    public void shouldDeleteLocation() {
        Location locationBeforeDelete = locationDAO.findByCity("Poznan");

        locationDAO.delete(locationBeforeDelete);

        Location locationAfterDelete = locationDAO.findByCity("Poznan");

        Assert.assertNotNull(locationBeforeDelete);
        Assert.assertNull(locationAfterDelete);
    }

    @Test(priority = 4)
    public void shouldUpdateLocation() {
        Location location = new Location("52N,21E", "Gdansk", "pom", "Poland");
        locationDAO.save(location);

        Location locationToUpdate = locationDAO.findByCity("Gdansk");
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

    @Test(priority = 6, expectedExceptions = NoResultException.class)
    public void shouldUpdateByCity() {
        Location location = new Location("52N,21E", "Szczecin", "lub", "Poland");
        locationDAO.save(location);

        Location locationToUpdate = locationDAO.findByCity("Szczecin");
        String previousCity = locationToUpdate.getCity();

        locationToUpdate.setCity("Katowice");
        locationDAO.updateByCity(locationToUpdate.getCity());

        Location locationAfterUpdate = locationDAO.findByCity("Katowice");

        assertNotNull(locationToUpdate);
        assertNotEquals(previousCity, locationAfterUpdate.getCity());
    }

    @Test(priority = 7)
    public void shouldDeleteByCity() {
        Location locationBeforeDelete = locationDAO.findByCity("Warsaw");

        locationDAO.deleteByCity(locationBeforeDelete.getCity());

        Location locationAfterDelete = locationDAO.findByCity("Warsaw");

        Assert.assertNotNull(locationBeforeDelete);
        Assert.assertNull(locationAfterDelete);
    }

    @AfterSuite
    static void cleanUp() {
        Location locationToClean = locationDAO.findByCity("Szczecin");
        locationDAO.delete(locationToClean);
    }

}