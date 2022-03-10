package dao;

import connection.HibernateUtil;
import model.Location;
import model.WeatherData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class WeatherDAO {

    private static final Logger logger = LogManager.getLogger(WeatherDAO.class);

    public static void save(WeatherData weatherData){

        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.save(weatherData);

            transaction.commit();
        } catch (HibernateException e){
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }
    }
}
