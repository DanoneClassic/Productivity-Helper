package aaade.UserService.controller;

import aaade.UserService.dto.AuthUserDto;
import aaade.UserService.dto.UserDTO;
import aaade.UserService.model.Address;
import aaade.UserService.request.AuthRequest;
import aaade.UserService.request.RegisterRequest;
import aaade.UserService.request.UserUpdateRequest;
import aaade.UserService.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController class provides RESTful API endpoints for managing users.
 */
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    /**
     * Saves a new user.
     *
     * @param request the request containing the user details
     * @return the saved user
     */
    @PostMapping("/save")
    public ResponseEntity<UserDTO> save(@RequestBody RegisterRequest request) {
        log.info("Saving new user with email: {}", request.getEmail());
        return ResponseEntity.ok(modelMapper.map(userService.saveUser(request), UserDTO.class));
    }

    /**
     * Retrieves all users.
     *
     * @return a list of all users
     */
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        log.info("Retrieving all users");
        return ResponseEntity.ok(userService.getAll().stream()
                .map(user -> modelMapper.map(user, UserDTO.class)).toList());
    }

    /**
     * Retrieves a user by its ID.
     *
     * @param id the ID of the user
     * @return the user with the given ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        log.info("Retrieving user with ID: {}", id);
        return ResponseEntity.ok(modelMapper.map(userService.getUserById(id), UserDTO.class));
    }

    /**
     * Retrieves a user by its username.
     *
     * @param username the username of the user
     * @return the user with the given username
     */
    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        log.info("Retrieving user with username: {}", username);
        return ResponseEntity.ok(modelMapper.map(userService.getUserByUsername(username), UserDTO.class));
    }

    /**
     * Retrieves the address of a user by its username.
     *
     * @param username the username of the user
     * @return the address of the user with the given username
     */
    @GetMapping("/getAddressByUsername/{username}")
    public ResponseEntity<Address> getUserAddressByUsername(@PathVariable String username) {
        log.info("Retrieving address for user with username: {}", username);
        Address address = userService.getUserAddressByUsername(username);
        return ResponseEntity.ok(address);
    }

    /**
     * Updates a user by its ID.
     *
     * @param request the request containing the updated user details
     * @return the updated user
     */
    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUserById(@RequestBody UserUpdateRequest request) {
        log.info("Updating user with email: {}", request.getEmail());
        return ResponseEntity.ok(modelMapper.map(userService.updateUserById(request), UserDTO.class));
    }

    /**
     * Deletes a user by its ID.
     *
     * @param id the ID of the user to delete
     */
    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        log.info("Deleting user with ID: {}", id);
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Validates a user's email and password.
     *
     * @param authRequest the request containing the user's email and password
     * @return the validated user
     */
    @PostMapping("/validate")
    public ResponseEntity<UserDTO> checkUserPasswordandEmail(@RequestBody AuthRequest authRequest) {
        log.info("Validating user with email: {}", authRequest.getEmail());
        UserDTO user = userService.checkUserPasswordandEmail(authRequest.getEmail(), authRequest.getPassword());
        return ResponseEntity.ok(user);
    }

    /**
     * Changes a user's password.
     *
     * @param authRequest the request containing the user's email and new password
     */
    @PostMapping("/change-password")
    public ResponseEntity<Void> changeUserPassword(@RequestBody AuthRequest authRequest) {
        log.info("Changing password for user with email: {}", authRequest.getEmail());
        userService.changePassword(authRequest.getEmail(), authRequest.getPassword());
        return ResponseEntity.ok().build();
    }
}