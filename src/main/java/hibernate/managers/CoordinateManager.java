package hibernate.managers;

import hibernate.models.entities.Coordinate;
import hibernate.models.entities.Hunter;
import hibernate.utility.HibernateUtility;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

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

    public Coordinate findCoord(String name, String latitude, String longitude) {

        session = sessionFactory.openSession();

        List<Coordinate> coordinates = new ArrayList<>();

        try {
            Query query = session.createQuery("FROM Coordinate Coord WHERE Coord.name = :name " +
                    "AND Coord.latitude = :latitude" + " AND Coord.longitude = :longitude");

            query.setParameter("name", name);
            query.setParameter("latitude", longitude);
            query.setParameter("longitude", longitude);

            query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            coordinates = query.list();

        } catch (HibernateException e) {
        } finally {
            session.close();
        }

        if (coordinates.isEmpty()) {
            return null;
        } else {
            return coordinates.get(0);
        }
    }

}
