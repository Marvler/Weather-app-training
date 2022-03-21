package dao;

import connection.HibernateUtil;
import model.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.List;

public class LocationDAO {

    private final Logger logger = LogManager.getLogger(LocationDAO.class);


    public void save(Location location) {

        Transaction transaction = null;
        Location foundLocation = findByCity(location.getCity());
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();


            if (foundLocation != null) {
                logger.info("City already in DB");
            } else {
                session.save(location);
            }

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }
    }

    public Location findByCity(String city) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Location location = session.createQuery("FROM Location WHERE city=:city", Location.class).
                    setParameter("city", city).
                    uniqueResultOptional().orElse(null);
            transaction.commit();

            return location;

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();

                logger.error(e.getMessage(), e);
            }
            return null;
        }
    }

    public void delete(Location location) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.delete(location);

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }
    }

    public void deleteByCity(String city) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Location location = session.createQuery("FROM Location WHERE city=:city", Location.class).
                    setParameter("city", city).
                    getSingleResult();
            session.delete(location);
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();

                logger.error(e.getMessage(), e);
            }
        }
    }

    public void update(Location location) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(location);

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }
    }

    public void updateByCity(String city) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Location location = session.createQuery("FROM Location WHERE city=:city", Location.class).
                    setParameter("city", city).
                    getSingleResult();
            session.update(location);
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();

                logger.error(e.getMessage(), e);
            }
        }
    }

    public List<Location> findAllLocations() {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            List<Location> locations = session.createQuery("SELECT n FROM Location AS n", Location.class)
                    .getResultList();

            transaction.commit();

            return locations;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }

        return Collections.emptyList();
    }


}
