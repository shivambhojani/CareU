package com.group6.careu.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class DoctorAppointmentModelTest {
    @Test
    void testCanEqual() {
        assertFalse((new DoctorAppointmentModel()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel();
        assertTrue(doctorAppointmentModel.canEqual(new DoctorAppointmentModel()));
    }

    @Test
    void testConstructor() {
        DoctorAppointmentModel actualDoctorAppointmentModel = new DoctorAppointmentModel();
        UUID randomUUIDResult = UUID.randomUUID();
        actualDoctorAppointmentModel.setAppointment_id(randomUUIDResult);
        actualDoctorAppointmentModel.setConsultationType("Consultation Type");
        actualDoctorAppointmentModel.setDate(mock(Date.class));
        actualDoctorAppointmentModel.setEnd_time(mock(Time.class));
        actualDoctorAppointmentModel.setMedications("Medications");
        actualDoctorAppointmentModel.setPatientName("Patient Name");
        actualDoctorAppointmentModel.setPatient_id(1);
        actualDoctorAppointmentModel.setStart_time(mock(Time.class));
        assertSame(randomUUIDResult, actualDoctorAppointmentModel.getAppointment_id());
        assertEquals("Consultation Type", actualDoctorAppointmentModel.getConsultationType());
        assertEquals("Medications", actualDoctorAppointmentModel.getMedications());
        assertEquals("Patient Name", actualDoctorAppointmentModel.getPatientName());
        assertEquals(1, actualDoctorAppointmentModel.getPatient_id().intValue());
    }

    @Test
    void testConstructor2() {
        Date date = mock(Date.class);
        Time start_time = mock(Time.class);
        Time end_time = mock(Time.class);
        DoctorAppointmentModel actualDoctorAppointmentModel = new DoctorAppointmentModel(1, "Patient Name", date,
                start_time, end_time, "Consultation Type", "Medications", UUID.randomUUID());
        UUID randomUUIDResult = UUID.randomUUID();
        actualDoctorAppointmentModel.setAppointment_id(randomUUIDResult);
        actualDoctorAppointmentModel.setConsultationType("Consultation Type");
        actualDoctorAppointmentModel.setDate(mock(Date.class));
        actualDoctorAppointmentModel.setEnd_time(mock(Time.class));
        actualDoctorAppointmentModel.setMedications("Medications");
        actualDoctorAppointmentModel.setPatientName("Patient Name");
        actualDoctorAppointmentModel.setPatient_id(1);
        actualDoctorAppointmentModel.setStart_time(mock(Time.class));
        assertSame(randomUUIDResult, actualDoctorAppointmentModel.getAppointment_id());
        assertEquals("Consultation Type", actualDoctorAppointmentModel.getConsultationType());
        assertEquals("Medications", actualDoctorAppointmentModel.getMedications());
        assertEquals("Patient Name", actualDoctorAppointmentModel.getPatientName());
        assertEquals(1, actualDoctorAppointmentModel.getPatient_id().intValue());
    }

    @Test
    void testEquals() {
        assertNotEquals(new DoctorAppointmentModel(), null);
        assertNotEquals(new DoctorAppointmentModel(), "Different type to DoctorAppointmentModel");
    }

    @Test
    void testEquals2() {
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel();
        assertEquals(doctorAppointmentModel, doctorAppointmentModel);
        int expectedHashCodeResult = doctorAppointmentModel.hashCode();
        assertEquals(expectedHashCodeResult, doctorAppointmentModel.hashCode());
    }

    @Test
    void testEquals3() {
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel();
        DoctorAppointmentModel doctorAppointmentModel1 = new DoctorAppointmentModel();
        assertEquals(doctorAppointmentModel, doctorAppointmentModel1);
        int expectedHashCodeResult = doctorAppointmentModel.hashCode();
        assertEquals(expectedHashCodeResult, doctorAppointmentModel1.hashCode());
    }

    @Test
    void testEquals4() {
        Date date = mock(Date.class);
        Time start_time = mock(Time.class);
        Time end_time = mock(Time.class);
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel(1, "Patient Name", date, start_time,
                end_time, "Consultation Type", "Medications", UUID.randomUUID());
        assertNotEquals(doctorAppointmentModel, new DoctorAppointmentModel());
    }

    @Test
    void testEquals5() {
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel();
        Date date = mock(Date.class);
        Time start_time = mock(Time.class);
        Time end_time = mock(Time.class);
        assertNotEquals(doctorAppointmentModel, new DoctorAppointmentModel(1, "Patient Name", date, start_time, end_time,
                "Consultation Type", "Medications", UUID.randomUUID()));
    }

    @Test
    void testEquals6() {
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel();
        doctorAppointmentModel.setPatientName("Patient Name");
        assertNotEquals(doctorAppointmentModel, new DoctorAppointmentModel());
    }

    @Test
    void testEquals7() {
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel();
        doctorAppointmentModel.setDate(mock(Date.class));
        assertNotEquals(doctorAppointmentModel, new DoctorAppointmentModel());
    }

    @Test
    void testEquals8() {
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel();
        doctorAppointmentModel.setStart_time(mock(Time.class));
        assertNotEquals(doctorAppointmentModel, new DoctorAppointmentModel());
    }

    @Test
    void testEquals9() {
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel();
        doctorAppointmentModel.setEnd_time(mock(Time.class));
        assertNotEquals(doctorAppointmentModel, new DoctorAppointmentModel());
    }

    @Test
    void testEquals10() {
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel();
        doctorAppointmentModel.setConsultationType("Consultation Type");
        assertNotEquals(doctorAppointmentModel, new DoctorAppointmentModel());
    }

    @Test
    void testEquals11() {
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel();
        doctorAppointmentModel.setMedications("Medications");
        assertNotEquals(doctorAppointmentModel, new DoctorAppointmentModel());
    }

    @Test
    void testEquals12() {
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel();
        doctorAppointmentModel.setAppointment_id(UUID.randomUUID());
        assertNotEquals(doctorAppointmentModel, new DoctorAppointmentModel());
    }

    @Test
    void testEquals13() {
        Date date = mock(Date.class);
        Time start_time = mock(Time.class);
        Time end_time = mock(Time.class);
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel(1, "Patient Name", date, start_time,
                end_time, "Consultation Type", "Medications", UUID.randomUUID());
        Date date1 = mock(Date.class);
        Time start_time1 = mock(Time.class);
        Time end_time1 = mock(Time.class);
        assertNotEquals(doctorAppointmentModel, new DoctorAppointmentModel(1, "Patient Name", date1, start_time1, end_time1,
                "Consultation Type", "Medications", UUID.randomUUID()));
    }

    @Test
    void testEquals14() {
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel();

        DoctorAppointmentModel doctorAppointmentModel1 = new DoctorAppointmentModel();
        doctorAppointmentModel1.setPatientName("Patient Name");
        assertNotEquals(doctorAppointmentModel, doctorAppointmentModel1);
    }

    @Test
    void testEquals15() {
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel();

        DoctorAppointmentModel doctorAppointmentModel1 = new DoctorAppointmentModel();
        doctorAppointmentModel1.setDate(mock(Date.class));
        assertNotEquals(doctorAppointmentModel, doctorAppointmentModel1);
    }

    @Test
    void testEquals16() {
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel();

        DoctorAppointmentModel doctorAppointmentModel1 = new DoctorAppointmentModel();
        doctorAppointmentModel1.setStart_time(mock(Time.class));
        assertNotEquals(doctorAppointmentModel, doctorAppointmentModel1);
    }

    @Test
    void testEquals17() {
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel();

        DoctorAppointmentModel doctorAppointmentModel1 = new DoctorAppointmentModel();
        doctorAppointmentModel1.setEnd_time(mock(Time.class));
        assertNotEquals(doctorAppointmentModel, doctorAppointmentModel1);
    }

    @Test
    void testEquals18() {
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel();

        DoctorAppointmentModel doctorAppointmentModel1 = new DoctorAppointmentModel();
        doctorAppointmentModel1.setConsultationType("Consultation Type");
        assertNotEquals(doctorAppointmentModel, doctorAppointmentModel1);
    }

    @Test
    void testEquals19() {
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel();

        DoctorAppointmentModel doctorAppointmentModel1 = new DoctorAppointmentModel();
        doctorAppointmentModel1.setMedications("Medications");
        assertNotEquals(doctorAppointmentModel, doctorAppointmentModel1);
    }

    @Test
    void testEquals20() {
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel();

        DoctorAppointmentModel doctorAppointmentModel1 = new DoctorAppointmentModel();
        doctorAppointmentModel1.setAppointment_id(UUID.randomUUID());
        assertNotEquals(doctorAppointmentModel, doctorAppointmentModel1);
    }

    @Test
    void testEquals21() {
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel();
        doctorAppointmentModel.setConsultationType("Consultation Type");

        DoctorAppointmentModel doctorAppointmentModel1 = new DoctorAppointmentModel();
        doctorAppointmentModel1.setConsultationType("Consultation Type");
        assertEquals(doctorAppointmentModel, doctorAppointmentModel1);
        int expectedHashCodeResult = doctorAppointmentModel.hashCode();
        assertEquals(expectedHashCodeResult, doctorAppointmentModel1.hashCode());
    }

    @Test
    void testEquals22() {
        DoctorAppointmentModel doctorAppointmentModel = new DoctorAppointmentModel();
        doctorAppointmentModel.setMedications("Medications");

        DoctorAppointmentModel doctorAppointmentModel1 = new DoctorAppointmentModel();
        doctorAppointmentModel1.setMedications("Medications");
        assertEquals(doctorAppointmentModel, doctorAppointmentModel1);
        int expectedHashCodeResult = doctorAppointmentModel.hashCode();
        assertEquals(expectedHashCodeResult, doctorAppointmentModel1.hashCode());
    }
}

