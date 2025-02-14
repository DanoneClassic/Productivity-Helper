package aaade.UserService.request;


import aaade.UserService.model.Address;
import lombok.Data;


@Data
public class UserUpdateRequest {
    private String username;
    private String password;
    private String email;
    private Address address;
}
