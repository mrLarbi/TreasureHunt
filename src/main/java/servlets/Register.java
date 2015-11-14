package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.SessionHandler;

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO get all sent fields, create a User object and persists it.
		request.setAttribute("title", "Register");
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 if(SessionHandler.signUp(request,response)) {
			 // TODO stuffs here
		 } else {
			 // TODO stuffs here
		 }

	}

}
