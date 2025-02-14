package aaade.AuthorizationService.request;


import aaade.AuthorizationService.dto.Role;
import lombok.Data;


@Data
public class UserUpdateRequest {
    private Long id;
    private String username;
    private String password;
    private Role role;
}
