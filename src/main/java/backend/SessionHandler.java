package backend;

import javax.servlet.http.HttpServletRequest;

public class SessionHandler {

    public static void checkSessionLogged(HttpServletRequest request)
    {
        if(request.getSession().getAttribute("isLogged") == null)
        {
            request.getSession().setAttribute("isLogged", false);
        }
    }

    public static void setLogged(HttpServletRequest request, boolean logged)
    {
        request.getSession().setAttribute("isLogged", logged);
    }

    public static boolean isLogged(HttpServletRequest request)
    {
        return (boolean) request.getSession().getAttribute("isLogged");
    }
}
