package com.siss.test.mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author econtreras
 */
public class Main {

	public static void main(String[] args) throws MessagingException {
		new Main().run();
	}

	private void run() throws MessagingException {
        Message message = new MimeMessage(getSession());

		message.setFrom(new InternetAddress("econtreras@siss.cl"));
        message.addRecipient(RecipientType.TO, new InternetAddress("enger_omar@hotmail.com"));
		//message.addFrom(new InternetAddress[] { new InternetAddress("sac@siss.cl") });

        message.setSubject("the subject");
		message.setContent("the body", "text/plain");

        Transport.send(message);
	}

	private Session getSession() {
		Authenticator authenticator = new Authenticator();

		Properties properties = new Properties();
		properties.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());
		properties.setProperty("mail.smtp.auth", "true");

		properties.setProperty("mail.smtp.host", "correo.siss.cl");
		properties.setProperty("mail.smtp.port", "25");

        properties.setProperty("mail.smtp.allow8bitmime", "true");
        properties.setProperty("mail.smtp.auth.mechanisms", "GSSAPI NTLM");
        properties.setProperty("mail.smtp.starttls.enable","true");
        properties.setProperty("mail.smtp.starttls.required","true");

        properties.setProperty("mail.smtp.socketFactory.port", "25");
        properties.setProperty("mail.smtp.socketFactory.class","javax.net.SocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback","false");

        return Session.getInstance(properties, authenticator);
	}

	private class Authenticator extends javax.mail.Authenticator {
		private PasswordAuthentication authentication;

		public Authenticator() {
			String username = "econtreras";
			String password = "147896325";
			authentication = new PasswordAuthentication(username, password);
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return authentication;
		}
	}

}
