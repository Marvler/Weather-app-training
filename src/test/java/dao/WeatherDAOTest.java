package dao;

import lombok.extern.log4j.Log4j2;
import model.WeatherData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

@Log4j2
class WeatherDAOTest {

    WeatherData weatherData = new WeatherData("XYZ", 123L, 1025L, 54L, "SE", 15.5);
    private final WeatherDAO weatherDAO;

    public WeatherDAOTest() {
        this.weatherDAO = new WeatherDAO();
    }

    @Test
    public void shouldSaveWeatherData() {
        weatherDAO.save(weatherData);
        WeatherData addedWeatherData = weatherDAO.findNewestRecordByCity("XYZ");
        Assertions.assertEquals(addedWeatherData.getCityName(), "XYZ");
        weatherDAO.deleteByID(weatherData.getId());
    }

    @Test
    public void shouldFindNewestRecordByCity() {
        weatherDAO.save(weatherData);
        WeatherData addedWeatherData = weatherDAO.findNewestRecordByCity("XYZ");
        Assertions.assertEquals(addedWeatherData.getCityName(), "XYZ");
        weatherDAO.deleteNewestRecordByCity(weatherData.getCityName());
    }

    @Test
    public void shouldFindNewestRecordByCityAndDate() {
        weatherDAO.save(weatherData);
        WeatherData addedWeatherData = weatherDAO.findNewestRecordByCityAndDate("XYZ", LocalDate.now());
        Assertions.assertEquals(addedWeatherData.getCityName(), "XYZ");
        Assertions.assertEquals(addedWeatherData.getDate(), LocalDate.now());
        weatherDAO.deleteNewestRecordByCity(weatherData.getCityName());
    }

    @Test
    public void shouldFindRecordByID() {
        WeatherData weatherData = new WeatherData("Gdańsk", 123L, 1025L, 54L, "SE", 15.5);
        weatherDAO.save(weatherData);
        WeatherData weatherDataFromDB = weatherDAO.findByID(weatherData.getId());
        Assertions.assertNotNull(weatherDataFromDB);
        weatherDAO.deleteByID(weatherData.getId());
    }

    @Test
    public void shouldFindListOfRecordsByDate(){
        for (int i = 0; i < 3; i++) {
            weatherDAO.save(new WeatherData("XYZ", 123L, 1025L, 54L, "SE", 15.5, LocalDate.now().plusDays(100)));
        }
        List <WeatherData>weatherData = weatherDAO.findByDate(LocalDate.now().plusDays(100));
        Assertions.assertEquals(weatherData.size(),3);
        weatherDAO.deleteAllRecordsByCity("XYZ");
    }

    @Test
    void shouldDeleteNewestRecordByCity() {
        weatherDAO.save(weatherData);
        weatherDAO.deleteNewestRecordByCity(weatherData.getCityName());
        Assertions.assertNull(weatherDAO.findNewestRecordByCity(weatherData.getCityName()));
    }

    @Test
    void shouldDeleteNewestRecordByCityAndDate() {
        weatherDAO.save(weatherData);
        weatherDAO.deleteNewestRecordByCityAndDate(weatherData.getCityName(), LocalDate.now());
        Assertions.assertNull(weatherDAO.findNewestRecordByCityAndDate(weatherData.getCityName(), LocalDate.now()));
    }

    @Test
    public void shouldDeleteById() {
        weatherDAO.save(weatherData);
        weatherDAO.deleteByID(weatherData.getId());
        Assertions.assertNull(weatherDAO.findByID(weatherData.getId()));
    }

    @Test
    public void shouldDeleteAllRecordByCity() {
        for (int i = 0; i < 3; i++) {
            weatherDAO.save(new WeatherData("XYZ", 123L, 1025L, 54L, "SE", 15.5));
        }
        weatherDAO.deleteAllRecordsByCity("XYZ");
        Assertions.assertNull(weatherDAO.findNewestRecordByCity("XYZ"));
    }

    @Test
    public void shouldDeleteAllRecordsByCityAndDate() {
        for (int i = 0; i < 3; i++) {
            weatherDAO.save(new WeatherData("XYZ", 123L, 1025L, 54L, "SE", 15.5, LocalDate.now().minusDays(2)));
        }
        weatherDAO.deleteAllRecordsByCityAndDate("XYZ", LocalDate.now().minusDays(2));
        Assertions.assertNull(weatherDAO.findNewestRecordByCityAndDate("XYZ", LocalDate.now().minusDays(2)));
    }

    @Test
    public void shouldDeleteAllRecordsByDate(){
        for (int i = 0; i < 3; i++) {
            weatherDAO.save(new WeatherData("XYZ", 123L, 1025L, 54L, "SE", 15.5, LocalDate.now().plusDays(100)));
        }
        weatherDAO.deleteAllRecordsByDate(LocalDate.now().plusDays(100));
        Assertions.assertEquals(weatherDAO.findByDate(LocalDate.now().plusDays(100)).size(),0);
    }

}