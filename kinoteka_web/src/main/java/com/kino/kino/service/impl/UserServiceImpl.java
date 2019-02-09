package com.kino.kino.service.impl;

import com.kino.kino.model.Role;
import com.kino.kino.model.User;
import com.kino.kino.repository.RoleRepository;
import com.kino.kino.repository.UserRepository;
import com.kino.kino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found!"));
    }

    @Override
    @Transactional
    public void add(User user) {
        Optional<User> dbUser = userRepository.findByUsername(user.getUsername());
        if(dbUser.isPresent()) throw new EntityExistsException("Username taken!");
        Role roleUser = roleRepository.findByName("ROLE_USER");
        user.setRole(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
