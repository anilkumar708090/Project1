package com.apiexample.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;


public class RegistrationDto {

    private long id;
    @Size(min = 2, max = 20, message = "Should be more than 2 charecters")
    private String name;

    @Email(message = "invalid email format")
    private String email;

    @Size(min = 10, max = 12,message = "Should be 10 or more")
    private String mobile;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


}