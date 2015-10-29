package hibernate.managers;

import hibernate.models.entities.Coordinate;
import hibernate.utility.HibernateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by mohameddd on 10/18/15.
 */
public class CoordinateManager {
    private SessionFactory sessionFactory;
    private Session session;

    public CoordinateManager() {
        sessionFactory = HibernateUtility.createSessionFactory(Coordinate.class);
    }

    public Coordinate createCoordinate(String name, String latitude, String longitude, String image) {
        Coordinate coordinate = new Coordinate(name, latitude,longitude,image);
        return coordinate;
    }

    public Integer addCoordinate(Coordinate coordinate) {
        // TODO
        session = sessionFactory.openSession();
        Transaction tx = null;
        Integer coordID = null;

        try {
            tx = session.beginTransaction();
            coordID = (Integer) session.save(coordinate);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return coordID;
    }

}
