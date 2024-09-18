package com.janmarkuslanger.animalshelterservice.repository;

import com.janmarkuslanger.animalshelterservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUsernameReturnsUserWhenUsernameExists() {
        User user = new User();
        user.setUsername("janedoe");
        user = userRepository.save(user);

        Optional<User> foundUser = userRepository.findByUsername("janedoe");

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("janedoe");
    }

    @Test
    void findByUsernameReturnsEmptyWhenUsernameDoesNotExist() {
        Optional<User> foundUser = userRepository.findByUsername("nonexistentuser");

        assertThat(foundUser).isNotPresent();
    }

    @Test
    void savePersistsUser() {
        User user = new User();
        user.setUsername("johnsmith");

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("johnsmith");
    }

    @Test
    void deleteRemovesUser() {
        User user = new User();
        user.setUsername("michael");
        user = userRepository.save(user);

        userRepository.delete(user);
        Optional<User> foundUser = userRepository.findById(user.getId());

        assertThat(foundUser).isNotPresent();
    }
}