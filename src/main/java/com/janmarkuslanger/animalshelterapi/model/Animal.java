package com.janmarkuslanger.animalshelterapi.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private AnimalType type;
    private String birthYear;
    private Gender gender;
    private String description;
    private Boolean spayed;
    private String atShelterSince;

    private Boolean adopted = false;

    private String image;

    @ElementCollection
    private List<String> gallery;

    public Animal() {}

    public Animal(Long id, String name, AnimalType type, String birthYear, Gender gender, String description, Boolean spayed, String atShelterSince, Boolean adopted, String image, List<String> gallery) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.birthYear = birthYear;
        this.gender = gender;
        this.description = description;
        this.spayed = spayed;
        this.atShelterSince = atShelterSince;
        this.adopted = adopted;
        this.image = image;
        this.gallery = gallery;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getSpayed() {
        return spayed;
    }

    public void setSpayed(Boolean spayed) {
        this.spayed = spayed;
    }

    public String getAtShelterSince() {
        return atShelterSince;
    }

    public void setAtShelterSince(String atShelterSince) {
        this.atShelterSince = atShelterSince;
    }

    public Boolean getAdopted() {
        return adopted;
    }

    public void setAdopted(Boolean adopted) {
        this.adopted = adopted;
    }
}