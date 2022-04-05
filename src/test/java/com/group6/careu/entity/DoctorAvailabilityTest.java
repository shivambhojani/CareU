package com.group6.careu.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

class DoctorAvailabilityTest {
    @Test
    void testConstructor() {
        Date availableDate = mock(Date.class);
        Time startTime = mock(Time.class);
        Time endTime = mock(Time.class);

        Doctor doctor = new Doctor();
        doctor.setDoctorAge(1);
        doctor.setDoctorExperience("Doctor Experience");
        doctor.setDoctorLocation("Doctor Location");
        doctor.setDoctorOverview("Doctor Overview");
        doctor.setDoctorQualification("Doctor Qualification");
        doctor.setDoctorRegNumber("42");
        doctor.setDoctorSpel("Doctor Spel");
        doctor.setDoctor_id(1);
        DoctorAvailability actualDoctorAvailability = new DoctorAvailability(1, availableDate, startTime, endTime, doctor,
                true);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1969-12-31", simpleDateFormat.format(actualDoctorAvailability.getAvailableDate()));
        assertTrue(actualDoctorAvailability.isAppointmentBooked());
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1969-12-31", simpleDateFormat1.format(actualDoctorAvailability.getStartTime()));
        assertEquals(1, actualDoctorAvailability.getId().intValue());
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1969-12-31", simpleDateFormat2.format(actualDoctorAvailability.getEndTime()));
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

