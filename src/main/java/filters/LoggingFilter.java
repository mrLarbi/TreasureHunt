package filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import backend.SessionHandler;
import hibernate.managers.UserManager;
import hibernate.models.entities.User;

// Request have to be filtered for user authentication
public class LoggingFilter implements Filter {

	// ServletContext context;

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		// context = config.getServletContext();
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp =(HttpServletResponse)response;

		User user = SessionHandler.getUser(req);

		Cookie cookie = SessionHandler.getCookie(req);

		if (user == null && cookie != null) {
			UserManager manager = new UserManager();
			user = manager.findByRemember(cookie.getValue());
			SessionHandler.setUser(req,user);
		}

		if (user != null) {
			chain.doFilter(request, response);
		} else {
			// Redirect to index page to log ins
			// context.getRequestDispatcher("/home").forward(request, response);
			resp.sendRedirect("/home");
		}
			
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		// context = null;
	}

}
