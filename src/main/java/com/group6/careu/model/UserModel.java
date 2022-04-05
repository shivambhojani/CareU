package com.group6.careu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private String firstName;
    private String lastName;
    private int phone;
    private String gender;
    private String email;
    private String password;
    private boolean enabled;
    private String role;
}
