package servlets;

import hibernate.managers.UserManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by Machi on 12/11/2015.
 */
public class EmailAvailable extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserManager manager = new UserManager();
        String userMail = request.getParameter("email");
        Writer writer = response.getWriter();
        response.setContentType("application/json");

        if  (manager.findUserByEmail(userMail) == null ) {
            writer.write(new Boolean(true).toString());
        } else {
            writer.write(new Boolean(false).toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
