package aaade.AuthorizationService.dto;

import aaade.AuthorizationService.dto.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * AuthUserDto is a data transfer object class that represents the user data for authentication.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AuthUserDto {
    private String id;
    private String email;
    private String username;
    private String password;
    private Role role;
}