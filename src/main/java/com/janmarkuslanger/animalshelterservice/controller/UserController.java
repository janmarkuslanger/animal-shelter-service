package com.janmarkuslanger.animalshelterservice.controller;

import com.janmarkuslanger.animalshelterservice.model.User;
import com.janmarkuslanger.animalshelterservice.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user", produces = {"application/json"})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Iterable<User> list() {
        return userService.list();
    }

    @GetMapping(value = "/{id}")
    public User get(Long id) {
        return userService.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
