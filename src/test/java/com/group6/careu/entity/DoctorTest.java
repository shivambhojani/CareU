package com.group6.careu.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DoctorTest {
    @Test
    void testConstructor() {
        Doctor actualDoctor = new Doctor(1, 1, "Doctor Spel", "Doctor Overview", "42", "Doctor Experience",
                "Doctor Qualification", "Doctor Location");

        assertEquals(1, actualDoctor.getDoctorAge().intValue());
        assertEquals(1, actualDoctor.getDoctor_id().intValue());
        assertEquals("Doctor Spel", actualDoctor.getDoctorSpel());
        assertEquals("42", actualDoctor.getDoctorRegNumber());
        assertEquals("Doctor Qualification", actualDoctor.getDoctorQualification());
        assertEquals("Doctor Overview", actualDoctor.getDoctorOverview());
        assertEquals("Doctor Location", actualDoctor.getDoctorLocation());
        assertEquals("Doctor Experience", actualDoctor.getDoctorExperience());
    }
}

