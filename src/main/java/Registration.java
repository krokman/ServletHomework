import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/Register")
public class Registration extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.println("<h1>Registration page<p>");
		try {
			if (isCorrectedNickname(req, out) && isCorrectedPassword(req, out) && isUserNotAlreadyExist(req, out)) {
				out.println("<h1>Account succeeded created<p> with Nickname - " + req.getParameter("Nickname")
						+ " and Password - " + req.getParameter("password"));
				UserHandler.usersStorage.addUser(new User(req.getParameter("Nickname"),
						req.getParameter("password")));
			}
		} catch (NullPointerException e) {
			out.println("Empty data, write ur data");
		}
	}

	private boolean isCorrectedNickname(HttpServletRequest req, PrintWriter out) {
		if (!req.getParameter("Nickname").matches("^[A-Za-z0-9]{3,18}$")) {
			out.println("<h1>Wrong Nickname, must be more 3 symbols and English symbols only");
			return false;
		}
		return true;
	}


	private boolean isCorrectedPassword(HttpServletRequest req, PrintWriter out) {
		if (!req.getParameter("password").matches("^[A-Za-z0-9]{6,18}$")) {
			out.println("<h1>Wrong password, must be more 6 symbols English symbols only");
			return false;
		}
		return true;
	}

	private boolean isUserNotAlreadyExist(HttpServletRequest req, PrintWriter out) {
		if (UserHandler.usersStorage.getUserByNickname(req.getParameter("Nickname")) != null) {
			out.println("User with this Nickname already exist");
			return false;
		}
		return true;
	}
}

