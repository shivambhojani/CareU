package com.group6.careu.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class DoctorAvailabilityTest {
    @Test
    void testConstructor() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Time startTime = Time.valueOf(dtf.format(now));
        LocalDateTime endTime = now.plusHours(5);
        Time endF = Time.valueOf(dtf.format(endTime));
        Doctor doctor = new Doctor();
        doctor.setDoctorAge(1);
        doctor.setDoctorExperience("Doctor Experience");
        doctor.setDoctorLocation("Doctor Location");
        doctor.setDoctorOverview("Doctor Overview");
        doctor.setDoctorQualification("Doctor Qualification");
        doctor.setDoctorRegNumber("42");
        doctor.setDoctorSpel("Doctor Spel");
        doctor.setDoctor_id(1);
        DoctorAvailability actualDoctorAvailability = new DoctorAvailability(1, Date.valueOf("1970-01-01"), startTime, endF, doctor,
                true);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1970-01-01", simpleDateFormat.format(actualDoctorAvailability.getAvailableDate()));
        assertTrue(actualDoctorAvailability.isAppointmentBooked());
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1970-01-01", simpleDateFormat1.format(actualDoctorAvailability.getStartTime()));
        assertEquals(1, actualDoctorAvailability.getId().intValue());
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1970-01-01", simpleDateFormat2.format(actualDoctorAvailability.getEndTime()));
        Doctor doctor1 = actualDoctorAvailability.getDoctor();
        assertSame(doctor, doctor1);
        assertEquals(1, doctor1.getDoctorAge().intValue());
        assertEquals(1, doctor1.getDoctor_id().intValue());
        assertEquals("Doctor Spel", doctor1.getDoctorSpel());
        assertEquals("42", doctor1.getDoctorRegNumber());
        assertEquals("Doctor Qualification", doctor1.getDoctorQualification());
        assertEquals("Doctor Overview", doctor1.getDoctorOverview());
        assertEquals("Doctor Location", doctor1.getDoctorLocation());
        assertEquals("Doctor Experience", doctor1.getDoctorExperience());
        assertSame(doctor1, doctor);
    }
}

