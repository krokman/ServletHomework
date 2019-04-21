import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.when;

public class ServletTestLogIn {

	@Mock
	HttpServletRequest request;

	@Mock
	HttpServletResponse response;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		AccountsHandler.accounts.put("qwe","12344321");
	}

	@Test
	public void testLogInWithAllCorrected() throws IOException, ServletException {

		when(request.getParameter("Nickname")).thenReturn("qwe");
		when(request.getParameter("password")).thenReturn("12344321");

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		when(response.getWriter()).thenReturn(pw);

		LogIn myServlet = new LogIn();
		myServlet.service(request, response);
		String result = sw.getBuffer().toString().trim().replaceAll("\n", "").replaceAll("\r", "");
		Assert.assertEquals(result, "<h1>Welcome qwe");
	}

	@Test
	public void testLogInWithEmpty() throws IOException, ServletException {

		when(request.getParameter("Nickname")).thenReturn(null);
		when(request.getParameter("password")).thenReturn(null);

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		when(response.getWriter()).thenReturn(pw);

		LogIn myServlet = new LogIn();
		myServlet.service(request, response);
		String result = sw.getBuffer().toString().trim().replaceAll("\n", "").replaceAll("\r", "");
		Assert.assertEquals(result, "There`s no such user");
	}

	@Test
	public void testLogInWithWrongPassword() throws IOException, ServletException {
		when(request.getParameter("Nickname")).thenReturn("qwe");
		when(request.getParameter("password")).thenReturn("123");

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		when(response.getWriter()).thenReturn(pw);

		LogIn myServlet = new LogIn();
		myServlet.service(request, response);
		String result = sw.getBuffer().toString().trim().replaceAll("\n", "").replaceAll("\r", "");
		Assert.assertEquals(result, "Wrong password");
	}


}

