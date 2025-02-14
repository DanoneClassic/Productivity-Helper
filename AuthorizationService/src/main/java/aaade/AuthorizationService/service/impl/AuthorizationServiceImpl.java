package aaade.AuthorizationService.service.impl;

import aaade.AuthorizationService.dto.AuthResponse;
import aaade.AuthorizationService.dto.Role;
import aaade.AuthorizationService.dto.UserDTO;
import aaade.AuthorizationService.request.AuthRequest;
import aaade.AuthorizationService.request.ChangePasswordRequest;
import aaade.AuthorizationService.request.RegisterRequest;
import aaade.AuthorizationService.exception.RegistrationAndAuthFailureException;
import aaade.AuthorizationService.service.AuthorizationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizationServiceImpl implements AuthorizationService {
    private final PasswordEncoder passwordEncoder;
    private final JWTServiceImpl jwtService;
    private final WebClient webClient;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public boolean registerNewUser(@NonNull RegisterRequest registerRequest) {
        log.info("Registering new user with email: {}", registerRequest.getEmail());

        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        webClient.post()
                .uri("http://localhost:8081/api/users/save")
                .bodyValue(registerRequest)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), clientResponse -> {
                    if (clientResponse.statusCode() == HttpStatus.NOT_FOUND) {
                        log.error("User already exists with email: {}", registerRequest.getEmail());
                        throw new RegistrationAndAuthFailureException("User already exists");
                    }
                    log.error("Client error during registration for email: {}", registerRequest.getEmail());
                    throw new RegistrationAndAuthFailureException("Client error");
                })
                .bodyToMono(Void.class)
                .block();

        log.info("Registration successful for email: {}", registerRequest.getEmail());
        kafkaTemplate.send("registration-topic", registerRequest.getEmail());
        return true;
    }

    public void changeUserPassword(ChangePasswordRequest request) {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail(request.getEmail());
        authRequest.setPassword(request.getOldPassword());

        try {
            AuthResponse authResponse = authUser(authRequest);

            if (authResponse != null) {
                String encodedNewPassword = passwordEncoder.encode(request.getNewPassword());
                updatePasswordInUserService(request.getEmail(), encodedNewPassword);
                log.info("Password successfully changed for user: {}", request.getEmail());
            }
        } catch (RegistrationAndAuthFailureException e) {
            throw new IllegalArgumentException("Invalid old password: " + e.getMessage());
        }
    }

    private void updatePasswordInUserService(String email, String encodedNewPassword) {
        webClient.post()
                .uri("http://localhost:8081/api/users/change-password")
                .bodyValue(new AuthRequest(email, encodedNewPassword))
                .retrieve()
                .toBodilessEntity()
                .block();
    }
    @Transactional
    public AuthResponse authUser(@NonNull AuthRequest authRequest) {
        log.info("Authenticating user with email: {}", authRequest.getEmail());

        UserDTO userFullInfoResponse = webClient.post()
                .uri("http://localhost:8081/api/users/validate")
                .bodyValue(authRequest)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), clientResponse -> {
                    if (clientResponse.statusCode() == HttpStatus.NOT_FOUND) {
                        log.error("User not found with email: {}", authRequest.getEmail());
                        throw new RegistrationAndAuthFailureException("User not found");
                    }
                    log.error("Client error during authentication for email: {}", authRequest.getEmail());
                    throw new RegistrationAndAuthFailureException("Client error");
                })
                .bodyToMono(UserDTO.class)
                .block();

        if (Objects.nonNull(userFullInfoResponse)) {
            Role role = userFullInfoResponse.getRole();
            String token = generateToken(userFullInfoResponse.getId(), role);
            log.info("Authentication successful for email: {}", authRequest.getEmail());
            return new AuthResponse(token, userFullInfoResponse);
        } else {
            log.error("Wrong password for email: {}", authRequest.getEmail());
            throw new RegistrationAndAuthFailureException("Wrong password!");
        }
    }

    private String generateToken(String id, Role role) {
        log.info("Generating JWT token for user ID: {}", id);
        return jwtService.generateToken(id, role);
    }
}