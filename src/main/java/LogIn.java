import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/LogIn")
public class LogIn extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		try {
			if (isUserNotExist(req, out) && isPasswordCorrected(req, out)) {
				out.println("<h1>Welcome " + req.getParameter("Nickname"));
			}
		} catch (NullPointerException e) {
			out.println("<h1>Wrong data ");
		}
	}

	private boolean isUserNotExist(HttpServletRequest req, PrintWriter out) {
		if (UserHandler.usersStorage.getUserByNickname(req.getParameter("Nickname")) == null) {
			out.println("There`s no such user");
			return false;
		}
		return true;
	}

	private boolean isPasswordCorrected(HttpServletRequest req, PrintWriter out) {
		if (!UserHandler.usersStorage.getUserByNickname(req.getParameter("Nickname")).getPassword()
				.equals(req.getParameter("password"))) {
			out.println("Wrong password");
			return false;
		}
		return true;
	}
}
