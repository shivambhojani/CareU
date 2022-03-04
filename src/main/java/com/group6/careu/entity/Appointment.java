package com.group6.careu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Bijitashya on 03, 2022
 */

@Data
@Entity
@ToString
@Table(name = "appointments")
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;

    @Lob
    private String medications;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Patient patient;
}
