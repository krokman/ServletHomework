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

@WebServlet("/GoodServlet/update")
public class UpdateGoodServlet extends HttpServlet {
	final static Logger logger = Logger.getLogger(UpdateGoodServlet.class);
	private GoodDao goodDao = new GoodDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("good getting by id");
		int id = Integer.parseInt(req.getParameter("id"));
		Good good = goodDao.getGoodById(id);
		req.setAttribute("good", good);
		req.getRequestDispatcher("/good.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("good updating");
		Good good = new Good(Integer.parseInt(req.getParameter("id")), req.getParameter("name"), req.getParameter("description"),
				req.getParameter("price"));
		goodDao.updateGood(good);
		resp.sendRedirect("/GoodServlet");
	}
}

