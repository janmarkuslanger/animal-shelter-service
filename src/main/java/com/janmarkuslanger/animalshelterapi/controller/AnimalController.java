package com.janmarkuslanger.animalshelterapi.controller;

import com.janmarkuslanger.animalshelterapi.model.Animal;
import com.janmarkuslanger.animalshelterapi.service.AnimalService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/animal", produces = {"application/json"})
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
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
        return animalService.createAnimal(newAnimal);
    }

    @PutMapping("/{id}")
    public Animal update(@PathVariable Long id, @RequestBody Animal animal) {
        Animal updatedAnimal = animalService.getAnimal(id);
        return animalService.updateAnimal(updatedAnimal);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        animalService.deleteAnimal(id);
    }
}