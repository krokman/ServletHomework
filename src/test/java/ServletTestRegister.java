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

public class ServletTestRegister {

	@Mock
	HttpServletRequest request;

	@Mock
	HttpServletResponse response;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testRegisterWithAllCorrected() throws IOException, ServletException {

		when(request.getParameter("Nickname")).thenReturn("qwe");
		when(request.getParameter("password")).thenReturn("12344321");

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		when(response.getWriter()).thenReturn(pw);

		Registration myServlet = new Registration();
		myServlet.service(request, response);
		String result = sw.getBuffer().toString().trim().replaceAll("\n", "").replaceAll("\r", "");
		Assert.assertEquals(result, "<h1>Registration page<p>" +
				"<h1>Account succeeded created<p> with Nickname - qwe and Password - 12344321");
	}

	@Test
	public void testRegisterWithEmpty() throws IOException, ServletException {

		when(request.getParameter("Nickname")).thenReturn(null);
		when(request.getParameter("password")).thenReturn(null);

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		when(response.getWriter()).thenReturn(pw);

		Registration myServlet = new Registration();
		myServlet.service(request, response);
		String result = sw.getBuffer().toString().trim().replaceAll("\n", "").replaceAll("\r", "");
		Assert.assertEquals(result, "<h1>Registration page<p>Empty data, write ur data");
	}

	@Test
	public void testRegisterWithWrongNickname() throws IOException, ServletException {
		when(request.getParameter("Nickname")).thenReturn("1");
		when(request.getParameter("password")).thenReturn("12344321");

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		when(response.getWriter()).thenReturn(pw);

		Registration myServlet = new Registration();
		myServlet.service(request, response);
		String result = sw.getBuffer().toString().trim().replaceAll("\n", "").replaceAll("\r", "");
		Assert.assertEquals(result, "<h1>Registration page<p><h1>Wrong Nickname, must be more 3 symbols and English symbols only");
	}

	@Test
	public void testRegisterWithWrongPassword() throws IOException, ServletException {
		when(request.getParameter("Nickname")).thenReturn("Akamade");
		when(request.getParameter("password")).thenReturn("123");

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		when(response.getWriter()).thenReturn(pw);

		Registration myServlet = new Registration();
		myServlet.service(request, response);
		String result = sw.getBuffer().toString().trim().replaceAll("\n", "").replaceAll("\r", "");
		Assert.assertEquals(result, "<h1>Registration page<p><h1>Wrong password, must be more 6 symbols English symbols only");
	}
}
