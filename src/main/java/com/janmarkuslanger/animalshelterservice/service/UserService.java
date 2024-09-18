package com.janmarkuslanger.animalshelterservice.service;

import com.janmarkuslanger.animalshelterservice.model.User;
import com.janmarkuslanger.animalshelterservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> list() {
        return userRepository.findAll();
    }

    public User create(User user) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(User user, User newUser) {
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setEmail(newUser.getEmail());
        user.setRole(newUser.getRole());
        return userRepository.save(user);
    }

    public User get(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}