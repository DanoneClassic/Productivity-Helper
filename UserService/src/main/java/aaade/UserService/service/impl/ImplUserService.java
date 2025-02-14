package aaade.UserService.service.impl;

import aaade.UserService.dto.AuthUserDto;
import aaade.UserService.dto.UserDTO;
import aaade.UserService.model.Address;
import aaade.UserService.model.Role;
import aaade.UserService.model.User;
import aaade.UserService.repository.UserRepository;
import aaade.UserService.request.RegisterRequest;
import aaade.UserService.request.UserUpdateRequest;
import aaade.UserService.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ImplUserService implements UserService {
    private final UserRepository repository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public User saveUser(RegisterRequest user) {
        user.setRole(Role.STANDART_USER.name());
        User userEntity = modelMapper.map(user, User.class);
        userEntity.setRole(Role.STANDART_USER);
        return repository.save(userEntity);
    }

    @Override
    public User getUserByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    protected User findUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public Address getUserAddressByUsername(String username) {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return user.getAddress();
    }

    @Override
    public void changePassword(String email, String password) {
        User user = getUserByEmail(email);
        user.setPassword(password);
        repository.save(user);
    }

    @Override
    public User deleteUserById(Long id) {
        User toDelete = findUserById(id);
        repository.delete(toDelete);
        return toDelete;
    }

    @Override
    public UserDTO checkUserPasswordandEmail(String email, String password) {
        User user = getUserByEmail(email);
        log.info("User found with email: {}", user.getEmail());

        if (passwordEncoder.matches(password, user.getPassword())) {
            log.info("Password matches for user: {}", email);
            return modelMapper.map(user, UserDTO.class);
        } else {
            log.error("Invalid password for user: {}", email);
            throw new EntityNotFoundException("User not found or invalid password");
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public Object updateUserById(UserUpdateRequest request) {
        User toUpdate = getUserByUsername(request.getUsername());
        modelMapper.map(request, toUpdate);
        return repository.save(toUpdate);
    }




}
