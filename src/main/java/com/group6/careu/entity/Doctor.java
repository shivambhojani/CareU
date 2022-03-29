package com.group6.careu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Bijitashya on 03, 2022
 */

@Entity
@Data
@ToString
@Table(name = "doctors")
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Integer doctor_id;

    @Column(name = "doctor_spel", length = 45)
    private String doctorSpel;

    @Column(name = "doctor_license", length = 20)
    private String doctorLicense;

    @Column(name = "doctor_age")
    private Integer doctorAge;

    @Column(name = "doctor_overview", length = 255)
    private String doctorOverview;

    @Column(name = "doctor_reg_number", length = 10)
    private String doctorRegNumber;

    @Column(name = "doctor_experience", length = 255)
    private String doctorExperience;

    @Column(name = "doctor_qualification", length = 255)
    private String doctorQualification;

    @Column(name = "doctor_location", length = 255)
    private String doctorLocation;

    @Lob
    private byte[] fileContent;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "doctor")
    private List<DoctorAvailability> availabilityList;

    /*
    @OneToMany(mappedBy = "doctor_profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DoctorAvailability> availabilityList;
    */

    public Doctor(Integer id, String doctorLicense, Integer doctorAge, String doctorSpel, String doctorOverview
        , String doctorRegNumber, String doctorExperience,String doctorQualification,String doctorLocation,
                  byte[] fileContent) {
        this.doctor_id = id;
        this.doctorLicense = doctorLicense;
        this.doctorAge = doctorAge;
        this.doctorSpel = doctorSpel;
        this.doctorOverview=doctorOverview;
        this.doctorRegNumber=doctorRegNumber;
        this.doctorExperience=doctorExperience;
        this.doctorQualification=doctorQualification;
        this.doctorLocation=doctorLocation;
        this.fileContent=fileContent;
    }

}
