package servlet;

import service.CodeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Buy")
public class Buy extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(CodeService.checkCode(Integer.parseInt(req.getParameter("code")))){
			req.setAttribute("valid", "true");
			req.getRequestDispatcher("afterBuy.jsp").forward(req, resp);
		}
		req.setAttribute("valid", "false");
		req.getRequestDispatcher("afterBuy.jsp").forward(req, resp);
	}
}
