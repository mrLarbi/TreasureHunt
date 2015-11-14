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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser  = SessionHandler.getUser(req);
        UserManager uManager = new UserManager();

        if (Validator.isValidUsername(req.getParameter("username"))) {

            User agent = uManager.findUserByUsername(req.getParameter("username"));

            if (agent != null && currentUser != null) {
                if (currentUser.getMyFollowers().contains(agent)
                        || currentUser.getListOFfollowed().contains(agent)) {
                    return;
                } else if (currentUser == agent) {
                    resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,"You can not follow yourself");
                    return; // Can not follow oneself.
                }
                uManager.addFriendshipBtn(agent,currentUser);
                resp.sendRedirect("profile");
            } else {
                // Error message
                resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,"User does not exist or you are not connected");
            }

        } else {
            resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,"Error");
        }

        // TODO notify user

    }
}
