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

@WebServlet("/AdminServlet/delete")
public class DeleteUserServlet extends HttpServlet {
	final static Logger logger = Logger.getLogger(DeleteUserServlet.class);
	private UserDao userDao = new UserDaoHib();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("user deleting");
		int id = Integer.parseInt(req.getParameter("id"));
		userDao.delete(User.class,id);
		resp.sendRedirect("/AdminServlet");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
