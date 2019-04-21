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

		if (isUserNotExist(req, out) && isPasswordCorrected(req, out)) {
			out.println("<h1>Welcome " + req.getParameter("Nickname"));
		}
	}

	private boolean isUserNotExist(HttpServletRequest req, PrintWriter out) {
		if (AccountsHandler.accounts.get(req.getParameter("Nickname")) == null) {
			out.println("There`s no such user");
			return false;
		}
		return true;
	}

	private boolean isPasswordCorrected(HttpServletRequest req, PrintWriter out) {
		if (!AccountsHandler.accounts.get(req.getParameter("Nickname")).equals(req.getParameter("password"))) {
			out.println("Wrong password");
			return false;
		}
		return true;
	}
}
