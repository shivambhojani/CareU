package com.group6.careu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorAppointmentModel {
    private Integer patient_id;
    private String patientName;
    private Date date;
    private Time start_time;
    private Time end_time;
    private String consultationType;
    private String medications;
    private UUID appointment_id;
}
