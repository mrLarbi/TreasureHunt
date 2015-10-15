package servlets;

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
		checkSessionLogged(request);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/JSP/welcome.jsp" ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkSessionLogged(request);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/JSP/welcome.jsp" ).forward( request, response );
	}
	
	private void checkSessionLogged(HttpServletRequest request)
	{
		if(request.getSession().getAttribute("logged") == null)																																
		{
			request.getSession().setAttribute("logged", false);
		}
	}

}
