package util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import io.github.cdimascio.dotenv.Dotenv;

// Send an alert to your email when the robot finds a super rare item, this will notify you to clear the capcha box for security reasons.
// Set to gmail and makes use of the SMTP architecture

public final class JavaMailUtil {

	public static void sendEmail(String neopetItem, String price) throws MessagingException {
		Dotenv dotenv = Dotenv.load();
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com"); // Configurations set to gmail by default accessing port 587
		properties.put("mail.smtp.port", "587");
	
		String emailAddress = dotenv.get("gmail_address") + "@gmail.com"; // Specify the credentials in your OWN .env file
		String password = dotenv.get("gmail_password"); // Securely saved password for particular account
		
		Session session = Session.getInstance(properties, new Authenticator() {
		
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailAddress, password); // Authenticate credentials here
			}
		});
		
		Message message = prepareMessage(session, emailAddress, password, neopetItem, price); // Specify the item bought and the price
		
		Transport.send(message);
		System.out.println("Message sent successfully");
	}
	
	public static Message prepareMessage(Session session, String emailAddress, String password, String neopetItem, String price) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailAddress));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress)); // Recipient is the same as sender
			message.setSubject(neopetItem);
			message.setText(neopetItem + " " + price + " " + " please clear capacha now!");
			return message;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
}
