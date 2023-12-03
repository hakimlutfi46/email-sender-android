package com.example.sendemailtest;

import java.util.Random;

public class OTPGenerator {
    public String generateOTP(int length) {
        // Daftar karakter yang digunakan untuk OTP
        String numbers = "0123456789";

        // Buat objek Random
        Random random = new Random();

        // StringBuilder untuk menyimpan OTP
        StringBuilder otp = new StringBuilder();

        // Generate OTP secara acak dengan panjang yang diinginkan
        for (int i = 0; i < length; i++) {
            otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return otp.toString();
    }
}
