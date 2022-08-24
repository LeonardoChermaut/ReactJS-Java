package com.java.auth.service;

import com.java.auth.model.UserModel;
import com.java.auth.repository.UserRepository;
import com.java.auth.security.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetalheUsuarioServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public DetalheUsuarioServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserModel> usuario = repository.findByEmail(login);
        if (usuario.isPresent()) {
            return new UserDetailsImpl(usuario);
        }
        throw new UsernameNotFoundException("User [" + login + "] não encontrado");
    }
}
