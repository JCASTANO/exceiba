package service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import exepcion.EmailSenderException;

public class GmailEmailService implements EmailService {
	
	private static final Logger LOGGER = Logger.getLogger(GmailEmailService.class);
	
	private final Properties properties = new Properties();

	private Session session;

	private void init() {

		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port",25);
		properties.put("mail.smtp.mail.sender","juan.castano@ceiba.com.co");
		properties.put("mail.smtp.user", "juan.castano@ceiba.com.co");
		properties.put("mail.smtp.password", "XXXX");
		properties.put("mail.smtp.auth", "true");

		session = Session.getDefaultInstance(properties);
	}

	@Override
	public void sendEmail(String text){

		init();
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("juan.botero@ceiba.com.co"));
			message.setSubject("Prueba");
			message.setText(text);
			Transport t = session.getTransport("smtp");
			t.connect((String)properties.get("mail.smtp.user"), (String) properties.get("mail.smtp.password"));
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		}catch (MessagingException e){
			LOGGER.error(e.getMessage(), e);
            throw new EmailSenderException(e.getMessage());
		}
		
	}

}
