package com.example.sendemailtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MainActivity extends AppCompatActivity {

    EditText email;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.etEmail);
        btnSend = findViewById(R.id.btnSendEmail);



        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailVal = email.getText().toString();
                try {
                String stringSenderEmail = "otpstayclean@gmail.com";
                String stringReciveEmail = emailVal;
                String stringPasswordSender = "fefybsmxlxeefimy";

                String stringHost = "smtp.gmail.com";

                    Properties properties = System.getProperties();
                    properties.put("mail.smtp.host", stringHost);
                    properties.put("mail.smtp.port", "587");
                    properties.put("mail.smtp.starttls.enable", "true");
                    properties.put("mail.smtp.auth", "true");

                javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(stringSenderEmail, stringPasswordSender);
                    }
                });
                MimeMessage mimeMessage = new MimeMessage(session);


                    mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReciveEmail));

                    mimeMessage.setSubject("Subject: Android App email");
                    mimeMessage.setText("Hello Programmer, \n\n Programer World has sent yout this email \n\n");

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Transport.send(mimeMessage);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Email sent successfully", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (MessagingException e) {
                                e.printStackTrace();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Failed to send email", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });
                    thread.start();

                } catch (AddressException e) {
                    e.printStackTrace();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}