package com.portfolio.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OtpService {
    private final UserService userService;
    private final JavaMailSender mailSender;
    private final Map<String, OtpEntry> otpStore = new ConcurrentHashMap<>();

    @Autowired
    public OtpService(UserService userService,
                      JavaMailSender mailSender) {
        this.userService = userService;
        this.mailSender   = mailSender;
    }

    /** Generate a 6‑digit OTP and store it for 5 minutes */
    public String generateOtpFor(String username) {
        String code = String.format("%06d", new Random().nextInt(1_000_000));
        otpStore.put(username,
                     new OtpEntry(code, LocalDateTime.now().plusMinutes(5)));
        return code;
    }

    /** Validate OTP and remove it on success */
    public boolean validateOtp(String username, String otp) {
        OtpEntry entry = otpStore.get(username);
        if (entry != null
            && entry.getExpiresAt().isAfter(LocalDateTime.now())
            && entry.getCode().equals(otp)) {
            otpStore.remove(username);
            return true;
        }
        return false;
    }

    /** Send the OTP via email using Spring’s JavaMailSender */
public void sendOtpEmail(String username, String otp) {
    String to;
    try {
        to = userService.findEmailByUsername(username);
    } catch (Exception e) {
        throw new IllegalStateException("Unable to load user email", e);
    }
    if (to == null || to.isEmpty()) {
        throw new IllegalArgumentException("No email found for user: " + username);
    }

    // 1) Log the OTP so you can see it in console
    System.out.println("🔐 OTP for " + username + " is: " + otp);

    // 2) Attempt real send, but don’t fail the whole flow if SMTP is missing
    try {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("no-reply@port4u.com");
        msg.setTo(to);
        msg.setSubject("Your PORT4U OTP Code");
        msg.setText("Your OTP code is: " + otp + "\nIt will expire in 5 minutes.");
        mailSender.send(msg);
    } catch (Exception ex) {
        // swallow or log, but don’t rethrow:
        System.err.println("⚠️ Warning: SMTP send failed: " + ex.getMessage());
    }
}


    /** Holder for code + expiration timestamp */
    private static class OtpEntry {
        private final String code;
        private final LocalDateTime expiresAt;
        public OtpEntry(String code, LocalDateTime expiresAt) {
            this.code      = code;
            this.expiresAt = expiresAt;
        }
        public String getCode()           { return code; }
        public LocalDateTime getExpiresAt() { return expiresAt; }
    }
}
