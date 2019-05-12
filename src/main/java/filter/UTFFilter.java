package filter;

import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


@WebFilter("/*")
public class UTFFilter implements Filter {
	final static Logger logger = Logger.getLogger(UTFFilter.class);
	private String encoding;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("requestEncoding");
		if (encoding == null) {
			encoding = "UTF-8";
		}
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		logger.debug("UTFFilter instaling");
		if (null == servletRequest.getCharacterEncoding()) {
			servletRequest.setCharacterEncoding(encoding);
		}
		servletResponse.setContentType("text/html; charset=UTF-8");
		servletResponse.setCharacterEncoding("UTF-8");

		filterChain.doFilter(servletRequest, servletResponse);

	}

	@Override
	public void destroy() {

	}
}
