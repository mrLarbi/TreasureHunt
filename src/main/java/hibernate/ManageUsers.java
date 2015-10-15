package hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.security.crypto.bcrypt.BCrypt;

import hibernate.objects.User;

public class ManageUsers {
	private SessionFactory sessionFactory;
	private Session session;

	public ManageUsers() {
		sessionFactory = createSessionFactory();
	}

	public static SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(User.class);
		configuration.configure();

		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();

		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		return sessionFactory;
	}

	/* Method to CREATE a user in the database */
	public Integer addUser(String username, String password, String email) {
		return addUser(createUser(username, password, email));
	}

	public User createUser(String username, String password, String email) {
		password = BCrypt.hashpw(password, BCrypt.gensalt(12));
		User user = new User(username, password, email);
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

	public static void main(String[] args) {

		ManageUsers MU = new ManageUsers();

		/* Add a few users in database */
		int uID1 = MU.addUser("calderino", "pwd001", "juan@gmail.com");
		int uID2 = MU.addUser("xxbluraydiscxx", "pwd002", "mohamed@gmail.com");
		int uID3 = MU.addUser("kilimanjaro", "pwd003", "charles@gmail.com");
		int uID4 = MU.addUser("test", "test", "test@gmail.com");

		/* List down all user */
		MU.listUsers();

		/* Update user */
		MU.updateUserAvatar(uID2, "momo.jpg");

		/* Delete an user */
		MU.deleteUser(uID4);

		/* List down new list of users */
		MU.listUsers();
	}

	// TODO need refactoring
	public User findUserByUsername(String username) {
		session = sessionFactory.openSession();
		Transaction transaction = null;

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
		Transaction transaction = session.getTransaction();

		try {
			Query query = session.createQuery("FROM User U WHERE U.email = :email");
			query.setParameter("email", email);

			List<User> user = query.list();

			if (user.isEmpty()) {
				return null;
			}

			return user.get(0);
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}

	public boolean isValidLogin(String username, String password) {
		User user = findUserByUsername(username);

		if (user == null) {
			user = findUserByEmail(username);
		}

		if (user != null && BCrypt.checkpw(password, user.getPassword())) {
			return true;
		}

		return false;
	}
}
