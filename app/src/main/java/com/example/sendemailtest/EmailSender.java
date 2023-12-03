package com.example.sendemailtest;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    private static final String SENDER_EMAIL = "otpjkost@gmail.com"; //change ur email
    private static final String SENDER_PASSWORD = "gordoreypusvvpbs"; //change ur password
    private static final String EMAIL_SUBJECT = "Verification Code";

    private static Properties properties;

    public EmailSender() {
        properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
    }

    public static void sendEmail(String recipientEmail, String otp) {
        EmailSender emailSender = new EmailSender();
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);

        try {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            mimeMessage.setSubject(EMAIL_SUBJECT);

            // Masukkan OTP ke dalam pesan email dengan format HTML
            String emailMessageWithOTP ="Your verificatioin code : "+otp;

            mimeMessage.setContent(emailMessageWithOTP, "text/html");

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
