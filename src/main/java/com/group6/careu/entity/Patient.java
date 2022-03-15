package com.group6.careu.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Bijitashya on 03, 2022
 */

@Setter
@Getter
@ToString
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patient_id;

    String disease;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
//    private Set<Appointment> appointments;
}
