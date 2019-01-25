package com.igi.kinoteka_api.service.impl;

import com.igi.kinoteka_api.repository.RoleRepository;
import com.igi.kinoteka_api.model.User;
import com.igi.kinoteka_api.model.UserDto;
import com.igi.kinoteka_api.repository.UserRepository;
import com.igi.kinoteka_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Not found!"));
    }

    @Override
    @Transactional
    public void addUser(UserDto user) {
        Optional<User> dbUser = userRepository.findByUsername(user.getUsername());
        if(!dbUser.isPresent()) {
            userRepository.save(
                    User.builder()
                    .username(user.getUsername())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .role( roleRepository.findByName("ROLE_USER"))
                    .build());
        } else{
            throw new IllegalArgumentException("User already exists!");
        }
    }
}
