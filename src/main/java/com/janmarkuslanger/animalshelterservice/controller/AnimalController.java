package com.janmarkuslanger.animalshelterservice.controller;

import com.janmarkuslanger.animalshelterservice.model.Animal;
import com.janmarkuslanger.animalshelterservice.AnimalService;
import com.janmarkuslanger.animalshelterservice.VercelService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/animal", produces = {"application/json"})
public class AnimalController {

    private final AnimalService animalService;
    private final VercelService vercelService;

    public AnimalController(AnimalService animalService, VercelService vercelService) {
        this.animalService = animalService;
        this.vercelService = vercelService;
    }

    @GetMapping("/")
    public Iterable<Animal> list() {
        return animalService.listAnimals();
    }

    @GetMapping("/{id}")
    public Animal get(@PathVariable Long id) {
        return animalService.getAnimal(id);
    }

    @PostMapping("/")
    public Animal create(@RequestBody Animal animal) {
        Animal newAnimal = new Animal();
        animalService.createAnimal(newAnimal);
        vercelService.triggerDeployment();
        return newAnimal;
    }

    @PutMapping("/{id}")
    public Animal update(@PathVariable Long id, @RequestBody Animal animal) {
        Animal updatedAnimal = animalService.getAnimal(id);
        animalService.updateAnimal(updatedAnimal);
        vercelService.triggerDeployment();
        return updatedAnimal;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        animalService.deleteAnimal(id);
        vercelService.triggerDeployment();
    }
}