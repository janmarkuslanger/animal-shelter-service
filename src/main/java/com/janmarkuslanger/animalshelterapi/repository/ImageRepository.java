package com.janmarkuslanger.animalshelterapi.repository;

import com.janmarkuslanger.animalshelterapi.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {}