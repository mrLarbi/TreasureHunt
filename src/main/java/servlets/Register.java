package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hibernate.ManageUsers;
import hibernate.objects.User;

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManageUsers manager;

	public Register() {
		super();
		// TODO Auto-generated constructor stub
		manager = new ManageUsers();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO get all sent fields, create a User object and persists it.
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String pConfirmation = request.getParameter("confirm");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String zip = request.getParameter("zip");
		String phone = request.getParameter("phone");

		if (!password.equals(pConfirmation)) {
			writer.write("REGISTRATION FAILURE");
			System.out.println(password);
			System.out.println(pConfirmation);
			return;
		}

		User newUser = manager.createUser(username, password, email);

		if (gender != null) {
			newUser.setGender(gender.charAt(0));
		}

		newUser.setName(name);
		newUser.setPhone(phone);

		if (zip != null & !zip.isEmpty()) {
			newUser.setPostalcode(Integer.parseInt(zip));
		}

		manager.addUser(newUser);

		writer.write("REGISTRATION SUCCESS");

	}

}
