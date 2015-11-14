package servlets;

import backend.SessionHandler;
import backend.Validator;
import hibernate.managers.UserManager;
import hibernate.models.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Machi on 31/10/2015.
 */
public class FollowUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser  = SessionHandler.getUser(req);
        UserManager uManager = new UserManager();

        if (Validator.isNumberFormat(req.getParameter("user"))) {

            User agent = uManager.find(Integer.parseInt(req.getParameter("user")));

            if (agent != null) {
                uManager.addFriendshipBtn(agent,currentUser);
            } else {
                // Error message
            }

        } else {
            resp.sendError(404);
        }

        // TODO notify user

    }
}
