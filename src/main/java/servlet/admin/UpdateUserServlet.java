package servlet.admin;

import dao.UserDao;
import dao.UserDaoHib;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AdminServlet/update")
public class UpdateUserServlet extends HttpServlet {
	final static Logger logger = Logger.getLogger(UpdateUserServlet.class);
	private UserDao userDao = new UserDaoHib();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("user getting by id");
		int id = Integer.parseInt(req.getParameter("id"));
		User user = userDao.getById(User.class,id);
		req.setAttribute("user", user);
		req.getRequestDispatcher("/user.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("user updating");
		User user = new User(Integer.parseInt(req.getParameter("id")), req.getParameter("nickname"), req.getParameter("password"),
				req.getParameter("email"), req.getParameter("role"), true);
		userDao.update(user);
		resp.sendRedirect("/AdminServlet");
	}
}

