package servlets;

import backend.SessionHandler;
import hibernate.managers.CoordinateManager;
import hibernate.managers.HuntManager;
import hibernate.models.entities.Coordinate;
import hibernate.models.entities.Hunt;
import hibernate.models.entities.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Machi on 30/10/2015.
 */
public class CreateHunt extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/createHunt.jsp").forward(request, response);
    	
    }

    private JSONObject parseRequestParams(HttpServletRequest request) {
        return new JSONObject(request.getParameter("param"));
    }

    private ArrayList<Coordinate> getCoordinates(JSONObject params) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        Coordinate coordinate;

        JSONArray jPoints = params.getJSONArray("points");
        JSONObject jPoint;

        String coordName;
        String coordLat;
        String coordLng;

        for (int i= 0; i < jPoints.length(); ++i) {
               jPoint = jPoints.getJSONObject(i);
               coordName = jPoint.getString("name");
               coordLat = jPoint.getString("lat");
               coordLng = jPoint.getString("lng");

            coordinate = CoordinateManager.createCoordinate(coordName,coordLat,coordLng, "");
            coordinates.add(coordinate);
        }

        //TODO parse json data and results in coordinates

        return coordinates;
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = SessionHandler.getUser(request);

        JSONObject params = parseRequestParams(request);

        String name = params.getString("name");

        HuntManager manager = new HuntManager();
        Hunt  hunt = manager.createHunt(name,currentUser);

        ArrayList<Coordinate> coordinates = getCoordinates(params);

        CoordinateManager coordinateManager = new CoordinateManager();

        coordinateManager.addCoordinates(coordinates);

        manager.addHunt(hunt);

        manager.addCoordinatesToHunt(hunt, coordinates);


        this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/displayhunt.jsp").forward(request, response);
    }
}
