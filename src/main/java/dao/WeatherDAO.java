package dao;

import connection.HibernateUtil;
import model.WeatherData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WeatherDAO {

    private static final Logger logger = LogManager.getLogger(WeatherDAO.class);

    public void save(WeatherData weatherData) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.save(weatherData);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }
    }

    public void deleteNewestRecordByCity(String cityName) {
        deleteWeatherDataFromDB(findNewestRecordByCity(cityName));
    }

    public void deleteNewestRecordByCityAndDate(String cityName, LocalDate localDate) {
        deleteWeatherDataFromDB(findNewestRecordByCityAndDate(cityName, localDate));
    }

    public void deleteByID(UUID id) {
        deleteWeatherDataFromDB(findByID(id));
    }

    public void deleteAllRecordsByCity(String city) {
        deleteWeatherDataBasedOnQuery(city, LocalDate.now(), 1);
    }

    public void deleteAllRecordsByCityAndDate(String city, LocalDate localDate) {
        deleteWeatherDataBasedOnQuery(city, localDate, 2);
    }

    private void deleteWeatherDataFromDB(WeatherData weatherData) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.delete(weatherData);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }
    }

    public WeatherData findNewestRecordByCity(String cityName) {
        return getWeatherComepleteData(cityName,LocalDate.now(),1);
    }


    public WeatherData findNewestRecordByCityAndDate(String cityName, LocalDate localDate) {
        return getWeatherComepleteData(cityName, localDate, 2);
    }

    public WeatherData findByID(UUID id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            WeatherData weatherData = session.createQuery("FROM WeatherData WHERE weather_data_id=:id", WeatherData.class).
                    setParameter("id", id).
                    uniqueResultOptional().orElse(null);
            transaction.commit();
            return weatherData;

        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
            return null;
        }
    }


    private WeatherData getWeatherComepleteData(String cityName, LocalDate localDate, int option) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            WeatherData weatherData = GetWeatherDataBasedOnQuery(cityName, localDate, session, option);
            transaction.commit();

            return weatherData;

        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }

        return null;
    }


    private WeatherData GetWeatherDataBasedOnQuery(String cityName, LocalDate localDate, Session session, int option) {
        List<WeatherData> weatherData = new ArrayList<>();
        switch (option) {
            case 1 -> weatherData = session.createQuery("FROM WeatherData WHERE city_name=:cityName", WeatherData.class)
                    .setParameter("cityName", cityName)
                    .getResultList();
            case 2 -> weatherData = session.createQuery("FROM WeatherData WHERE date=:localDate AND city_name=:cityName", WeatherData.class)
                    .setParameter("cityName", cityName)
                    .setParameter("localDate", localDate)
                    .getResultList();

        }
        if (weatherData.size() > 0) {
            return weatherData.get(weatherData.size() - 1);
        } else return null;
    }

    private void deleteWeatherDataBasedOnQuery(String cityName, LocalDate localDate, int option) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            switch (option) {
                case 1 -> session.createSQLQuery("DELETE FROM weather_data WHERE city_name=:city")
                        .setParameter("city", cityName)
                        .executeUpdate();
                case 2 -> session.createSQLQuery("DELETE FROM weather_data WHERE city_name=:city AND date=:localDate")
                        .setParameter("city", cityName)
                        .setParameter("localDate", localDate)
                        .executeUpdate();
            }
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }
    }
}
