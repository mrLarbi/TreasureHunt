package hibernate.managers;

import hibernate.models.entities.Coordinate;
import hibernate.models.entities.Hunt;
import hibernate.models.entities.User;
import hibernate.utility.HibernateUtility;
import hibernate.utility.SqlDateUtility;
import org.hibernate.*;

import java.util.*;

/**
 * Created by mohameddd on 10/18/15.
 */
public class HuntManager {

    private static SessionFactory sessionFactory;
    static {
        sessionFactory = HibernateUtility.createSessionFactory();
    }

    private Session session;


    public static Hunt createHunt(String name, User creator) {
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
        return Collections.emptyList();
    }

    public Hunt find(Integer huntId) {
        session = sessionFactory.openSession();
        Hunt hunt = null;
        try {
            hunt = (Hunt) session.get(Hunt.class, huntId);
        } catch (HibernateException e) {

        } finally {
            session.close();
            return hunt;
        }
    }

    public void addCoordinatesToHunt(Hunt hunt, ArrayList<Coordinate> coordinates) {
        for(Coordinate coord: coordinates) {
            addCoordinateToHunt(hunt,coord);
        }
    }

    public List<Hunt> getLastFiveHunts() {
        List<Hunt> hunts = latestHunts();
        int length = hunts.size();
        if (length < 6 ) {
            Collections.reverse(hunts);
            return hunts;
        }

        List<Hunt> desiredFive = new ArrayList<>();

        for(int i = 0; i < 5 ; i++) {
            desiredFive.add(hunts.get(length-1-i));
        }

        return desiredFive;
    }

    public Coordinate getCoordnate(Hunt hunt, String lat, String lng) {
        Set<Coordinate> coordinates = hunt.getCoordinates();
        for (Coordinate coordinate:coordinates) {
            if (coordinate.getLatitude().equalsIgnoreCase(lat) && coordinate.getLongitude().equalsIgnoreCase(lng)) {
                return coordinate;
            }
        }
        return null;
    }


    public List<Hunt> searchHuntByName(String name) {
        Session session = sessionFactory.openSession();
        List<Hunt> results = Collections.emptyList();
        try {
            Query query = session.createQuery("FROM Hunt H WHERE H.name LIKE %:phrase%");
            query.setParameter("phrase",name);

            results = query.list();
            session.close();
            return results;
        } catch (HibernateException e) {
        } finally {
            session.close();
        }
        return results;
    }
}
