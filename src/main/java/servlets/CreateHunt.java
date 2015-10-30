package servlets;

import hibernate.managers.HuntManager;
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
public class CreateHunt extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        HuntManager manager = new HuntManager();
        User currentUser = (User)request.getSession().getAttribute("user");
        Hunt  hunt = manager.createHunt(name,currentUser);
        manager.addHunt(hunt);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
