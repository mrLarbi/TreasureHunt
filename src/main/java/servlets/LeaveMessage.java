package servlets;

import backend.SessionHandler;
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
public class LeaveMessage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = SessionHandler.getUser(req);

        String to = req.getParameter("receiver");
        String messageContent = req.getParameter("content");

        UserManager  uManager = new UserManager();

        User receiver = uManager.find(to);

        if (receiver != null) {
            uManager.sendMessageTo(currentUser,receiver, messageContent);
        } else {

        }

        // TODO notify user.
    }
}
