package com.group6.careu.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

class AppointmentTest {
    @Test
    void testConstructor() {
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
        Appointment actualAppointment = new Appointment("Medications", doctor, patient, "Consulation Type",
                mock(Time.class), mock(Time.class), mock(Date.class));

        assertNull(actualAppointment.getAppointmentId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1969-12-31", simpleDateFormat.format(actualAppointment.getStartTime()));
        assertNull(actualAppointment.getPatientFeedback());
        Patient patient1 = actualAppointment.getPatient();
        assertSame(patient, patient1);
        assertEquals("Medications", actualAppointment.getMedications());
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1970-01-01", simpleDateFormat1.format(actualAppointment.getAppointment_date()));
        Doctor doctor1 = actualAppointment.getDoctor();
        assertSame(doctor, doctor1);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1970-01-01", simpleDateFormat2.format(actualAppointment.getEndTime()));
        assertEquals("Consulation Type", actualAppointment.getConsulationType());
        assertEquals("Doctor Spel", doctor1.getDoctorSpel());
        assertEquals("42", doctor1.getDoctorRegNumber());
        assertEquals("Doctor Qualification", doctor1.getDoctorQualification());
        assertEquals("Doctor Overview", doctor1.getDoctorOverview());
        assertEquals("Doctor Location", doctor1.getDoctorLocation());
        assertEquals("Doctor Experience", doctor1.getDoctorExperience());
        assertEquals(1, doctor1.getDoctorAge().intValue());
        assertEquals(1, patient1.getPatient_id().intValue());
        assertEquals(1, doctor1.getDoctor_id().intValue());
        assertEquals("Disease", patient1.getDisease());
        assertSame(doctor1, doctor);
        assertSame(patient1, patient);
    }
}

