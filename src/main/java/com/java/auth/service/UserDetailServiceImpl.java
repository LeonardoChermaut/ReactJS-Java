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
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserModel> user = repository.findByEmail(login);
        if (user.isPresent()) {
            return new UserDetailsImpl(user);
        }
        throw new UsernameNotFoundException("Usuário [" + login + "] não encontrado");
    }
}
