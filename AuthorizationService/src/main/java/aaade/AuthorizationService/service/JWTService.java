package aaade.AuthorizationService.service;

import aaade.AuthorizationService.dto.Role;

/**
 * JWTService interface provides methods for generating and validating JWT tokens.
 */
public interface JWTService {

    /**
     * Validates a JWT token.
     *
     * @param token the JWT token to validate
     */
    void validateToken(final String token);

    /**
     * Generates a JWT token.
     *
     * @param id   the user ID
     * @param role the user role
     * @return the generated JWT token
     */
    String generateToken(String id, Role role);
}
