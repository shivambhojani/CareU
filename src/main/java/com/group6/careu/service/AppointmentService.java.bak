package com.group6.careu.service;

import com.group6.careu.entity.Appointment;
import com.group6.careu.model.AppointmentModel;
import com.group6.careu.model.PatientAppointmentModel;

import java.sql.Date;
import java.util.List;

public interface AppointmentService {

    Appointment pushPatientAppointment(AppointmentModel appointment);

    List<Appointment> getTodaysPatientAppointments(Integer id ,Date date);

    List<Appointment> getPatientFutureAppointments(Integer id, Date date);

    List<Appointment> getPatientPastAppointments(Integer id, Date date);
}
