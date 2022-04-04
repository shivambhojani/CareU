package com.group6.careu.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group6.careu.entity.Appointment;
import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.Patient;
import com.group6.careu.model.AppointmentModel;
import com.group6.careu.repository.AppointmentRepository;
import com.group6.careu.repository.UserRepository;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
/**
 * Created by Bijitashya on 04, 2022
 */
@ContextConfiguration(classes = {AppointmentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AppointmentServiceImplTest {
    @MockBean
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentServiceImpl appointmentServiceImpl;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testGetTodaysPatientAppointments() {
        ArrayList<Appointment> appointmentList = new ArrayList<>();
        when(this.appointmentRepository.getTodaysAppointmentByPatientId((Integer) any(), (Date) any()))
                .thenReturn(appointmentList);
        List<Appointment> actualTodaysPatientAppointments = this.appointmentServiceImpl.getTodaysPatientAppointments(1,
                mock(Date.class));
        assertSame(appointmentList, actualTodaysPatientAppointments);
        assertTrue(actualTodaysPatientAppointments.isEmpty());
        verify(this.appointmentRepository).getTodaysAppointmentByPatientId((Integer) any(), (Date) any());
    }

    @Test
    void testGetPatientFutureAppointments() {
        ArrayList<Appointment> appointmentList = new ArrayList<>();
        when(this.appointmentRepository.getFutureAppointmentByPatientId((Integer) any(), (Date) any()))
                .thenReturn(appointmentList);
        List<Appointment> actualPatientFutureAppointments = this.appointmentServiceImpl.getPatientFutureAppointments(1,
                mock(Date.class));
        assertSame(appointmentList, actualPatientFutureAppointments);
        assertTrue(actualPatientFutureAppointments.isEmpty());
        verify(this.appointmentRepository).getFutureAppointmentByPatientId((Integer) any(), (Date) any());
    }

    @Test
    void testGetPatientPastAppointments() {
        ArrayList<Appointment> appointmentList = new ArrayList<>();
        when(this.appointmentRepository.getPastAppointmentByPatientId((Integer) any(), (Date) any()))
                .thenReturn(appointmentList);
        List<Appointment> actualPatientPastAppointments = this.appointmentServiceImpl.getPatientPastAppointments(1,
                mock(Date.class));
        assertSame(appointmentList, actualPatientPastAppointments);
        assertTrue(actualPatientPastAppointments.isEmpty());
        verify(this.appointmentRepository).getPastAppointmentByPatientId((Integer) any(), (Date) any());
    }

    @Test
    void testGetAppointmentsByAppointmentId() {
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
        when(this.appointmentRepository.getAppointmentsByAppointmentId((UUID) any())).thenReturn(appointment);
        assertSame(appointment, this.appointmentServiceImpl.getAppointmentsByAppointmentId(UUID.randomUUID()));
        verify(this.appointmentRepository).getAppointmentsByAppointmentId((UUID) any());
    }

    @Test
    void testUpdatePatientFeedback() {
        when(this.appointmentRepository.updatePatientFeedback((UUID) any(), (String) any())).thenReturn(1);
        assertEquals(1,
                this.appointmentServiceImpl.updatePatientFeedback(UUID.randomUUID(), "Patient Feedback").intValue());
        verify(this.appointmentRepository).updatePatientFeedback((UUID) any(), (String) any());
    }

    @Test
    void testUpdateMedication() {
        when(this.appointmentRepository.updateMedication((UUID) any(), (String) any())).thenReturn(1);
        assertEquals(1, this.appointmentServiceImpl.updateMedication(UUID.randomUUID(), "Medications").intValue());
        verify(this.appointmentRepository).updateMedication((UUID) any(), (String) any());
    }

    @Test
    void testGetDoctorTodaysAppointments() {
        ArrayList<Appointment> appointmentList = new ArrayList<>();
        when(this.appointmentRepository.getTodaysAppointmentByDoctorId((Integer) any(), (Date) any()))
                .thenReturn(appointmentList);
        List<Appointment> actualDoctorTodaysAppointments = this.appointmentServiceImpl.getDoctorTodaysAppointments(1,
                mock(Date.class));
        assertSame(appointmentList, actualDoctorTodaysAppointments);
        assertTrue(actualDoctorTodaysAppointments.isEmpty());
        verify(this.appointmentRepository).getTodaysAppointmentByDoctorId((Integer) any(), (Date) any());
    }

    @Test
    void testGetDoctorFutureAppointments() {
        ArrayList<Appointment> appointmentList = new ArrayList<>();
        when(this.appointmentRepository.getFutureAppointmentByDoctorId((Integer) any(), (Date) any()))
                .thenReturn(appointmentList);
        List<Appointment> actualDoctorFutureAppointments = this.appointmentServiceImpl.getDoctorFutureAppointments(1,
                mock(Date.class));
        assertSame(appointmentList, actualDoctorFutureAppointments);
        assertTrue(actualDoctorFutureAppointments.isEmpty());
        verify(this.appointmentRepository).getFutureAppointmentByDoctorId((Integer) any(), (Date) any());
    }

    @Test
    void testGetDoctorPastAppointments() {
        ArrayList<Appointment> appointmentList = new ArrayList<>();
        when(this.appointmentRepository.getPastAppointmentByDoctorId((Integer) any(), (Date) any()))
                .thenReturn(appointmentList);
        List<Appointment> actualDoctorPastAppointments = this.appointmentServiceImpl.getDoctorPastAppointments(1,
                mock(Date.class));
        assertSame(appointmentList, actualDoctorPastAppointments);
        assertTrue(actualDoctorPastAppointments.isEmpty());
        verify(this.appointmentRepository).getPastAppointmentByDoctorId((Integer) any(), (Date) any());
    }

    @Test
    void testDeleteAppointmentBasedOnId() {
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
        when(this.appointmentRepository.getAppointmentBasedOnApptId((String) any())).thenReturn(appointment);
        doNothing().when(this.appointmentRepository).delete((Appointment) any());
        assertSame(appointment, this.appointmentServiceImpl.deleteAppointmentBasedOnId("42"));
        verify(this.appointmentRepository).getAppointmentBasedOnApptId((String) any());
        verify(this.appointmentRepository).delete((Appointment) any());
    }
}

