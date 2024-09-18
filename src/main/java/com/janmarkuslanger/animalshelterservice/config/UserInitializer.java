package com.janmarkuslanger.animalshelterservice.config;

import com.janmarkuslanger.animalshelterservice.model.User;
import com.janmarkuslanger.animalshelterservice.model.Role;
import com.janmarkuslanger.animalshelterservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserInitializer {

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty() && userRepository.findAll().isEmpty()) {
                User user = new User();
                user.setUsername("admin");
                user.setEmail("admin@admin.de");
                user.setPassword(passwordEncoder.encode("admin"));
                user.setRole(Role.ADMIN);
                userRepository.save(user);

                System.out.println("Default admin user created.");
            }
        };
    }
}
