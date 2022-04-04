package com.group6.careu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientSettingsModel {

    private String firstName;
    private String lastName;
    private String phone;
    private String gender;
    private String email;
    private String disease;


}
