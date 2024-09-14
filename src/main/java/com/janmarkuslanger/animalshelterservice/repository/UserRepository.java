package com.janmarkuslanger.animalshelterservice.repository;

import com.janmarkuslanger.animalshelterservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}