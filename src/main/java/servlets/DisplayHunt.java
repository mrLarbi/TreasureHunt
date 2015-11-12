package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.SessionHandler;
import hibernate.managers.HuntManager;
import hibernate.managers.UserManager;
import hibernate.models.entities.Hunt;

/**
 * Created by Machi on 30/10/2015.
 */
public class DisplayHunt extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String huntID = request.getParameter("id");

        Hunt hunt = null;
        HuntManager huntManager = new HuntManager();
        
        if (huntID != null && !huntID.isEmpty()) {
            hunt = huntManager.find(Integer.parseInt(huntID));
        }
    	
        if(hunt == null)
        	hunt = huntManager.createHunt("None", new UserManager().createUser("404", "", ""));
        
        request.setAttribute("hunt", hunt);
        request.setAttribute("logged", new Boolean(SessionHandler.checkSessionLogged(request)));
    	this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/hunt.jsp").forward(request, response);
    	
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }
}
