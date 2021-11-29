package com.example.springjwt.auth.model.token;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    @Getter
    @Setter
    private String token;

    public JwtAuthenticationToken(Object principal, Object credentials, String token) {
        super(null, null);
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
