import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/user.jsp";
	private static String LIST_USER = "/listUser.jsp";
	private UserDao dao;

	public UserController() {
		super();
		dao = new UserDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="";
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("delete")){
			String nickname = request.getParameter("nickname");
			dao.deleteUser(nickname);
			forward = LIST_USER;
			request.setAttribute("users", dao.getAllUsers());
		} else if (action.equalsIgnoreCase("edit")){
			forward = INSERT_OR_EDIT;
			String nickname = request.getParameter("nickname");
			User user = dao.getUserByNickname(nickname);
			request.setAttribute("user", user);
		} else if (action.equalsIgnoreCase("listUser")){
			forward = LIST_USER;
			request.setAttribute("users", dao.getAllUsers());
		} else {
			forward = INSERT_OR_EDIT;
		}
		request.getRequestDispatcher(forward).forward(request,response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setNickname(request.getParameter("nickname"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		String nickname = dao.getUserByNickname(request.getParameter("nickname")).getNickname();
		if(nickname == null || nickname.isEmpty())
		{
			dao.addUser(user);
		}
		else{
			dao.updateUser(user);
		}
		RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
		request.setAttribute("users", dao.getAllUsers());
		view.forward(request, response);
	}
}