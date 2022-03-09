package com.group6.careu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by Bijitashya on 03, 2022
 */

@Data
@Entity
@ToString
@Table(name = "appointment")
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

    private String consulationType;

    //hh:mm:ss
    private Time time;

    //YYYY-MM-DD
    private Date appointment_date;
}
