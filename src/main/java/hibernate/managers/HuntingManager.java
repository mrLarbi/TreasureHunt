package hibernate.managers;

import hibernate.models.entities.Coordinate;
import hibernate.models.entities.Hunt;
import hibernate.models.entities.Hunter;
import hibernate.models.entities.User;
import hibernate.utility.HibernateUtility;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Machi on 24/10/2015.
 */

// TODO
public class HuntingManager {

    private static SessionFactory sessionFactory;

    static {
        sessionFactory = HibernateUtility.createSessionFactory();
    }

    private static Session session;

    public static Hunter newHunterInstance(User hunter, Hunt hunt,Coordinate coord) {
        Hunter hunting = new Hunter(hunter,hunt);
        hunting.setFinished(false); // Z
        hunting.setHuntedPoint(coord);
        return hunting;
    }

    public static Set<Hunter> createHunting (User hunter, Hunt hunt) {
          Set<Coordinate> coordinates = hunt.getCoordinates();
          Set<Hunter> created = new HashSet<>();

          for(Coordinate coord: coordinates) {
              created.add(newHunterInstance(hunter,hunt,coord));
          }

            return created;
    }

    public static boolean checkCoordinate(User hunter, Hunt hunt, String latitude, String longitude, boolean value) {
        // TODO
        session = sessionFactory.openSession();

        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Hunter H WHERE H.hunting.hunter.id = :hunter_id " +
                    "AND H.hunting.hunt.id = :hunt_id" + " AND H.hunting.huntedPoint.latitude = :lat" +
                    " AND H.hunting.huntedPoint.longitude = :lng");

            query.setParameter("hunter_id", hunter.getId());
            query.setParameter("hunt_id", hunt.getId());
            query.setParameter("lat", latitude);
            query.setParameter("lng",longitude);


            query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

            List<Hunter> huntings = query.list();

            if (huntings.isEmpty()) {
                return true;
            }
            Hunter hunting = huntings.get(0);

            hunting.setFinished(value);
            session.update(hunting);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public static Hunter getHunter(User hunter, Hunt hunt) {
        session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("FROM Hunter H WHERE H.hunting.hunter.id = :hunter_id " +
                    "AND H.hunting.hunt.id = :hunt_id");

            query.setParameter("hunter_id", hunter.getId());
            query.setParameter("hunt_id", hunt.getId());


            query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

            List<Hunter> huntings = query.list();

            if (huntings.isEmpty()) {
                return null;
            }
            Hunter hunting = huntings.get(0);
            return hunting;
        } catch (HibernateException e) {
           return null;
        } finally {
            session.close();
        }

    }
}
