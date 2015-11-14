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
public class LeaveMessage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/leaveMessage.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = SessionHandler.getUser(req);

        String to = req.getParameter("to");

        if  (!Validator.isValidUsername(to)) {
            resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Username not valid");
            return;
        }

        String messageContent = req.getParameter("content");

        UserManager  uManager = new UserManager();

        User receiver = uManager.findUserByUsername(to);

        if (receiver != null && currentUser !=null) {
            uManager.sendMessageTo(currentUser,receiver, messageContent);
            resp.sendRedirect("profile");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "User name not valid");
        }

    }
}
