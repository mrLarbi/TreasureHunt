package hibernate.managers;

import hibernate.models.entities.Hunt;
import hibernate.models.entities.Hunter;
import hibernate.models.entities.User;
import hibernate.utility.HibernateUtility;
import org.hibernate.*;

import java.util.List;

/**
 * Created by Machi on 24/10/2015.
 */

// TODO
public class HuntingManager {

    private static SessionFactory sessionFactory = HibernateUtility.createSessionFactory(Hunter.class);
    private static Session session;

    public HuntingManager() {

    }

    public static Hunter createHunting (User hunter, Hunt hunt ) {
        Hunter hunting = new Hunter(hunter,hunt);
        hunting.setFinished(false); // Z
        return hunting;
    }

    public static void finishHunting(User hunter, Hunt hunt) {
        // TODO
        session = sessionFactory.openSession();

        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Hunter H WHERE H.hunting.hunter = :hunter_id " +
                    "AND H.hunting.hunt = :hunt_id");

            query.setParameter("hunter_id", hunter.getId());
            query.setParameter("hunt_id", hunt.getId());

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
