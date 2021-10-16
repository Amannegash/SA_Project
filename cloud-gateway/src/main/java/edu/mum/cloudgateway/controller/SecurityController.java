package edu.mum.cloudgateway.controller;


import edu.mum.cloudgateway.security.JWTUtil;
import edu.mum.cloudgateway.security.PBKDF2Encoder;
import edu.mum.cloudgateway.security.model.AuthRequest;
import edu.mum.cloudgateway.security.model.AuthResponse;
import edu.mum.cloudgateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class SecurityController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PBKDF2Encoder passwordEncoder;

    @Autowired
    UserService userService;

    @GetMapping("/welcome")
    public String getInitialMessage() {
        return "Welcome to the Hotel Reservation System";
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest ar) {
        return userService.findByUsername(ar.getUsername()).map((userDetails) -> {
            if (passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword())) {
                return ResponseEntity.ok(new AuthResponse (jwtUtil.generateToken(userDetails)));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
