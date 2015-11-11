package servlets;

import backend.SessionHandler;
import hibernate.managers.HuntManager;
import hibernate.models.entities.Coordinate;
import hibernate.models.entities.Hunt;
import hibernate.models.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Machi on 30/10/2015.
 */
public class CreateHunt extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/createhunt.jsp").forward(request, response);
    	
    	User currentUser = SessionHandler.getUser(request);

        String name = request.getParameter("name");

        HuntManager manager = new HuntManager();
        Hunt  hunt = manager.createHunt(name,currentUser);

        manager.addCoordinatesToHunt(hunt,getCoordsFromRequest(request));
        manager.addHunt(hunt);
    }


    private ArrayList<Coordinate> getCoordsFromRequest(HttpServletRequest request) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();

        String jsonCoords = request.getParameter("coords");

        //TODO parse json data and results in coordinates

        return coordinates;
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
