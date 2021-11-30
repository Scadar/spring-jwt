package com.example.springjwt.auth.model.payload;

import com.example.springjwt.auth.model.Role;
import com.example.springjwt.auth.model.User;
import lombok.Getter;

import java.util.Set;

@Getter
public class CurrentUserResponse {

    private final Long id;
    private final String email;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final Boolean active;
    private final Set<Role> roles;
    private final Boolean isEmailVerified;

    public CurrentUserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.active = user.getActive();
        this.roles = user.getRoles();
        this.isEmailVerified = user.getIsEmailVerified();
    }
}
