package hibernate.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by Machi on 24/10/2015.
 */
public class HibernateUtility {
    private  static SessionFactory sessionFactory = null;

    private static void newFactory  () {
        Configuration configuration = new Configuration();
        configuration.configure();

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
                .buildServiceRegistry();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

    }

    public static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            newFactory();
        }
        return sessionFactory;
    }
}
