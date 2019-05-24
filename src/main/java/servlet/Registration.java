package servlet;

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

@WebServlet(value = "/Registration")
public class Registration extends HttpServlet {
	private UserDao dao = new UserDaoHib();
	final static Logger logger = Logger.getLogger(Registration.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User userReg= new User(req.getParameter("Nickname"),
				req.getParameter("password"), req.getParameter("email"), req.getParameter("role"), true);
		dao.add(userReg);
		req.setAttribute("user", userReg);
		logger.debug("new user registered");
		logger.trace("redirect to afterRegistration page");
		req.getRequestDispatcher("afterRegistration.jsp").forward(req, resp);

	}
}