package aaade.UserService.service;

import aaade.UserService.dto.UserDTO;
import aaade.UserService.model.Address;
import aaade.UserService.model.User;
import aaade.UserService.request.RegisterRequest;
import aaade.UserService.request.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService interface provides methods for managing users.
 */
@Service
public interface UserService {

    /**
     * Retrieves all users.
     *
     * @return a list of all users
     */
    List<User> getAll();

    /**
     * Retrieves a user by ID.
     *
     * @param id the ID of the user
     * @return the user with the given ID
     */
    User getUserById(Long id);

    /**
     * Saves a new user.
     *
     * @param user the user to save
     * @return the saved user
     */
    User saveUser(RegisterRequest user);

    /**
     * Retrieves a user by email.
     *
     * @param email the email of the user
     * @return the user with the given email
     */
    User getUserByEmail(String email);

    /**
     * Retrieves a user by username.
     *
     * @param username the username of the user
     * @return the user with the given username
     */
    User getUserByUsername(String username);

    /**
     * Updates a user by ID.
     *
     * @param request the request containing the updated user details
     * @return the updated user
     */
    Object updateUserById(UserUpdateRequest request);

    /**
     * Deletes a user by ID.
     *
     * @param id the ID of the user to delete
     * @return the deleted user
     */
    User deleteUserById(Long id);

    /**
     * Validates a user's email and password.
     *
     * @param email    the email of the user
     * @param password the password of the user
     * @return the validated user
     */
    UserDTO checkUserPasswordandEmail(String email, String password);

    /**
     * Retrieves the address of a user by username.
     *
     * @param username the username of the user
     * @return the address of the user with the given username
     */
    Address getUserAddressByUsername(String username);

    /**
     * Changes a user's password.
     *
     * @param email    the email of the user
     * @param password the new password of the user
     */
    void changePassword(String email, String password);
}