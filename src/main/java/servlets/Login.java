package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hibernate.managers.ManageUsers;

public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	ManageUsers manager;
	
	public Login() {
		super();
		manager = new ManageUsers();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(HttpServletResponse.SC_FORBIDDEN);

		String username = req.getParameter("username");
		String password = req.getParameter("password");
	
		//TODO validation
		if (manager.isValidLogin(username, password)) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/welcome.jsp").forward(req, resp);
			resp.sendRedirect("login");
		} else {
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
