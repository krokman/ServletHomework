package servlet.admin;

import dao.GoodDao;
import model.Good;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GoodServlet/AddGood")
public class AddGoodServlet extends HttpServlet {
	private GoodDao goodDao = new GoodDao();
	final static Logger logger = Logger.getLogger(AddGoodServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("adding good");
		goodDao.addGood(new Good(
				req.getParameter("name"),
				req.getParameter("description"),
				req.getParameter("price")));
		resp.sendRedirect("/GoodServlet");
	}

}
