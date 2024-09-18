package com.janmarkuslanger.animalshelterservice.service;

import com.janmarkuslanger.animalshelterservice.model.Animal;
import com.janmarkuslanger.animalshelterservice.repository.AnimalRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AnimalServiceTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalService animalService;

    public AnimalServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listReturnsAllAnimals() {
        Iterable<Animal> animals = List.of(new Animal(), new Animal());
        when(animalRepository.findAll()).thenReturn((List<Animal>) animals);

        Iterable<Animal> result = animalService.list();

        assertThat(result).isEqualTo(animals);
    }

    @Test
    void getReturnsAnimalWhenIdExists() {
        Animal animal = new Animal();
        when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));

        Animal result = animalService.get(1L);

        assertThat(result).isEqualTo(animal);
    }

    @Test
    void getReturnsNullWhenIdDoesNotExist() {
        when(animalRepository.findById(1L)).thenReturn(Optional.empty());

        Animal result = animalService.get(1L);

        assertThat(result).isNull();
    }

    @Test
    void createSavesAndReturnsAnimal() {
        Animal animal = new Animal();
        when(animalRepository.save(animal)).thenReturn(animal);

        Animal result = animalService.create(animal);

        assertThat(result).isEqualTo(animal);
    }

    @Test
    void updateModifiesAndReturnsAnimal() {
        Animal animal = new Animal();
        Animal newAnimal = new Animal();
        newAnimal.setName("Updated Name");
        when(animalRepository.save(animal)).thenReturn(animal);

        Animal result = animalService.update(animal, newAnimal);

        assertThat(result.getName()).isEqualTo("Updated Name");
    }

    @Test
    void deleteRemovesAnimalById() {
        doNothing().when(animalRepository).deleteById(1L);

        animalService.delete(1L);

        verify(animalRepository, times(1)).deleteById(1L);
    }
}