package hibernate.managers;

import hibernate.models.entities.Message;
import hibernate.models.entities.User;
import hibernate.utility.HibernateUtility;
import hibernate.utility.SqlDateUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;

/**
 * Created by mohameddd on 10/18/15.
 */
public class MessageManager {

    public static Message createMessage(User sender, User receiver, String content) {
        Message message = new Message(sender, receiver);
        message.setContent(content);
        message.setSentdate(SqlDateUtility.now());
        return message;
    }

}
