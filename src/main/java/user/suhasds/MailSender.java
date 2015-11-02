package user.suhasds;

import java.io.Console;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class MailSender {

	private String fromId = null;
	private String password = null;
	private Session session = null;

	public MailSender(String from, String pwd) {
		fromId = from;
		password = pwd;

		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		properties.put("mail.smtp.port", "587"); // TLS Port
		properties.put("mail.smtp.auth", "true"); // enable authentication
		properties.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

		// create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromId, password);
			}
		};

		// create a session for the user
		session = Session.getDefaultInstance(properties, auth);
	}

	public boolean send(String to, String subject, String body) {

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(fromId));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			message.setText(body);

			// Send message
			Transport.send(message);

		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		}

		return true;
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("Enter sender's email ID: ");
		String from = scan.nextLine();

		Console cnsl = System.console();
		char[] pwd = cnsl.readPassword("Password: ");

		MailSender ms = new MailSender(from, new String(pwd));

		System.out.println("Reciever's email ID: ");
		String to = scan.nextLine();

		System.out.println("Enter the subject: ");
		String subject = scan.nextLine();

		System.out.println("Enter the message: ");
		String msg = scan.nextLine();

		boolean success = ms.send(to, subject, msg);

		if (success == true)
			System.out.println("Sent message successfully....");
		else
			System.out.println("Unsuccessful....");

		scan.close();
	}
}
