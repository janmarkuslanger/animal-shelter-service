package com.janmarkuslanger.animalshelterapi.repository;

import com.janmarkuslanger.animalshelterapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}