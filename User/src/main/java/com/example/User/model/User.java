package com.example.User.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "user_profile")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "personal_number", unique = true, nullable = false)
    private UUID personalNumber;

    @Column(name = "number_phone", unique = true)
    private String numberPhone;
    private String position;

    public User(Long id, String name, String surname, String email, UUID personalNumber, String numberPhone, String position) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.personalNumber = personalNumber;
        this.numberPhone = numberPhone;
        this.position = position;
    }

    public User() {
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

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
