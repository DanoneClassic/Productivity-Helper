package aaade.UserService.dto;

import aaade.UserService.model.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * AuthUserDto is a data transfer object class that represents the user data.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AuthUserDto {
    private String id;
    private String email;
    private Role role;
}