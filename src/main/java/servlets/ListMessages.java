package servlets;

import backend.SessionHandler;
import hibernate.models.entities.Message;
import hibernate.models.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by Machi on 31/10/2015.
 */
public class ListMessages extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = SessionHandler.getUser(req);

        Set<Message> UserReceivedMessages = user.getReceivedMessages();

        Set<Message> userSentMessages = user.getSentMessages();

        // TODO list all messages
    }
}
