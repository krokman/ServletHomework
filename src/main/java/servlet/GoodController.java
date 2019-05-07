package servlet;

import dao.GoodDao;
import model.Good;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GoodController")
public class GoodController extends HttpServlet {

	private GoodDao dao;
	final static Logger logger = Logger.getLogger(GoodController.class);
	private static String INSERT_OR_EDIT = "/good.jsp";
	private static String LIST_GOOD = "/goodControllerPage.jsp";

	public GoodController() {
		dao = new GoodDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		logger.debug("getting request parameter action = " + action);
		if (action.equalsIgnoreCase("delete")) {
			logger.debug("good deleting");
			String name = request.getParameter("name");
			dao.deleteGood(name);
			forward = LIST_GOOD;
			request.setAttribute("goods", dao.getAllGoods());
		} else if (action.equalsIgnoreCase("edit")) {
			logger.debug("good editing");
			forward = INSERT_OR_EDIT;
			String name = request.getParameter("name");
			Good good = dao.getGoodByName(name);
			request.setAttribute("good", good);
		} else if (action.equalsIgnoreCase("goodList")) {
			logger.debug("good list getting");
			forward = LIST_GOOD;
			request.setAttribute("goods", dao.getAllGoods());
		} else {
			logger.debug("good adding");
			forward = INSERT_OR_EDIT;
		}
		logger.trace("redirect to " + forward);
		request.getRequestDispatcher(forward).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = dao.getGoodByName(request.getParameter("name")).getName();
		if (name == null || name.isEmpty()) {
			logger.debug("good adding");
			Good good = new Good(request.getParameter("name"), request.getParameter("description"),
					request.getParameter("price"));
			dao.addGood(good);
		} else {
			logger.debug("good editing");
			Good good = new Good(request.getParameter("name"), request.getParameter("description"),
					request.getParameter("price"));
			dao.updateGood(good);
		}
		RequestDispatcher view = request.getRequestDispatcher(LIST_GOOD);
		request.setAttribute("goods", dao.getAllGoods());
		logger.trace("redirect to admin page");
		view.forward(request, response);
	}
}
