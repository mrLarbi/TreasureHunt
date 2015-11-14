package hibernate.managers;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
	private static SessionFactory sessionFactory;

	static {
		sessionFactory = HibernateUtility.createSessionFactory();
	}

	private Session session;

	/* Method to CREATE a user in the database */
	public Integer addUser(String username, String password, String email) {
		return addUser(createUser(username, password, email));
	}

	public static User createUser(String username, String password, String email) {
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

	public boolean updateUser (User user ) {
		session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			session.close();
			return false;
		}

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

	public static void main(String[] args) {

		UserManager um = new UserManager();

		um.addUser("Charles", "Charles1234", "charles@gmail.com");
		um.addUser("Juan", "Juan1234", "juan@gmail.com");
		um.addUser("Mohamed", "Mohamed1234", "mohamed@gmail.com");

		User mohamed = um.findUserByUsername("Mohamed");
		User charles = um.findUserByUsername("Juan");
		User juan = um.findUserByUsername("Charles");

		System.out.print(mohamed.toString());
		System.out.print(charles.toString());
		System.out.print(juan.toString());

		um.addFriendshipBtn(mohamed, charles);
		um.addFriendshipBtn(mohamed, juan);
		um.addFriendshipBtn(charles, juan);

		MessageManager mM = new MessageManager();
		mM.addMessage(juan, mohamed, "Dummy message");
		mM.addMessage(charles, mohamed, "Dummy message 2");

	}

	// TODO need refactoring
	public User findUserByUsername(String username) {
		session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("FROM User U WHERE U.username = :username");
			query.setParameter("username", username);

			List<User> user = query.list();

			session.close();

			if (user.isEmpty()) {
				return null;
			}

			return user.get(0);
		} catch (HibernateException e) {
			session.close();
			return null;
		}
	}

	// TODO need refactoring with method up-top
	public User findUserByEmail(String email) {
		session = sessionFactory.openSession();
		
		try {
			Query query = session.createQuery("FROM User U WHERE U.email = :email");
			query.setParameter("email", email);

			List<User> user = query.list();

			session.close();
			if (user.isEmpty()) {
				return null;
			}

			return user.get(0);
		} catch (HibernateException e) {
			session.close();
			return null;
		}
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


			session.close();
			if (user.isEmpty()) {
				return null;
			}

			return user.get(0);
		} catch (HibernateException e) {
			session.close();
			return null;
		}
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

	public boolean checkCoordinate(User hunter, Hunt currentHunt, String latitude, String longitude, boolean value) {
		return HuntingManager.checkCoordinate(hunter, currentHunt,latitude,longitude,value);
	}

	public User find(Integer userId) {
		User user = null;
		session = sessionFactory.openSession();

		try {
			user = (User)session.get(User.class,userId);
		} catch (HibernateException e) {
		} finally {
			session.close();
		}

		return user;
	}

	public List<User> searchUserByUsernamae(String phrase) {
		Session session = sessionFactory.openSession();
		List<User> results = Collections.emptyList();
		try {
			Query query = session.createQuery("FROM User U WHERE " +
					"U.username LIKE %:phrase%"
					+ " OR U.name LIKE %:phrase%");

			query.setParameter("phrase",phrase);

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

