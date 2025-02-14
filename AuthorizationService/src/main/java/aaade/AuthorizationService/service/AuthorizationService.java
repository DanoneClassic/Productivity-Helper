package aaade.AuthorizationService.service;

import aaade.AuthorizationService.dto.AuthResponse;
import aaade.AuthorizationService.request.AuthRequest;
import aaade.AuthorizationService.request.ChangePasswordRequest;
import aaade.AuthorizationService.request.RegisterRequest;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

/**
 * AuthorizationService interface provides methods for user authentication.
 */
public interface AuthorizationService {

    /**
     * Registers a new user.
     *
     * @param registerRequest the registration request
     * @return true if the registration was successful
     */
    @Transactional
    boolean registerNewUser(@NonNull RegisterRequest registerRequest);

    /**
     * Changes a user's password.
     *
     * @param request the change password request
     */
    void changeUserPassword(ChangePasswordRequest request);

    /**
     * Authenticates a user.
     *
     * @param authRequest the authentication request
     * @return the authentication response
     */
    @Transactional
    AuthResponse authUser(@NonNull AuthRequest authRequest);
}