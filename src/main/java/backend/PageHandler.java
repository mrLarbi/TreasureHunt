package backend;

import javax.servlet.http.HttpServletRequest;

public class PageHandler {

    public static void setPageTitle(HttpServletRequest request, String title) {
        request.setAttribute("title", title);
    }

    public static void setHeaderTitle(HttpServletRequest request, String header) {
        request.setAttribute("header", header);
    }

    public static void setTitleAndHeader(HttpServletRequest request, String title) {
        setPageTitle(request, title);
        setHeaderTitle(request, title);
    }
}
