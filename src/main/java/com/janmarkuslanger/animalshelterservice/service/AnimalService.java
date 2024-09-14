package com.janmarkuslanger.animalshelterservice.service;

import com.janmarkuslanger.animalshelterservice.model.Animal;
import com.janmarkuslanger.animalshelterservice.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public Iterable<Animal> list() {
        return animalRepository.findAll();
    }

    public Animal get(Long id) {
        return animalRepository.findById(id).orElse(null);
    }

    public Animal create(Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal update(Animal animal) {
        return animalRepository.save(animal);
    }

    public void delete(Long id) {
        animalRepository.deleteById(id);
    }
}
