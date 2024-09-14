package com.janmarkuslanger.animalshelterservice.controller;

import com.janmarkuslanger.animalshelterservice.model.Animal;
import com.janmarkuslanger.animalshelterservice.service.AnimalService;
import com.janmarkuslanger.animalshelterservice.service.VercelService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/animal", produces = {"application/json"})
public class AnimalController {
    private final AnimalService animalService;
    private final VercelService vercelService;

    public AnimalController(AnimalService animalService, VercelService vercelService) {
        this.animalService = animalService;
        this.vercelService = vercelService;
    }

    @GetMapping("/")
    public Iterable<Animal> list() {
        return animalService.list();
    }

    @GetMapping("/{id}")
    public Animal get(@PathVariable Long id) {
        return animalService.get(id);
    }

    @PostMapping("/")
    public Animal create(@RequestBody Animal animal) {
        Animal newAnimal = new Animal();
        animalService.create(newAnimal);
        vercelService.triggerDeployment();
        return newAnimal;
    }

    @PutMapping("/{id}")
    public Animal update(@PathVariable Long id, @RequestBody Animal animal) {
        Animal updatedAnimal = animalService.get(id);
        animalService.update(updatedAnimal);
        vercelService.triggerDeployment();
        return updatedAnimal;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        animalService.delete(id);
        vercelService.triggerDeployment();
    }
}