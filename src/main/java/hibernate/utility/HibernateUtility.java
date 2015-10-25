package hibernate.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by Machi on 24/10/2015.
 */
public class HibernateUtility {
    public static SessionFactory createSessionFactory(Class clazz) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(clazz);
        configuration.configure();

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
                .buildServiceRegistry();

        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }

}
