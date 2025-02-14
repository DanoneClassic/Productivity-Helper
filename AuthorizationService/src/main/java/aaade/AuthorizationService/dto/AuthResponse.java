package aaade.AuthorizationService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * AuthResponse is a data transfer object class that represents the response of the authentication process.
 */
@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private UserDTO user;
}
