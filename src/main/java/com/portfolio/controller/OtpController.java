package com.portfolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.service.OtpService;
import com.portfolio.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class OtpController {
    private final OtpService otpService;
    private final UserService userService;

    public OtpController(OtpService otpService,
                         UserService userService) {
        this.otpService  = otpService;
        this.userService = userService;
    }

    // 1) Request an OTP be sent to the user’s email
    @PostMapping("/request-otp")
    public ResponseEntity<String> requestOtp(@RequestBody SimpleRequest req) {
        if (!userService.existsByUsername(req.getUsername())) {
            return ResponseEntity.status(404)
                                 .body("User not found");
        }
        String otp = otpService.generateOtpFor(req.getUsername());
        otpService.sendOtpEmail(req.getUsername(), otp);
        return ResponseEntity.ok("OTP sent to your email");
    }

    // 2) Verify the OTP submitted by the user
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpRequest req) {
        if (otpService.validateOtp(req.getUsername(), req.getOtp())) {
            return ResponseEntity.ok("OTP verified, login success");
        }
        return ResponseEntity.status(401)
                             .body("Invalid or expired OTP");
    }

    // -------------------------------
    // DTO classes for request bodies
    // -------------------------------
    public static class SimpleRequest {
        private String username;
        public String getUsername()           { return username; }
        public void   setUsername(String u)   { this.username = u; }
    }

    public static class OtpRequest {
        private String username;
        private String otp;
        public String getUsername()           { return username; }
        public String getOtp()                { return otp; }
        public void   setUsername(String u)   { this.username = u; }
        public void   setOtp(String o)        { this.otp      = o; }
    }
}
