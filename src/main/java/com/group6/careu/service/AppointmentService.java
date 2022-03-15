package com.group6.careu.service;

import com.group6.careu.entity.Appointment;
import com.group6.careu.model.AppointmentModel;
import com.group6.careu.model.PatientAppointmentModel;

import java.util.List;

public interface AppointmentService {

    Appointment pushPatientAppointment(AppointmentModel appointment);

    List<Appointment> getPatientAppointments(Integer id);
}
