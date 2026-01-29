package com.example.Autotenification.dto;

import java.util.UUID;

public class UserCreatedEvent {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private UUID personalNumber;

    public UserCreatedEvent(Long id, String name, String surname, String email, UUID personalNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.personalNumber = personalNumber;
    }

    public UserCreatedEvent() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(UUID personalNumber) {
        this.personalNumber = personalNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
