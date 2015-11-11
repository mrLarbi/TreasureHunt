package servlets;

import hibernate.managers.UserManager;

import java.io.IOException;
import java.io.Writer;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hibernate.managers.UserManager;
import hibernate.models.entities.User;

import javax.json.Json;
import javax.json.JsonObject;
/**
 * Created by mohameddd on 11/11/15.
 */
public class Profile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        System.out.println(id);

        UserManager userManager = new UserManager();
        User user = userManager.findUserByUsername(id);

        String phone = "None";
        String zipcode = "None";

        String gender = "None";
        if(user.getGender() == 'M') {
            gender = "Male";
        }
        else if(user.getGender() == 'F') {
            gender = "Female";
        }

        System.out.println(user.getPhone());

        if("".equals(user.getPhone())) {
            phone = user.getPhone();
        }

        if(user.getPostalcode() != 0) {
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
