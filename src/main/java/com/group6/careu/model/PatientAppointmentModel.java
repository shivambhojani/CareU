package com.group6.careu.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientAppointmentModel {
    private String doctorName;
    private Integer patient_id;
    private Date date;
    private Time start_time;
    private Time end_time;
    private String consultationType;
    private String medications;


}
