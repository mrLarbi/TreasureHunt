package servlets;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hibernate.managers.UserManager;


public class NameAvailable extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserManager manager = new UserManager();
		String requestedName = request.getParameter("username");
		Writer writer = response.getWriter();
		response.setContentType("application/json");

		if  (manager.findUserByUsername(requestedName) == null ) {
			writer.write(new Boolean(true).toString());
		} else {
			writer.write(new Boolean(false).toString());
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
