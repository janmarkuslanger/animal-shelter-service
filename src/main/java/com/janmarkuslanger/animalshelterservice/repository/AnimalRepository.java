package com.janmarkuslanger.animalshelterservice.repository;

import com.janmarkuslanger.animalshelterservice.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {}