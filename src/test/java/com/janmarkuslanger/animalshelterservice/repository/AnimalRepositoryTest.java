package com.janmarkuslanger.animalshelterservice.repository;

import com.janmarkuslanger.animalshelterservice.model.Animal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AnimalRepositoryTests {

    @Autowired
    private AnimalRepository animalRepository;

    @Test
    void findByIdReturnsAnimalWhenIdExists() {
        Animal animal = new Animal();
        animal.setName("Buddy");
        animal = animalRepository.save(animal);

        Optional<Animal> foundAnimal = animalRepository.findById(animal.getId());

        assertThat(foundAnimal).isPresent();
        assertThat(foundAnimal.get().getName()).isEqualTo("Buddy");
    }

    @Test
    void findByIdReturnsEmptyWhenIdDoesNotExist() {
        Optional<Animal> foundAnimal = animalRepository.findById(999L);

        assertThat(foundAnimal).isNotPresent();
    }

    @Test
    void savePersistsAnimal() {
        Animal animal = new Animal();
        animal.setName("Max");

        Animal savedAnimal = animalRepository.save(animal);

        assertThat(savedAnimal.getId()).isNotNull();
        assertThat(savedAnimal.getName()).isEqualTo("Max");
    }

    @Test
    void deleteRemovesAnimal() {
        Animal animal = new Animal();
        animal.setName("Charlie");
        animal = animalRepository.save(animal);

        animalRepository.delete(animal);
        Optional<Animal> foundAnimal = animalRepository.findById(animal.getId());

        assertThat(foundAnimal).isNotPresent();
    }
}