package servlet;

import dao.UserDaoSQL;
import org.apache.log4j.Logger;
import service.BasketGood;
import util.HashPasswordUtil;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/LogIn")
public class LogIn extends HttpServlet {
	private UserDaoSQL dao;
	final static Logger logger = Logger.getLogger(LogIn.class);

	public LogIn() {
		dao = new UserDaoSQL();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (isUserExist(req) && isPasswordCorrected(req) && !isAdmin(req, resp)) {
			logger.debug("user entered");
			req.getSession().setAttribute("BasketGood", new BasketGood().goodsList);
			req.getSession().setAttribute("user", dao.getUserByNickname(req.getParameter("Nickname")));
			logger.trace("redirect to market with user");
			req.getRequestDispatcher("/Market").forward(req, resp);
		}
	}

	private boolean isUserExist(HttpServletRequest req) {
		if (dao.getUserByNickname(req.getParameter("Nickname")) == null) {
			logger.warn("wrong nickname was entered");
			return false;
		}
		return true;
	}

	private boolean isPasswordCorrected(HttpServletRequest req) {
		if (!dao.getUserByNickname(req.getParameter("Nickname")).getPassword()
				.equals(HashPasswordUtil.getHashPassword(req.getParameter("password"),
						dao.getUserByNickname(req.getParameter("Nickname")).getSalt()))) {
			logger.warn("wrong password was entered");
			return false;
		}
		return true;
	}

	private boolean isAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (dao.getUserByNickname(req.getParameter("Nickname")).getRole().equalsIgnoreCase("admin")) {
			logger.debug("Admin entered");
			HttpSession newSession = req.getSession(true);
			logger.trace("setting new session for admin");
			//setting session to expiry in 5 mins
			newSession.setMaxInactiveInterval(5 * 60);
			logger.trace("adding new cookie for admin");
			Cookie message = new Cookie("privacy", "admin");
			resp.addCookie(message);
			logger.trace("redirect to market with admin");
			resp.sendRedirect("/AdminServlet");
			return true;
		}
		return false;
	}

}
