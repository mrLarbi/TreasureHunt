package servlets;

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
        User hunter = (User)req.getSession().getAttribute("user");
        String huntId = req.getParameter("hunt");
        HuntManager manager = new HuntManager();
        Hunt hunt = manager.find(huntId);
        UserManager userManager = new UserManager();
        userManager.startHunting(hunter,hunt);
    }
}
