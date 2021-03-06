package backend;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hibernate.managers.UserManager;
import hibernate.models.entities.User;

public class SessionHandler {

	public static void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Cookie cookie = getTokenCookie(request);
		if (cookie != null) {
			cookie.setMaxAge(0);
			cookie.setValue(null);
			response.addCookie(cookie);
		}
		SessionHandler.setUser(request,null);
		response.sendRedirect("home");
	}

	public static boolean signUp(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserManager manager = new UserManager();

		User user = createUserFromSignUpRequest(request, manager);

		if (user == null) {
			// Login errors
			response.sendRedirect("home");
			return false;
		}

		addUserToSession(request, user);

		addRememberCookie(request, response,user);
		
		Integer id = manager.addUser(user);

		if (id != null)  {
			// successfully added.
			response.sendRedirect("user/profile");
			return true;
		} else {
			return false;
		}
	}

	public static void addRememberCookie(HttpServletRequest request, HttpServletResponse response, User user) {
		Cookie cookie = getTokenCookie(request);
		if (cookie == null) {
			String toRemember = request.getParameter("username") + request.getParameter("password") + new Date().toString();

			user.setRemember(toRemember);
			cookie = new Cookie("token", user.getRemember());
			response.addCookie(cookie);
		} else {
			cookie.setValue(user.getRemember());
		}

		//cookie.setSecure(true); // send the cookie using a secure protocol
		cookie.setMaxAge(7 * 60 * 60 * 24); // 7 days remember me cookie
	}

	public static void logMe(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		UserManager manager = new UserManager();
		User user = getUserFromRememberCookie(request, manager);

		if (user == null) {
			String login = request.getParameter("login");
			String password = request.getParameter("password");

			if ((Validator.isValidMail(login) || Validator.isValidUsername(login))
					&& Validator.isValidPassword(password)) {
				user = manager.find(login, password);
			}
		}

		if (user != null) {
			addUserToSession(request, user);
			updateRemember(request,user,manager);

			addRememberCookie(request,response,user);

			response.sendRedirect("user/profile");
		// session.
			// response.sendRedirect("login"); // Go to start page.
		} else {
			// Set error msg for ${error}
			request.setAttribute("error", "Unknown login, try again");

			// Go back to login page.
			response.sendRedirect("home");
		}
	}

	public static void addUserToSession (HttpServletRequest request, User user) {
		request.getSession().setAttribute("user", user);
	}
	public static User getUserFromRememberCookie(HttpServletRequest request, UserManager manager) {
		Cookie cookie = getTokenCookie(request);

		if (cookie != null) {
			return manager.findByRemember(cookie.getValue());
		}

		return null;
	}

	public static Cookie getTokenCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();

		if (cookies == null ) {
			return null;
		}

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("token")) {
				return cookie;
			}
		}
		return null;
	}

	public static void updateRemember(HttpServletRequest request, User user, UserManager manager) {
		String login = request.getParameter("email");
		String password = request.getParameter("password");
		String remember = login + password + new Date();
		manager.updateRememberMe(user,remember);
	}

	public static User createUserFromSignUpRequest(HttpServletRequest request, UserManager manager) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String pConfirmation = request.getParameter("confirmation");
		String email = request.getParameter("email");
		String name = request.getParameter("realname");
		String gender = request.getParameter("gender");
		String zip = request.getParameter("zcode");
		String phone = request.getParameter("phone");

		if (!password.equals(pConfirmation) && !Validator.isValidPassword(password) &&
				!Validator.isValidMail(email) && !Validator.isValidUsername(username)) {
			return null;
		}

		User newUser = manager.createUser(username, password, email);

		if (gender != null && !gender.isEmpty()) {
			newUser.setGender(gender.charAt(0));
		}

		if (Validator.isValidPhoneNumber(phone)) {
			newUser.setPhone(phone);
		}

		if (Validator.isValidPostalCode(zip)) {
			newUser.setPostalcode(Integer.parseInt(zip));
		}

		newUser.setName(name);

		return newUser;
	}

	public static boolean isSignedIn(HttpServletRequest request) {
		return new Boolean(SessionHandler.getUser(request) != null);
	}

	public static User getUser(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			return getUserFromRememberCookie(request, new UserManager());
		}
		return user;
	}

	public static void setUser(HttpServletRequest request, User user) {
		request.getSession().setAttribute("user",user);
	}
}
