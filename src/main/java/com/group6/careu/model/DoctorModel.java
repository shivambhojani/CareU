package com.group6.careu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorModel {
    private String doctorFName;
    private String doctorlName;
    private String doctorSpel;
    private String doctorLicense;
    private Integer doctorAge;
    private String doctorContact;
    private String doctorOverview;
    private int doctorRegNumber;
    private String doctorExperience;
    private String doctorQualification;
    private String doctorLocation;
    private byte[] fileContent;
}
