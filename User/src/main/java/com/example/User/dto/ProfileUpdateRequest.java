package com.example.User.dto;

public class ProfileUpdateRequest {

    private String position;
    private String numberPhone;

    public ProfileUpdateRequest(String position, String numberPhone) {
        this.position = position;
        this.numberPhone = numberPhone;
    }

    public ProfileUpdateRequest() {
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

}
