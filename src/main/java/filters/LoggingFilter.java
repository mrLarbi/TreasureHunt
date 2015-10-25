package filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import hibernate.models.entities.User;

// TODO implement this 
// Request have to be filtered for user authentication
public class LoggingFilter implements Filter {

	ServletContext context;

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		context = config.getServletContext();
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user != null) {
			chain.doFilter(request, response);
		} else {
			// Redirect to index page to log ins
			context.getRequestDispatcher("index.jsp").forward(request, response);
		}
			
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		context = null;
	}

}
