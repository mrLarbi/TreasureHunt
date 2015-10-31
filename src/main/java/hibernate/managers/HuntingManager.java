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

    private static SessionFactory sessionFactory = HibernateUtility.createSessionFactory(Hunter.class);
    private static Session session;

    public HuntingManager() {

    }

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

    public static void checkCoordinate(User hunter, Hunt hunt, Coordinate coordinate) {
        // TODO
        session = sessionFactory.openSession();

        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Hunter H WHERE H.hunting.hunter.id = :hunter_id " +
                    "AND H.hunting.hunt.id = :hunt_id" + " AND H.hunting.huntedPoint.id = :coord_id");

           query.setParameter("hunter_id", hunter.getId());
            query.setParameter("hunt_id", hunt.getId());
            query.setParameter("coord_id", coordinate.getId());


            query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

            List<Hunter> huntings = query.list();

            if (huntings.isEmpty()) {
                return;
            }
            Hunter hunting = huntings.get(0);

            hunting.setFinished(true);
            session.update(hunting);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
}