package com.janmarkuslanger.animalshelterservice.service;

import com.janmarkuslanger.animalshelterservice.model.Role;
import com.janmarkuslanger.animalshelterservice.model.User;
import com.janmarkuslanger.animalshelterservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listReturnsAllUsers() {
        Iterable<User> users = List.of(new User(), new User());
        when(userRepository.findAll()).thenReturn((List<User>) users);

        Iterable<User> result = userService.list();

        assertThat(result).isEqualTo(users);
    }

    @Test
    void createSavesAndReturnsUserWithEncodedPassword() {
        User user = new User();
        user.setPassword("plainPassword");
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(user.getPassword());
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.create(user);

        assertThat(result.getPassword()).isNotEqualTo("plainPassword");
        assertThat(encoder.matches("plainPassword", result.getPassword())).isTrue();
    }

    @Test
    void updateModifiesAndReturnsUser() {
        User user = new User();
        User newUser = new User();
        newUser.setUsername("UpdatedUsername");
        newUser.setPassword("UpdatedPassword");
        newUser.setEmail("UpdatedEmail");
        newUser.setRole(Role.ADMIN);
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.update(user, newUser);

        assertThat(result.getUsername()).isEqualTo("UpdatedUsername");
        assertThat(result.getPassword()).isEqualTo("UpdatedPassword");
        assertThat(result.getEmail()).isEqualTo("UpdatedEmail");
        assertThat(result.getRole()).isEqualTo(Role.ADMIN);
    }

    @Test
    void getReturnsUserWhenIdExists() {
        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.get(1L);

        assertThat(result).isEqualTo(user);
    }

    @Test
    void getReturnsNullWhenIdDoesNotExist() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        User result = userService.get(1L);

        assertThat(result).isNull();
    }

    @Test
    void deleteRemovesUserById() {
        doNothing().when(userRepository).deleteById(1L);

        userService.delete(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }
}