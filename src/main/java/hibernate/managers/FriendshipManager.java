package hibernate.managers;

import hibernate.models.entities.Friend;
import hibernate.models.entities.User;
import hibernate.utility.HibernateUtility;
import hibernate.utility.SqlDateUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;

/**
 * Created by Machi on 24/10/2015.
 */
// TODO
public class FriendshipManager {

    public static Friend createFriendshipBTn(User agent, User follower) {
        Friend frienship = new Friend(agent,follower);
        frienship.setCreated(SqlDateUtility.now());
        return frienship;
    }

    public static void removeFriendFrom(User agent, User followerToRemove) {
       agent.getMyFollowers().remove(followerToRemove);
    }
}
