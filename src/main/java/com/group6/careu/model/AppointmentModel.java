package com.group6.careu.model;

import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentModel {

    private Integer doctor_id;
    private Patient patient;
    private String consulationType;
    private String time;
    private Date appointment_date;
}
