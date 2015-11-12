package servlets;

import backend.SessionHandler;
import hibernate.managers.CoordinateManager;
import hibernate.managers.HuntManager;
import hibernate.managers.HuntingManager;
import hibernate.managers.UserManager;
import hibernate.models.entities.Coordinate;
import hibernate.models.entities.Hunt;
import hibernate.models.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Machi on 30/10/2015.
 */
public class CheckPoint extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = SessionHandler.getUser(request);
        String huntDetails = request.getParameter("hunt");
        String jsonCoord = request.getParameter("coord");

        // Parse coord to Coordinate

        CoordinateManager cManager = new CoordinateManager();
        Coordinate coord = cManager.findCoord("", "", "");


        HuntManager htManager = new HuntManager();
        Hunt hunt = htManager.find(Integer.parseInt(huntDetails));

        UserManager uManager = new UserManager();
        uManager.checkCoordinate(currentUser,hunt,coord);

        // TODO page redirection
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
