package servlets;

import backend.SessionHandler;
import backend.Validator;
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
        String userId = request.getParameter("id");
        User user;

        if (userId != null && !userId.isEmpty() && Validator.isNumberFormat(userId)) {
            UserManager userManager = new UserManager();
            user = userManager.find(Integer.parseInt(userId));
        } else {
            user = SessionHandler.getUser(request);
        }

        String phone = "None";
        String zipcode = "None";
        String name = "None";
        boolean isSameUser = false;

        String gender = "None";
        if (user.getGender() == 'M') {
            gender = "Male";
        } else if (user.getGender() == 'F') {
            gender = "Female";
        }

        if (user.getPhone() != null) {
            phone = user.getPhone();
        }

        if (user.getPostalcode() != 0) {
            zipcode = "" + user.getPostalcode();
        }

        if (user.getName() != null) {
            name = user.getName();
        }

        User currentUser = SessionHandler.getUser(request);
        if(currentUser.getId() == user.getId()) {
            isSameUser = true;
        }

        request.setAttribute("username", user.getUsername());
        request.setAttribute("email", user.getEmail());
        request.setAttribute("phone", phone);
        request.setAttribute("zipcode", zipcode);
        request.setAttribute("gender", gender);
        request.setAttribute("name", name);
        request.setAttribute("avatar", "" + user.getAvatar());
        request.setAttribute("createdhunts", user.getCreatedhunts());
        request.setAttribute("currenthunts", user.getCurrentHunts());
        request.setAttribute("friends", user.getMyFollowers());
        request.setAttribute("messages", user.getReceivedMessages());
        request.setAttribute("isSameUser", isSameUser);
        this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/viewProfile.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
