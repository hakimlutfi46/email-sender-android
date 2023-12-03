package com.example.sendemailtest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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

    EditText etEmail, etOtp;
    Button btnSend, btnOtpConfirm;
    String generatedOTP;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.tvEmail);
        btnSend = findViewById(R.id.confirmBtn);
        etOtp = findViewById(R.id.tvOtp);
        btnOtpConfirm = findViewById(R.id.confirmOtp);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = Objects.requireNonNull(etEmail.getText()).toString();

                if (email.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Harap isi field!", Toast.LENGTH_SHORT).show();
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(MainActivity.this, "Harap masukkan email yang valid", Toast.LENGTH_SHORT).show();
                } else {
                    sendOTP(email);
                }

            }
        });

        btnOtpConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String otp = Objects.requireNonNull(etOtp.getText()).toString();

                if (otp.isEmpty()){
                    Toast.makeText(MainActivity.this, "Harap isi field!", Toast.LENGTH_SHORT).show();
                }else {
                    if(otp.equals(generatedOTP)){
                        Toast.makeText(MainActivity.this, "Kode OTP valid", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Kode OTP tidak valid", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    private void sendOTP(String email){
        OTPGenerator otpGenerator = new OTPGenerator();
        generatedOTP = otpGenerator.generateOTP(5);
        EmailSender.sendEmail(email, generatedOTP);
        Toast.makeText(this, "OTP berhasil dikirim ke email", Toast.LENGTH_SHORT).show();
    }
}