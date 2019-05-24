package servlet;

import dao.GoodDaoSQL;
import model.User;
import org.apache.log4j.Logger;
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
	final static Logger logger = Logger.getLogger(Market.class);
	private GoodDaoSQL goodDaoSQL = new GoodDaoSQL();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.trace("attribute good is setting ");
		req.setAttribute("goods", goodDaoSQL.getAllGoods());

		req.setAttribute("baskets", req.getSession().getAttribute("BasketGood"));
		logger.trace("redirect to market");
		req.getRequestDispatcher("market.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int code = RandomCodeUtil.getRandomCode();
		logger.debug("random code initializing = " + code);
		CodeService.codeList.add(code);
		logger.trace("attribute code is setting");
		req.getSession().setAttribute("code", code);
		User user = (User) req.getSession().getAttribute("user");
		logger.trace("user object session getting attribute = " + user);
		MailService.sendMessage(user.getEmail(), code);
		logger.debug("email with code was sent");
		logger.trace("redirect to buy");
		req.getRequestDispatcher("buy.jsp").forward(req, resp);
	}
}