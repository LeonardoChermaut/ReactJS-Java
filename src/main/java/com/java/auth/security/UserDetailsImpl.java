package com.java.auth.security;

import com.java.auth.model.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private final Optional<UserModel> usuario;

    public UserDetailsImpl(Optional<UserModel> usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return usuario.orElse(new UserModel()).getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.orElse(new UserModel()).getEmail();
    }

    public String getName() {
        return usuario.orElse(new UserModel()).getNome();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}