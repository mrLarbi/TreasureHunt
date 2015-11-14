package servlets;

import backend.SessionHandler;
import backend.Validator;
import hibernate.managers.HuntManager;
import hibernate.managers.UserManager;
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
public class StartHunt extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User hunter = SessionHandler.getUser(req);
        String huntId = req.getParameter("hunt");

        if  (!Validator.isNumberFormat(huntId)) {
            resp.sendError(405);
            return;
        }
        HuntManager manager = new HuntManager();
        Hunt hunt = manager.find(Integer.parseInt(huntId));

        UserManager userManager = new UserManager();
        userManager.startHunting(hunter,hunt);
    }
}
