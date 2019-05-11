package filter;

import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/AdminServlet/*", "/GoodServlet/*"})
public class UserControllerFilter implements Filter {
	final static Logger logger = Logger.getLogger(UserControllerFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		logger.trace("entering filter");
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String message = "";
		logger.trace("checking cookies");
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("privacy")) {
					message = cookie.getValue();
				}
			}
		}
		if (message.equalsIgnoreCase("admin")) {
			filterChain.doFilter(servletRequest, servletResponse);
			logger.trace("admin request");
		} else {
			request.getRequestDispatcher("/LogIn").forward(servletRequest, servletResponse);
			logger.debug("someone without admin privacy trying get admin data");
		}
	}

	@Override
	public void destroy() {
	}
}

