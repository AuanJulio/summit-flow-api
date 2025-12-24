package com.summitflow.service;

import com.summitflow.entity.User;
import com.summitflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User save(User user){
        String passord = user.getPassword();
        user.setPassword(passwordEncoder.encode(passord));
        return userRepository.save(user);
    }

}
