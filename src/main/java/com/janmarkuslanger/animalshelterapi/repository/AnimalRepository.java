package com.janmarkuslanger.animalshelterapi.repository;

import com.janmarkuslanger.animalshelterapi.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {}