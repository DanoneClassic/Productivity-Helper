package aaade.AuthorizationService.controller;

import aaade.AuthorizationService.dto.AuthResponse;
import aaade.AuthorizationService.request.AuthRequest;
import aaade.AuthorizationService.request.ChangePasswordRequest;
import aaade.AuthorizationService.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import aaade.AuthorizationService.service.AuthorizationService;
import aaade.AuthorizationService.service.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * AuthController class provides RESTful API endpoints for user authentication.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthorizationService authorizationService;
    private final JWTService jwtService;

    /**
     * Registers a new user.
     *
     * @param authRequest the registration request
     * @return true if the registration was successful
     */
    @PostMapping("/register")
    public boolean register(@RequestBody RegisterRequest authRequest) {
        log.info("Registering new user with email: {}", authRequest.getEmail());
        authorizationService.registerNewUser(authRequest);
        return true;
    }

    /**
     * Authenticates a user.
     *
     * @param authRequest the authentication request
     * @return the authentication response
     */
    @PostMapping("/token")
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        return authorizationService.authUser(authRequest);
    }

    /**
     * Validates a JWT token.
     *
     * @param token the JWT token to validate
     */
    @PostMapping("/validate")
    public void validateToken(@RequestBody String token) {
        jwtService.validateToken(token);
    }

    /**
     * Changes a user's password.
     *
     * @param request the change password request
     * @return a ResponseEntity with a message indicating the result of the operation
     */
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
        log.info("Request to change password for email: {}", request.getEmail());
        try {
            authorizationService.changeUserPassword(request);
            return ResponseEntity.ok("Password changed successfully.");
        } catch (Exception e) {
            log.error("Error changing password: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}