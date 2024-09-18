package com.janmarkuslanger.animalshelterservice.service;

import com.janmarkuslanger.animalshelterservice.model.User;
import com.janmarkuslanger.animalshelterservice.repository.UserRepository;
import com.janmarkuslanger.animalshelterservice.model.Role;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    public CustomUserDetailsServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsernameReturnsUserDetailsWhenUsernameExists() {
        User user = new User();
        user.setUsername("janedoe");
        user.setPassword("password");
        user.setRole(Role.ADMIN);
        when(userRepository.findByUsername("janedoe")).thenReturn(Optional.of(user));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("janedoe");

        assertThat(userDetails.getUsername()).isEqualTo("janedoe");
        assertThat(userDetails.getPassword()).isEqualTo("password");
        assertThat(userDetails.getAuthorities()).hasSize(1);
        assertThat(userDetails.getAuthorities().iterator().next().getAuthority()).isEqualTo("ADMIN");
    }

    @Test
    void loadUserByUsernameThrowsExceptionWhenUsernameDoesNotExist() {
        when(userRepository.findByUsername("nonexistentuser")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> customUserDetailsService.loadUserByUsername("nonexistentuser"))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessage("User not found");
    }
}