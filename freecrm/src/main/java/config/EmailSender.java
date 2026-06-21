package config;

import java.io.File;
import java.util.Properties;

import org.testng.annotations.Test;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;



public class EmailSender {
	String senderEmail ="samarthnpatel20@gmail.com";
	String appPassword = "hgiftudootnaflby";
	 String recieverEmail = "samarthnpatel20@gmail.com";

	public void sendemail() throws Exception, Exception
	{

	Properties props = new Properties();
	props.put("mail.smtp.host", "smtp.gmail.com");         // SMTP server host address
	props.put("mail.smtp.port", "587");                    // TLS port (or 465 for SSL)
	props.put("mail.smtp.auth", "true");                   // Enables account authentication
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.ssl.trust", "*");
	
	Session session = Session.getInstance(props, new Authenticator() {
	    protected PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication(senderEmail, appPassword); //
	    }
	});
	try
	{
	session.setDebug(true);
    Message message= new MimeMessage(session);
    message.setFrom(new InternetAddress(senderEmail));
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recieverEmail));
    message.setSubject("Testng Report");
 //   message.setText("Hello\n Please find attached Report\n Mosam");
    //Email Bodypart
    
    MimeBodyPart textPart = new MimeBodyPart();
    textPart.setText("Hello\n Please find attached Report\n Mosam");
    
    //set attachment
    
    MimeBodyPart attachmentPart = new MimeBodyPart();
    String filePath = System.getProperty("user.dir")  + File.separator + "ExtentReport.html"; 
   // String filePath = "C:\\Users\\mosam\\eclipse-workspace\\freecrm\\target\\ExtentReport.html";
    System.out.println("File is attached");
    attachmentPart.attachFile(new File(filePath));
    
    //combine body and attachment part
    
    MimeMultipart multipart = new MimeMultipart();
    multipart.addBodyPart(textPart);
    multipart.addBodyPart(attachmentPart);
    message.setContent(multipart);
    
    //send email
    Transport.send(message);
    System.out.println("Email send successfull");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	}

