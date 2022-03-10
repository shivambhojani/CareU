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
    Integer doctor_id;

    @Column(name = "license_number", length = 20, nullable = false)
    private String license_number;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "specialization", length = 20, nullable = false)
    private String specialization;

    @Column(name = "doctor_contact", nullable = false)
    private Integer doctorContact;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
    private Set<Appointment> appointments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor")
//    @JoinTable(name = "doctor_availability" ,joinColumns = @JoinColumn(name = "doctor_id"))
    private List<DoctorAvailability> availabilityList;
}
