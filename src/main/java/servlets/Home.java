package servlets;

import backend.PageHandler;
import backend.SessionHandler;
import hibernate.managers.HuntManager;
import hibernate.models.entities.Hunt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomePage
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Home() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionHandler.checkSessionLogged(request);
		PageHandler.setTitleAndHeader(request, "Welcome to your Treasure Hunt!");
		request.getSession().setAttribute("hunts",new HuntManager().getLastFiveHunts());
		this.getServletContext().getRequestDispatcher( "/WEB-INF/JSP/welcome.jsp" ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
