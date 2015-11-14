package servlets;

import backend.SessionHandler;
import backend.Validator;
import hibernate.managers.UserManager;
import hibernate.models.entities.User;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Created by mohameddd on 11/11/15.
 */
public class EditProfile extends HttpServlet {

    private final static Logger LOGGER =
            Logger.getLogger(EditProfile.class.getCanonicalName());
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     updateProfile(request,response);
    }

    private JSONObject parseRequest(HttpServletRequest request) {
        return new JSONObject(request.getParameter("param"));
    }

    private void updateProfile (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = SessionHandler.getUser(request);
        UserManager userManager = new UserManager();

        String param = request.getParameter("param");
        if (param != null && !param.isEmpty()) {
            JSONObject params = parseRequest(request);
            String name = params.getString("newName");
            String zip = params.getString("newZip");
            String phone = params.getString("newPhone");

            if  (Validator.isValidPhoneNumber(phone)) {
                currentUser.setPhone(phone);
            }

            if (Validator.isValidPostalCode(zip)) {
                currentUser.setPostalcode(Integer.parseInt(zip));
            }

            if (name != null & !name.isEmpty()) {
                currentUser.setName(name);
            }

        }

        if (saveAvatarFile(request,response,currentUser.getUsername()+".png")) {
            currentUser.setAvatar(currentUser.getUsername());
        }

        if (userManager.updateUser(currentUser)) {
            this.getServletContext().getRequestDispatcher("user/profile").forward(request,response);
        } else {
            response.getWriter().write(new Boolean(false).toString());
        }
    }

    public boolean saveAvatarFile(HttpServletRequest request, HttpServletResponse response, final String fileName) throws IOException, ServletException {
        // Create path components to save the file
        User currentUser = SessionHandler.getUser(request);
        final String path = "/Resources/Images";
        final Part filePart = request.getPart("newAvatar");

        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();


        boolean result = false;
        try {

            File file = new File(path + File.separator + fileName);

            if(!file.exists() && !file.isDirectory())
            {
                file.createNewFile();
            }

            out = new FileOutputStream(file);
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024*1024*2];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            LOGGER.log(Level.INFO, "File{0}being uploaded to {1}",
                    new Object[]{fileName, path});

            result = true;
        } catch (FileNotFoundException fne) {
            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                    new Object[]{fne.getMessage()});

            result = false;

        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            if (writer != null) {
                writer.close();
            }

            return result;
        }
    }
}
