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
import java.util.List;

/**
 * Created by Machi on 12/11/2015.
 */
public class Search extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phrase = request.getParameter("phrase");

        List<Hunt> hunts = new HuntManager().searchHuntByName(phrase);
        List<User> users = new UserManager().searchUserByUsernamae(phrase);

        request.setAttribute("hunts", hunts);
        request.setAttribute("users", users);

        request.getRequestDispatcher("/WEB-INF/JSP/search.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
