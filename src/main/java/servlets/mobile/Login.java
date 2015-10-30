package servlets.mobile;

import hibernate.managers.UserManager;
import hibernate.models.entities.User;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by Machi on 30/10/2015.
 */
public class Login extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserManager manager = new UserManager();
        String login = request.getParameter("email");
        String password = request.getParameter("password");

        User user = manager.find(login,password);
        Writer writer = response.getWriter();
        response.setContentType("application/json");
        JsonObject userJsonObject;
        if (user != null) {
             userJsonObject = Json.createObjectBuilder()
                     .add("success", true)
                     .add("id", user.getId())
                     .add("email", user.getEmail())
                     .add("username", user.getUsername())
                     .add("realname", user.getName())
                     .add("zipcode", user.getPostalcode())
                     .build();
        } else {
            userJsonObject = Json.createObjectBuilder().add("success", false).build();
        }

        writer.write(userJsonObject.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
