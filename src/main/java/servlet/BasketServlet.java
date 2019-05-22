package servlet;

import model.Good;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Basket")
public class BasketServlet extends HttpServlet {
	final static Logger logger = Logger.getLogger(BasketServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("adding good to basket");
		ArrayList<Good> basketGood = (ArrayList<Good>) req.getSession().getAttribute("BasketGood");
		basketGood.add(new Good(req.getParameter("name"), req.getParameter("description"), req.getParameter("price")));
		req.getSession().setAttribute("BasketGood", basketGood);
		logger.debug("redirect to market");
		resp.sendRedirect("/Market");
	}
}