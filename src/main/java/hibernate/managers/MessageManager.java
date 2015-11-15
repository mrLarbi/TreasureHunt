package hibernate.managers;

import hibernate.models.entities.Message;
import hibernate.models.entities.User;
import hibernate.utility.HibernateUtility;
import hibernate.utility.SqlDateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by mohameddd on 10/18/15.
 */
public class MessageManager {

    private SessionFactory sessionFactory;
    private Session session;

    public MessageManager() {
        sessionFactory = HibernateUtility.createSessionFactory(Message.class);
    }

    public static Message createMessage(User sender, User receiver, String content) {
        Message message = new Message(sender, receiver);
        message.setContent(content);
        message.setSentdate(SqlDateUtility.now());
        return message;
    }

    public Integer addMessage(User sender, User receiver, String content) {
        return addMessage(createMessage(sender, receiver, content));
    }

    public Integer addMessage(Message message) {
        session = sessionFactory.openSession();
        Transaction tx = null;
        Integer messageId = null;

        try {
            tx = session.beginTransaction();
            messageId = (Integer) session.save(message);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return messageId;

    }

}
