import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/Registration")
public class Registration extends HttpServlet {
	private UserDao dao;

	public Registration() {
		dao = new UserDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (isCorrectedNickname(req) && isCorrectedPassword(req)) {
			dao.addUser(new User(req.getParameter("Nickname"),
					req.getParameter("password"), req.getParameter("email"), req.getParameter("role")));
			req.setAttribute("user", dao.getUserByNickname(req.getParameter("Nickname")));
			req.getRequestDispatcher("afterRegistration.jsp").forward(req, resp);
		}else {
			req.setAttribute("data", "wrong");
			req.getRequestDispatcher("afterRegistration.jsp").forward(req, resp);
		}
	}


	private boolean isCorrectedNickname(HttpServletRequest req) {
		if (!req.getParameter("Nickname").matches("^[A-Za-z0-9]{3,18}$")) {
			return false;
		}
		return true;
	}


	private boolean isCorrectedPassword(HttpServletRequest req) {
		if (!req.getParameter("password").matches("^[A-Za-z0-9]{6,18}$")) {
			return false;
		}
		return true;
	}


}