package dao;

import connection.HibernateUtil;
import model.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LocationDAO {

    private static final Logger logger = LogManager.getLogger(LocationDAO.class);

    public static void save(Location location){

        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.save(location);

            transaction.commit();
        } catch (HibernateException e){
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }
    }
}
