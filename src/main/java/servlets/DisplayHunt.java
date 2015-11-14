package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.SessionHandler;
import backend.Validator;
import hibernate.managers.CoordinateManager;
import hibernate.managers.HuntManager;
import hibernate.managers.HuntingManager;
import hibernate.managers.UserManager;
import hibernate.models.entities.Coordinate;
import hibernate.models.entities.Hunt;
import hibernate.models.entities.Hunter;
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

        if (Validator.isNumberFormat(huntID)) {
            hunt = huntManager.find(Integer.parseInt(huntID));
        }

        if(hunt == null) {
        	hunt = huntManager.createHunt("None", new UserManager().createUser("404", "", ""));
        }

        request.setAttribute("hunt", hunt);
        request.setAttribute("logged", SessionHandler.isSignedIn(request));

        User current = SessionHandler.getUser(request);
        if (hunt != null) {
            List<Hunter>  hunterList = Collections.emptyList();

            if (current != null) {
                 hunterList = HuntingManager.getDonePoints(current,hunt);
            }

            if (hunterList.isEmpty()) {
                hunterList = HuntingManager.sendAllAsNotCheckedPoints(current,hunt);
            }

            request.setAttribute("done", hunterList);
        }

        request.setAttribute("title",hunt.getName());
    	this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/hunt.jsp").forward(request, response);

        // response.sendError(403)

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("appliaction/html");
        if(SessionHandler.isSignedIn(request)) {
          writer.write(new Boolean(checkPoint(request,response)).toString());
        }  else {
          writer.write("/home");
        }
    }

    private JSONObject parseRequestParams(HttpServletRequest request) {
        return new JSONObject(request.getParameter("param"));
    }

    private boolean checkPoint(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject params = parseRequestParams(request);
        String lat = params.get("lat").toString();
        String lng = params.get("lng").toString();

        String id = params.getString("id");

        if  (!Validator.isNumberFormat(id)) {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return false;
        }

        Integer huntId = Integer.parseInt(id);
        Hunt hunt= new HuntManager().find(huntId);
        boolean value = Boolean.parseBoolean(params.getString("value"));

        UserManager manager = new UserManager();

        if  (hunt == null) {
            return false;
        } else {
             User currentUser = SessionHandler.getUser(request);
            if (currentUser == null) {
                return false;
            } else {
                Hunter hunter = HuntingManager.getHunter(currentUser, hunt);
                if (hunter == null) {
                    manager.startHunting(currentUser,hunt);
                    return manager.checkCoordinate(currentUser,hunt,lat,lng,value);
                } else {
                    return manager.checkCoordinate(currentUser, hunt, lat, lng, value);
                }
            }
        }
    }
}
