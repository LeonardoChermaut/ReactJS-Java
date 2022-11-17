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
    private UserRepository userRepository;

    public UserModel getContext() {
        UserDetailsImpl userDetail = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserModel> user = userRepository.findByEmail(userDetail.getUsername());

        return user.orElse(null);
    }

    public UserModel getUser() {
        UserDetailsImpl userDetail = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserModel> user = userRepository.findByNome(userDetail.getName());

        return user.orElse(null);
    }
}
