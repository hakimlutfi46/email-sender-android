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

    private static final String SENDER_EMAIL = "otpjkost@gmail.com";  // sesuaikan dengan email anda
    private static final String SENDER_PASSWORD = "gordoreypusvvpbs"; // sesuaikan dengan password abda
    private static final String EMAIL_SUBJECT = "Kode Verifikasi J-Kost";

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
            String emailMessageWithOTP = "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "    <style>" +
                    "        .verification-box {" +
                    "            display: inline-block;" +
                    "            padding: 5px 10px;" +
                    "            background-color: #3B46F1;" +
                    "            color: white;" +
                    "            border-radius: 5px;" +
                    "            transition: background-color 0.5s ease-in-out;" +
                    "        }" +
                    "        .verification-code {" +
                    "            font-size: 1.2em;" +
                    "            font-weight: bold;" +
                    "        }" +
                    "        .verification-box:hover {" +
                    "            background-color: #32BA7C;" +
                    "        }" +
                    "    </style>" +
                    "</head>" +
                    "<body>" +
                    "    <p>Kepada (Nama penerima),</p>"+
                    "    <p>Untuk menyelesaikan proses reset password, kami memerlukan verifikasi email Anda dengan memasukkan kode verifikasi berikut:</p>" +
                    "    <h3>Kode Verifikasi Anda : <span class=\"verification-box\"><span class=\"verification-code\">" + otp + "</span></span></h3>" +
                    "    <p>Kami mohon untuk tidak memberikan kode verifikasi ini pada orang lain untuk menjaga keamanan akun Anda. Jangan ragu untuk menghubungi kami jika Anda mengalami kesulitan atau memiliki pertanyaan lebih lanjut.</p>" +
                    "    <p>Terima kasih atas perhatiannya.</p>" +
                    "    <p>Hormat Kami, J-Kost</p>" +
                    "</body>" +
                    "</html>";

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
