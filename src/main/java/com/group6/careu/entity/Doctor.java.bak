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

    @Column(name = "license_number", length = 20)
    private String license_number;

    @Column(name = "age", nullable = true)
    private Integer age;

    @Column(name = "specialization", length = 20)
    private String specialization;

    @Column(name = "doctor_contact")
    private Integer doctorContact;

}
