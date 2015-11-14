package servlets;

import backend.PageHandler;
import backend.SessionHandler;
import hibernate.managers.HuntManager;

import java.io.IOException;

import javax.servlet.ServletException;
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
		SessionHandler.isSignedIn(request);
		PageHandler.setTitleAndHeader(request, "Welcome to your Treasure Hunt!");
		request.setAttribute("hunts",new HuntManager().getLastFiveHunts());
		request.setAttribute("signed",SessionHandler.isSignedIn(request));
		request.setAttribute("title","Treasure hunt");
		this.getServletContext().getRequestDispatcher( "/WEB-INF/JSP/welcome.jsp" ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
