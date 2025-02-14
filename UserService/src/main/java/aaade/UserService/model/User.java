package aaade.UserService.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * User class represents a user in the system.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String username;

    @NonNull
    public String email;

    @NonNull
    private String password;

    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;


}
