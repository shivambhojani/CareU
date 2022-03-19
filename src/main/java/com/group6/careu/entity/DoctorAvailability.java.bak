package com.group6.careu.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@NoArgsConstructor
@Table(name = "doctor_availability")
@Getter
@Setter
public class DoctorAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "available_date", nullable = false)
    private String availableDate;

    @Column(name = "start_time", nullable = false)
    private String startTime;

    @Column(name = "end_time", nullable = false)
    private String endTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", updatable = false)
    private Doctor doctor;

    public DoctorAvailability(Integer id, String availableDate, String startTime, String endTime, Doctor doctor) {
        this.id = id;
        this.availableDate = availableDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.doctor = doctor;
    }
}
