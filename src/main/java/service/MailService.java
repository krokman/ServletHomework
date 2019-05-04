package service;

import org.apache.log4j.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService {
	final static Logger logger = Logger.getLogger(MailService.class);
	public static void sendMessage(String toEmail, int code) {

		final String username = "javatest54321@gmail.com";
		final String password = "Java54321test";
		logger.debug("entering google email with data -" +username + " " + password);
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		logger.debug("properties initializing " + props);

		Session session = Session.getInstance(props,
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			logger.debug("sending message");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("javatest54321@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toEmail));
			message.setSubject("Code confirm");
			message.setText(String.valueOf(code));

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			logger.error("cant send a message" + e);
			throw new RuntimeException(e);
		}
	}
}
