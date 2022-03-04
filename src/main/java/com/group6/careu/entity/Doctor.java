package com.group6.careu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
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

    private Integer license_number;
    private String specialization;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
    private Set<Appointment> appointments;
}
