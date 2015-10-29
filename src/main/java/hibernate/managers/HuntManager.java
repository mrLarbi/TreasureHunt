package hibernate.managers;

import hibernate.models.entities.Coordinate;
import hibernate.models.entities.Hunt;
import hibernate.models.entities.User;
import hibernate.utility.HibernateUtility;
import hibernate.utility.SqlDateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mohameddd on 10/18/15.
 */
public class HuntManager {

    private SessionFactory sessionFactory;
    private Session session;

    public HuntManager() {

        sessionFactory = HibernateUtility.createSessionFactory(Hunt.class);
    }

    public Hunt createHunt(String name, User creator) {
        Hunt hunt = new Hunt();
        hunt.setCreator(creator);
        hunt.setCreatedAt(SqlDateUtility.now());
        hunt.setName(name);
        return hunt;
    }

    public Integer addHunt(Hunt hunt) {
        session = sessionFactory.openSession();
        Transaction tx = null;
        Integer huntID = null;

        try {
            tx = session.beginTransaction();
            huntID = (Integer) session.save(hunt);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return huntID;
    }

    public void addCoordinateToHunt(Hunt hunt, Coordinate coordinate) {
        session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            hunt.addCoordinate(coordinate);
            session.update(hunt);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public List<Hunt> latestHunts() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("FROM Hunt ").list();

          } catch (HibernateException e) {
              } finally {
            session.close();
        }
        return null;
    }
}
