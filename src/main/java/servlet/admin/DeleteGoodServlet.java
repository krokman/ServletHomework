package servlet.admin;

import dao.GoodDao;

import dao.GoodDaoHib;
import model.Good;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GoodServlet/delete")
public class DeleteGoodServlet extends HttpServlet {
	final static Logger logger = Logger.getLogger(DeleteGoodServlet.class);
	private GoodDao goodDao = new GoodDaoHib();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("good deleting");
		int id = Integer.parseInt(req.getParameter("id"));
		goodDao.delete(Good.class, id);
		resp.sendRedirect("/GoodServlet");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
