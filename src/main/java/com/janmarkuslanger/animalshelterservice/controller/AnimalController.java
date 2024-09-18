package com.janmarkuslanger.animalshelterservice.controller;

import com.janmarkuslanger.animalshelterservice.model.Animal;
import com.janmarkuslanger.animalshelterservice.service.AnimalService;
import com.janmarkuslanger.animalshelterservice.service.VercelService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/v1/animal", produces = {"application/json"})
public class AnimalController {
    private final AnimalService animalService;
    private final VercelService vercelService;

    public AnimalController(AnimalService animalService, VercelService vercelService) {
        this.animalService = animalService;
        this.vercelService = vercelService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR', 'API')")
    public Iterable<Animal> list() {
        return animalService.list();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR', 'API')")
    public Animal get(@PathVariable Long id) {
        return animalService.get(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public Animal create(@RequestBody Animal animal) {
        Animal newAnimal = new Animal();
        animalService.create(newAnimal);
        vercelService.triggerDeployment();
        return newAnimal;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public Animal update(@PathVariable Long id, @RequestBody Animal animal) {
        Animal updatedAnimal = animalService.get(id);

        if (updatedAnimal == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal not found");
        }

        updatedAnimal.setName(animal.getName());
        updatedAnimal.setAdopted(animal.getAdopted());
        updatedAnimal.setBirthYear(animal.getBirthYear());
        updatedAnimal.setGallery(animal.getGallery());
        updatedAnimal.setAtShelterSince(animal.getAtShelterSince());
        updatedAnimal.setDescription(animal.getDescription());
        updatedAnimal.setGender(animal.getGender());
        updatedAnimal.setSpayed(animal.getSpayed());
        updatedAnimal.setType(animal.getType());

        animalService.update(updatedAnimal);
        vercelService.triggerDeployment();
        return updatedAnimal;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public void delete(@PathVariable Long id) {
        animalService.delete(id);
        vercelService.triggerDeployment();
    }
}