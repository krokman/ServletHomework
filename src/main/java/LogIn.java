import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/LogIn")
public class LogIn extends HttpServlet {
	private UserDao dao;

	public LogIn() {
		super();
		dao = new UserDao();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		try {
			if (isUserExist(req, out) && isPasswordCorrected(req, out)) {
				adminCheck(req, resp);
				out.println("<h1>Welcome " + req.getParameter("Nickname"));
			}
		} catch (NullPointerException e) {
			out.println("<h1>Wrong data ");
		}
	}

	private boolean isUserExist(HttpServletRequest req, PrintWriter out) {
		if (dao.getUserByNickname(req.getParameter("Nickname")) == null) {
			out.println("There`s no such user");
			return false;
		}
		return true;
	}

	private boolean isPasswordCorrected(HttpServletRequest req, PrintWriter out) {
		if (!dao.getUserByNickname(req.getParameter("Nickname")).getPassword()
				.equals(req.getParameter("password"))) {
			out.println("Wrong password");
			return false;
		}
		return true;
	}

	private void adminCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (dao.getUserByNickname(req.getParameter("Nickname")).getRole().equalsIgnoreCase("admin")) {
			HttpSession newSession = req.getSession(true);

			//setting session to expiry in 5 mins
			newSession.setMaxInactiveInterval(5 * 60);

			Cookie message = new Cookie("privacy", "admin");
			resp.addCookie(message);
			resp.sendRedirect("/UserController?action=listUser");

		}
	}
}
