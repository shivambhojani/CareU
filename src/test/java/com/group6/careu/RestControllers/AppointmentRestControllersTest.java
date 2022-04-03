package com.group6.careu.RestControllers;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group6.careu.entity.Appointment;
import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.Patient;
import com.group6.careu.service.AppointmentServiceImpl;

import java.sql.Date;
import java.sql.Time;

import java.util.UUID;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;

class AppointmentRestControllersTest {

    @Test
    void testGetPatientFeedback() {
        Doctor doctor = new Doctor();
        doctor.setDoctorAge(1);
        doctor.setDoctorExperience("Doctor Experience");
        doctor.setDoctorLocation("Doctor Location");
        doctor.setDoctorOverview("Doctor Overview");
        doctor.setDoctorQualification("Doctor Qualification");
        doctor.setDoctorRegNumber("42");
        doctor.setDoctorSpel("Doctor Spel");
        doctor.setDoctor_id(1);

        Patient patient = new Patient();
        patient.setDisease("Disease");
        patient.setPatient_id(1);

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(UUID.randomUUID());
        appointment.setAppointment_date(mock(Date.class));
        appointment.setConsulationType("Consulation Type");
        appointment.setDoctor(doctor);
        appointment.setEndTime(mock(Time.class));
        appointment.setMedications("Medications");
        appointment.setPatient(patient);
        appointment.setPatientFeedback("Patient Feedback");
        appointment.setStartTime(mock(Time.class));
        AppointmentServiceImpl appointmentServiceImpl = mock(AppointmentServiceImpl.class);
        when(appointmentServiceImpl.getAppointmentsByAppointmentId((UUID) any())).thenReturn(appointment);
        AppointmentRestControllers appointmentRestControllers = new AppointmentRestControllers(appointmentServiceImpl);
        UUID appointment_id = UUID.randomUUID();
        assertSame(appointment, appointmentRestControllers.getPatientFeedback(appointment_id, new ConcurrentModel()));
        verify(appointmentServiceImpl).getAppointmentsByAppointmentId((UUID) any());
    }

}

