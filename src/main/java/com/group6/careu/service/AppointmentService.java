package com.group6.careu.service;

import com.group6.careu.entity.Appointment;
import com.group6.careu.model.AppointmentModel;

public interface AppointmentService {

    Appointment pushPatientAppointment(AppointmentModel appointment);
}
