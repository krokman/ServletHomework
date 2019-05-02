package servlet;

import dao.GoodDao;
import model.User;
import service.CodeService;
import service.MailService;
import util.RandomCodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Market")
public class Market extends HttpServlet {

	private GoodDao goodDao = new GoodDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("goods", goodDao.getAllGoods());
		req.getRequestDispatcher("market.jsp").forward(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int code = RandomCodeUtil.getRandomCode();
		CodeService.codeList.add(code);
		req.getSession().setAttribute("code", code);
		User user = (User)req.getSession().getAttribute("user");
		MailService.sendMessage(user.getEmail(), code);
		req.getRequestDispatcher("buy.jsp").forward(req,resp);
	}
}
