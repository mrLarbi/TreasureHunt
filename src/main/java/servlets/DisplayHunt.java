package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.SessionHandler;
import hibernate.managers.CoordinateManager;
import hibernate.managers.HuntManager;
import hibernate.managers.UserManager;
import hibernate.models.entities.Coordinate;
import hibernate.models.entities.Hunt;
import hibernate.models.entities.User;
import org.json.JSONObject;

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
        request.setAttribute("logged", SessionHandler.isSignedIn(request));
    	this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/hunt.jsp").forward(request, response);
    	
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("appliaction/json");
        writer.write(new Boolean(checkPoint(request,response)).toString());
    }

    private JSONObject parseRequestParams(HttpServletRequest request) {
        return new JSONObject(request.getParameter("param"));
    }

    private boolean checkPoint(HttpServletRequest request, HttpServletResponse response) {
        JSONObject params = parseRequestParams(request);
        String lat = params.getString("lat");
        String lng = params.getString("lng");

        Integer huntId = Integer.parseInt(params.getString("huntname"));
        Hunt hunt= new HuntManager().find(huntId);
        boolean value = Boolean.parseBoolean(params.getString("value"));

        if  (hunt == null) {
            return false;
        } else {
             User currentUser = SessionHandler.getUser(request);
            if (currentUser == null) {
                return false;
            } else {
                return new UserManager().checkCoordinate(currentUser,hunt,lat,lng,value);
            }
        }
    }
}
