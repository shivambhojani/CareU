package com.group6.careu.service;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group6.careu.entity.Appointment;
import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.Patient;
import com.group6.careu.entity.UserDocument;

import java.io.UnsupportedEncodingException;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;
import javax.mail.MessagingException;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
/**
 * Created by Bijitashya on 04, 2022
 */
@ContextConfiguration(classes = {EmailServiceImpl.class})
@ExtendWith(SpringExtension.class)
class EmailServiceImplTest {
    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @Test
    void testGetContentStringToBookAppointment() {

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
        this.emailServiceImpl.getContentStringToBookAppointment(appointment);
    }

    @Test
    void testGetContentStringToBookAppointment2() {

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
        Appointment appointment = mock(Appointment.class);
        when(appointment.getConsulationType()).thenReturn("Consulation Type");
        when(appointment.getAppointment_date()).thenReturn(mock(Date.class));
        when(appointment.getEndTime()).thenReturn(mock(Time.class));
        when(appointment.getStartTime()).thenReturn(mock(Time.class));
        when(appointment.getAppointmentId()).thenReturn(UUID.randomUUID());
        doNothing().when(appointment).setAppointmentId((UUID) any());
        doNothing().when(appointment).setAppointment_date((Date) any());
        doNothing().when(appointment).setConsulationType((String) any());
        doNothing().when(appointment).setDoctor((Doctor) any());
        doNothing().when(appointment).setEndTime((Time) any());
        doNothing().when(appointment).setMedications((String) any());
        doNothing().when(appointment).setPatient((Patient) any());
        doNothing().when(appointment).setPatientFeedback((String) any());
        doNothing().when(appointment).setStartTime((Time) any());
        appointment.setAppointmentId(UUID.randomUUID());
        appointment.setAppointment_date(mock(Date.class));
        appointment.setConsulationType("Consulation Type");
        appointment.setDoctor(doctor);
        appointment.setEndTime(mock(Time.class));
        appointment.setMedications("Medications");
        appointment.setPatient(patient);
        appointment.setPatientFeedback("Patient Feedback");
        appointment.setStartTime(mock(Time.class));
        this.emailServiceImpl.getContentStringToBookAppointment(appointment);
        verify(appointment).getConsulationType();
        verify(appointment).getAppointment_date();
        verify(appointment).getEndTime();
        verify(appointment).getStartTime();
        verify(appointment).getAppointmentId();
        verify(appointment).setAppointmentId((UUID) any());
        verify(appointment).setAppointment_date((Date) any());
        verify(appointment).setConsulationType((String) any());
        verify(appointment).setDoctor((Doctor) any());
        verify(appointment).setEndTime((Time) any());
        verify(appointment).setMedications((String) any());
        verify(appointment).setPatient((Patient) any());
        verify(appointment).setPatientFeedback((String) any());
        verify(appointment).setStartTime((Time) any());
    }

    @Test
    void testGetContentForCancelAppointment() {

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
        this.emailServiceImpl.getContentForCancelAppointment(appointment);
    }

    @Test
    void testGetContentForCancelAppointment2() {

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
        Appointment appointment = mock(Appointment.class);
        when(appointment.getAppointment_date()).thenReturn(mock(Date.class));
        when(appointment.getAppointmentId()).thenReturn(UUID.randomUUID());
        doNothing().when(appointment).setAppointmentId((UUID) any());
        doNothing().when(appointment).setAppointment_date((Date) any());
        doNothing().when(appointment).setConsulationType((String) any());
        doNothing().when(appointment).setDoctor((Doctor) any());
        doNothing().when(appointment).setEndTime((Time) any());
        doNothing().when(appointment).setMedications((String) any());
        doNothing().when(appointment).setPatient((Patient) any());
        doNothing().when(appointment).setPatientFeedback((String) any());
        doNothing().when(appointment).setStartTime((Time) any());
        appointment.setAppointmentId(UUID.randomUUID());
        appointment.setAppointment_date(mock(Date.class));
        appointment.setConsulationType("Consulation Type");
        appointment.setDoctor(doctor);
        appointment.setEndTime(mock(Time.class));
        appointment.setMedications("Medications");
        appointment.setPatient(patient);
        appointment.setPatientFeedback("Patient Feedback");
        appointment.setStartTime(mock(Time.class));
        this.emailServiceImpl.getContentForCancelAppointment(appointment);
        verify(appointment).getAppointment_date();
        verify(appointment).getAppointmentId();
        verify(appointment).setAppointmentId((UUID) any());
        verify(appointment).setAppointment_date((Date) any());
        verify(appointment).setConsulationType((String) any());
        verify(appointment).setDoctor((Doctor) any());
        verify(appointment).setEndTime((Time) any());
        verify(appointment).setMedications((String) any());
        verify(appointment).setPatient((Patient) any());
        verify(appointment).setPatientFeedback((String) any());
        verify(appointment).setStartTime((Time) any());
    }

}

