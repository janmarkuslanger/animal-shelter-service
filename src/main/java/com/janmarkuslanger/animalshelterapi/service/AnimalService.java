package com.janmarkuslanger.animalshelterapi.service;

import com.janmarkuslanger.animalshelterapi.model.Animal;
import com.janmarkuslanger.animalshelterapi.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public Iterable<Animal> listAnimals() {
        return animalRepository.findAll();
    }

    public Animal getAnimal(Long id) {
        return animalRepository.findById(id).orElse(null);
    }

    public Animal createAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal updateAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }
}
