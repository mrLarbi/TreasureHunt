package servlets;

import backend.SessionHandler;
import hibernate.managers.UserManager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hibernate.models.entities.User;

/**
 * Created by mohameddd on 11/11/15.
 */
public class Profile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("id");

        User user;

        if (username != null && !username.isEmpty()) {
            UserManager userManager = new UserManager();
            user = userManager.findUserByUsername(username);
        } else {
            user = SessionHandler.getUser(request);
        }

        String phone = "None";
        String zipcode = "None";

        String gender = "None";
        if (user.getGender() == 'M') {
            gender = "Male";
        } else if (user.getGender() == 'F') {
            gender = "Female";
        }

        System.out.println(user.getPhone());

        if ("".equals(user.getPhone())) {
            phone = user.getPhone();
        }

        if (user.getPostalcode() != 0) {
            zipcode = "" + user.getPostalcode();
        }

        request.setAttribute("username", user.getUsername());
            request.setAttribute("email", user.getEmail());
            request.setAttribute("phone", phone);
            request.setAttribute("zipcode", zipcode);
            request.setAttribute("gender", gender);
            request.setAttribute("avatar", "" + user.getAvatar());
            this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/viewProfile.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
