package servlet;

import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/UserController")
public class UserController extends HttpServlet {
	final static Logger logger = Logger.getLogger(UserController.class);
	private static String INSERT_OR_EDIT = "/user.jsp";
	private static String LIST_USER = "/listUser.jsp";
	private UserDao dao;

	public UserController() {
		dao = new UserDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		logger.debug("getting request parameter action = " + action);
		if (action.equalsIgnoreCase("delete")) {
			logger.debug("user deleting");
			String nickname = request.getParameter("nickname");
			dao.deleteUser(nickname);
			forward = LIST_USER;
			request.setAttribute("users", dao.getAllUsers());
		} else if (action.equalsIgnoreCase("edit")) {
			logger.debug("user editing");
			forward = INSERT_OR_EDIT;
			String nickname = request.getParameter("nickname");
			User user = dao.getUserByNickname(nickname);
			request.setAttribute("user", user);
		} else if (action.equalsIgnoreCase("listUser")) {
			logger.debug("user list getting");
			forward = LIST_USER;
			request.setAttribute("users", dao.getAllUsers());
		} else {
			logger.debug("user adding");
			forward = INSERT_OR_EDIT;
		}
		logger.trace("redirect to " + forward);
		request.getRequestDispatcher(forward).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = new User();
		user.setNickname(request.getParameter("nickname"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		user.setRole(request.getParameter("role"));
		logger.debug("user editing" + user);
		String nickname = dao.getUserByNickname(request.getParameter("nickname")).getNickname();
		if (nickname == null || nickname.isEmpty()) {
			logger.debug("user adding");
			dao.addUser(user);
		} else {
			logger.debug("user editing");
			dao.updateUser(user);
		}
		RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
		request.setAttribute("users", dao.getAllUsers());
		logger.trace("redirect to admin page");
		view.forward(request, response);
	}
}