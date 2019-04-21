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
		if (req.getParameter("Nickname") != null && req.getParameter("password") != null) {
			if (AccountsHandler.accounts.get(req.getParameter("Nickname")) != null) {
				if (AccountsHandler.accounts.get(req.getParameter("Nickname")).equals(req.getParameter("password"))) {
					out.println("<h1>Welcome " + req.getParameter("Nickname"));

				} else {
					out.println("<h1>Wrong Nickname or password " + req.getParameter("Nickname") + " " + req.getParameter("password"));
				}
			} else {
				out.println("<h1>No users with this Nickname");
			}
		}
	}
}
