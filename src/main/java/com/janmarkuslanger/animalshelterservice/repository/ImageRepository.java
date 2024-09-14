package com.janmarkuslanger.animalshelterservice.repository;

import com.janmarkuslanger.animalshelterservice.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {}