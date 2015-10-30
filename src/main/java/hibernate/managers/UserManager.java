package hibernate.managers;

import java.util.Iterator;
import java.util.List;

import hibernate.models.entities.Coordinate;
import hibernate.models.entities.Hunt;
import hibernate.utility.HibernateUtility;
import hibernate.utility.SqlDateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.security.crypto.bcrypt.BCrypt;

import hibernate.models.entities.User;

public class UserManager {
	private SessionFactory sessionFactory;
	private Session session;

	public UserManager() {
		sessionFactory = HibernateUtility.createSessionFactory(User.class);
	}

	/* Method to CREATE a user in the database */
	public Integer addUser(String username, String password, String email) {
		return addUser(createUser(username, password, email));
	}

	public User createUser(String username, String password, String email) {
		password = BCrypt.hashpw(password, BCrypt.gensalt(12));
		User user = new User(username, password, email);
		user.setCreated(SqlDateUtility.now());

		return user;
	}

	public Integer addUser(User user) {
		session = sessionFactory.openSession();
		Transaction tx = null;
		Integer userID = null;

		try {
			tx = session.beginTransaction();
			userID = (Integer) session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return userID;

	}

	/* Method to DELETE a user from the records */
	public void deleteUser(Integer userID) {
		session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			User user = (User) session.get(User.class, userID);
			session.delete(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to READ all the users */
	public void listUsers() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<User> users = session.createQuery("FROM User").list();
			for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {

				User user = (User) iterator.next();
				System.out.print(user.getUsername() + "\t" + user.getEmail() + "\t" + user.getAvatar() + "\t"
						+ user.getName() + "\t" + user.getGender() + "\t" + user.getPostalcode() + "\t"
						+ user.getPhone() + "\n");
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to UPDATE password for an user */
	public void updateUserPassword(Integer userID, String newPassword) {
		session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			User user = (User) session.get(User.class, userID);
			user.setPassword(newPassword);
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to UPDATE name for an user */
	public void updateUserName(Integer userID, String newName) {
		session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			User user = (User) session.get(User.class, userID);
			user.setName(newName);
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to UPDATE avatar for an user */
	public void updateUserAvatar(Integer userID, String newAvatar) {
		session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			User user = (User) session.get(User.class, userID);
			user.setAvatar(newAvatar);
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to UPDATE gender for an user */
	public void updateUserGender(Integer userID, char newGender) {
		session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			User user = (User) session.get(User.class, userID);
			user.setGender(newGender);
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to UPDATE postal code for an user */
	public void updateUserPostalCode(Integer userID, int newPostalCode) {
		session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			User user = (User) session.get(User.class, userID);
			user.setPostalcode(newPostalCode);
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to UPDATE phone for an user */
	public void updateUserPhone(Integer userID, String newPhone) {
		session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			User user = (User) session.get(User.class, userID);
			user.setPhone(newPhone);
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void addMessage(Integer userID, Integer friendID) {

	}

	public void removeMessage(Integer userID, Integer friendID) {

	}

	public static void main(String[] args) {

		UserManager MU = new UserManager();

		User user = MU.findUserByEmail("charles@gmail.com");

		HuntManager HM = new HuntManager();
		List<Hunt> hunts = HM.latestHunts();
		Hunt hunt = hunts.get(1);

		Coordinate coordinate = new Coordinate();
		coordinate.setLatitude("67.9");
		coordinate.setLongitude("34.09");
		coordinate.setName("Test");

		CoordinateManager CM = new CoordinateManager();

		CM.addCoordinate(coordinate);


		hunt.addCoordinate(coordinate);

		MU.startHunting(user,hunt);

		MU.checkCoordinate(user,hunt, coordinate);

	}

	// TODO need refactoring
	public User findUserByUsername(String username) {
		session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("FROM User U WHERE U.username = :username");
			query.setParameter("username", username);

			List<User> user = query.list();

			if (user.isEmpty()) {
				return null;
			}

			return user.get(0);
		} catch (HibernateException e) {
		} finally {
			session.close();
		}
		return null;
	}

	// TODO need refactoring with method up-top
	public User findUserByEmail(String email) {
		session = sessionFactory.openSession();
		
		try {
			Query query = session.createQuery("FROM User U WHERE U.email = :email");
			query.setParameter("email", email);

			List<User> user = query.list();

			if (user.isEmpty()) {
				return null;
			}

			return user.get(0);
		} catch (HibernateException e) {
		} finally {
			session.close();
		}

		return null;
	}

	public boolean isValidLogin(String username, String password) {
		return find(username, password) != null;
	}

	public User findByRemember(String remember) {
		session = sessionFactory.openSession();
		
		try {
			Query query = session.createQuery("FROM User U WHERE U.remember = :remember");
			query.setParameter("remember", remember);

			List<User> user = query.list();

			if (user.isEmpty()) {
				return null;
			}

			return user.get(0);
		} catch (HibernateException e) {
		} finally {
			session.close();
		}

		return null;
	}

	public User find(String username, String password) {
		User user = findUserByUsername(username);

		if (user == null) {
			user = findUserByEmail(username);
		}

		if (user != null && BCrypt.checkpw(password, user.getPassword())) {
			return user;
		}

		return null;
 
	}

	public void addFriendshipBtn(User agent, User follower) {
		session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			agent.getMyFollowers().add(FriendshipManager.createFriendshipBTn(agent,follower));
			session.update(agent);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void updateRememberMe(User user, String remember) {
		session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			user.setRemember(remember);
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}


	public void sendMessageTo(User sender, User receiver, String content) {
		session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			sender.getSentMessages().add(MessageManager.createMessage(sender,receiver,content));
			session.update(sender);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void startHunting(User hunter, Hunt startedHunt) {
		// TODO
		session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			hunter.getCurrenthunts().addAll(HuntingManager.createHunting(hunter, startedHunt));
			session.update(hunter);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void checkCoordinate(User hunter, Hunt currentHunt, Coordinate coordinate) {
		HuntingManager.checkCoordinate(hunter, currentHunt, coordinate);
	}
}
