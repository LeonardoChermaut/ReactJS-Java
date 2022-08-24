package com.java.auth.util;

import com.java.auth.model.UserModel;
import com.java.auth.repository.UserRepository;
import com.java.auth.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class UserUtil {

    @Autowired
    private UserRepository repository;

    public UserModel getUsuarioLogado() {
        UserDetailsImpl userDetail = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserModel> usuario = repository.findByEmail(userDetail.getUsername());

        return usuario.orElse(null);
    }

    public UserModel getUsuario() {
        UserDetailsImpl userDetail = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserModel> usuario = repository.findByNome(userDetail.getName());

        return usuario.orElse(null);
    }
}
