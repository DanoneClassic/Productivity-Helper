package aaade.UserService.dto;

import aaade.UserService.model.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * UserDTO is a data transfer object class that represents the user data.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserDTO {
    private String id;
    private String username;
    private String email;
    private Role role;
}
