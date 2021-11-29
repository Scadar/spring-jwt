
package com.example.springjwt.auth.service;

import com.example.springjwt.auth.annotation.CurrentUser;
import com.example.springjwt.auth.exception.UserLogoutException;
import com.example.springjwt.auth.exception.UserRegistrationException;
import com.example.springjwt.auth.model.*;
import com.example.springjwt.auth.model.payload.LogOutRequest;
import com.example.springjwt.auth.model.payload.RegistrationRequest;
import com.example.springjwt.auth.repository.UserRepository;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class);
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserDeviceService userDeviceService;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleService roleService, UserDeviceService userDeviceService, RefreshTokenService refreshTokenService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.userDeviceService = userDeviceService;
        this.refreshTokenService = refreshTokenService;
    }

    /**
     * Finds a user in the database by username
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Finds a user in the database by email
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Find a user in db by id.
     */
    public Optional<User> findById(Long Id) {
        return userRepository.findById(Id);
    }

    /**
     * Save the user to the database
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Check is the user exists given the email: naturalId
     */
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Check is the user exists given the username: naturalId
     */
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }


    /**
     * Creates a new user from the registration request
     */
    public User createUser(RegistrationRequest registerRequest) {
        User newUser = new User();
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setUsername(registerRequest.getUsername());
        try {
            newUser.addRole(getRolesForNewUser());
        } catch (NotFoundException e) {
            throw new UserRegistrationException(newUser.getEmail(), "Role not found");
        }
        newUser.setActive(true);
        newUser.setIsEmailVerified(false);
        return newUser;
    }

    /**
     * Performs a quick check to see what roles the new user could be assigned to.
     *
     * @return default role
     */
    private Role getRolesForNewUser() throws NotFoundException {
        return roleService.findByRoleName(RoleName.ROLE_USER);
    }

    /**
     * Log the given user out and delete the refresh token associated with it. If no device
     * id is found matching the database for the given user, throw a log out exception.
     */
    public void logoutUser(@CurrentUser CustomUserDetails currentUser, LogOutRequest logOutRequest) {
        String deviceId = logOutRequest.getDeviceInfo().getDeviceId();
        UserDevice userDevice = userDeviceService.findByUserId(currentUser.getId())
                .filter(device -> device.getDeviceId().equals(deviceId))
                .orElseThrow(() -> new UserLogoutException(logOutRequest.getDeviceInfo().getDeviceId(), "Invalid device Id supplied. No matching device found for the given user "));

        logger.info("Removing refresh token associated with device [" + userDevice + "]");
        refreshTokenService.deleteById(userDevice.getRefreshToken().getId());
    }
}
