package servlet;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(value = "/LogIn")
public class LogIn extends HttpServlet {
	private UserDao dao;

	public LogIn() {
		dao = new UserDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (isUserExist(req) && isPasswordCorrected(req) && !isAdmin(req, resp)) {
			req.getSession().setAttribute("user", dao.getUserByNickname(req.getParameter("Nickname")));
			req.getRequestDispatcher("/Market").forward(req, resp);
		}
	}

	private boolean isUserExist(HttpServletRequest req) {
		if (dao.getUserByNickname(req.getParameter("Nickname")) == null) {
			return false;
		}
		return true;
	}

	private boolean isPasswordCorrected(HttpServletRequest req) {
		if (!dao.getUserByNickname(req.getParameter("Nickname")).getPassword()
				.equals(req.getParameter("password"))) {
			return false;
		}
		return true;
	}

	private boolean isAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (dao.getUserByNickname(req.getParameter("Nickname")).getRole().equalsIgnoreCase("admin")) {
			HttpSession newSession = req.getSession(true);

			//setting session to expiry in 5 mins
			newSession.setMaxInactiveInterval(5 * 60);

			Cookie message = new Cookie("privacy", "admin");
			resp.addCookie(message);
			resp.sendRedirect("/UserController?action=listUser");
		return true;
		}
		return false;
	}

}
