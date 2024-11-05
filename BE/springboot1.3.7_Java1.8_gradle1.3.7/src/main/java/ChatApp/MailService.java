package com.example.demo;

import jakarta.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;

import org.springframework.beans.factory.annotation.Value;
import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.Properties;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MailService {
    private Properties properties;

    private javax.mail.Session session;

    @Value("${google.mail.smtp.username}")
    private String username;

    @Value("${google.mail.smtp.password}")
    private String password;

    @Value("${google.mail.smtp.host}")
    private String host;

    @Value("${google.mail.smtp.port}")
    private String port;

    @PostConstruct
    protected void onPostConstruct() {
        this.properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", port);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};

        this.session = javax.mail.Session.getInstance(properties, auth);
    }

    public void testSendMessage() {
        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                javax.mail.Message.RecipientType.TO,
                    InternetAddress.parse(username)
            );
            message.setSubject("Testing Gmail SSL");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");

            javax.mail.Transport.send(message);

            System.out.println("Done");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
