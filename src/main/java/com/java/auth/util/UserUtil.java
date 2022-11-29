package com.java.auth.util;

import java.util.Optional;
import com.java.auth.model.UserModel;
import com.java.auth.security.UserDetailsImpl;
import com.java.auth.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

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
        Optional<UserModel> user = userRepository.findByEmail(userDetail.getUsername());

        return user.orElse(null);
    }
}
