package servlet.admin;


import dao.UserDao;
import dao.UserDaoHib;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	final static Logger logger = Logger.getLogger(AdminServlet.class);
	private UserDao userDao = new UserDaoHib();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("user list getting");
		req.setAttribute("users", userDao.getAllUsers());
		req.getRequestDispatcher("listUser.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
