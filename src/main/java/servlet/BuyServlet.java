package servlet;

import org.apache.log4j.Logger;
import service.CodeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	final static Logger logger = Logger.getLogger(BuyServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (CodeService.checkCode(Integer.parseInt(req.getParameter("code")))) {
			req.setAttribute("valid", "true");
			logger.debug("purchase complete");
			logger.trace("redirect to afterBuy page with valid code");
			req.getRequestDispatcher("afterBuy.jsp").forward(req, resp);
		}
		req.setAttribute("valid", "false");
		logger.debug("invalid purchase code");
		logger.trace("redirect to afterBuy page with invalid code");
		req.getRequestDispatcher("afterBuy.jsp").forward(req, resp);
	}
}
