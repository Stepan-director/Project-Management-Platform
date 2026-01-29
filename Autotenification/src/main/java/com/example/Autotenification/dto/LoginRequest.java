package com.example.Autotenification.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {

    @NotBlank(message = "Адрес электронной почты не должеен быть пустым")
    @Email(message = "Email должен быть в формате user@severstal.com")
    private String email;

    @Size(min = 8, max = 40, message = "Длина пароля не должна быть меньше 8 и больше 40")
    @NotBlank(message = "Пароль не может быть пустым")
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
