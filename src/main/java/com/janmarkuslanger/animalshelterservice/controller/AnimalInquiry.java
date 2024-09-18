package com.janmarkuslanger.animalshelterservice.controller;

import com.janmarkuslanger.animalshelterservice.model.Animal;
import com.janmarkuslanger.animalshelterservice.service.AnimalService;
import com.janmarkuslanger.animalshelterservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value ="/api/v1/inquiry" )
public class AnimalInquiry {

    @Autowired
    private AnimalService animalService;
    @Autowired
    private EmailService emailService;

    @Value("${animalshelter.inquiry.email}")
    private String toEmail;

    @GetMapping("/send")
    public String sendEmail(@RequestParam Long animalId, @RequestParam String name, @RequestParam String email, @RequestParam String message) {
        Animal animal = animalService.get(animalId);

        if (animal == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Animal not found");
        }

        if (name == null || name.isEmpty() || email == null || email.isEmpty() || message == null || message.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name, email and message are required");
        }

        StringBuilder text = new StringBuilder();
        text
                .append("Tieranfrage für: ")
                .append(animal.getName())
                .append("\n")
                .append("Name: ")
                .append(name)
                .append("\n")
                .append("Email: ")
                .append(email)
                .append("\n")
                .append("Nachricht: ")
                .append(message);

        emailService.sendSimpleEmail(toEmail, "Tieranfrage", text.toString());
        return "Email sent successfully!";
    }

}