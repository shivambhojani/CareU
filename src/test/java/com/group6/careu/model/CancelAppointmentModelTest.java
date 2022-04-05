package com.group6.careu.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CancelAppointmentModelTest {
    @Test
    void testCanEqual() {
        assertFalse((new CancelAppointmentModel()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        CancelAppointmentModel cancelAppointmentModel = new CancelAppointmentModel();

        CancelAppointmentModel cancelAppointmentModel1 = new CancelAppointmentModel();
        cancelAppointmentModel1.setApptDate("2020-03-01");
        cancelAppointmentModel1.setDoctorId(123);
        cancelAppointmentModel1.setPatientId(123);
        assertTrue(cancelAppointmentModel.canEqual(cancelAppointmentModel1));
    }

    @Test
    void testConstructor() {
        CancelAppointmentModel actualCancelAppointmentModel = new CancelAppointmentModel();
        actualCancelAppointmentModel.setApptDate("2020-03-01");
        actualCancelAppointmentModel.setDoctorId(123);
        actualCancelAppointmentModel.setPatientId(123);
        assertEquals("2020-03-01", actualCancelAppointmentModel.getApptDate());
        assertEquals(123, actualCancelAppointmentModel.getDoctorId().intValue());
        assertEquals(123, actualCancelAppointmentModel.getPatientId().intValue());
    }

    @Test
    void testEquals() {
        CancelAppointmentModel cancelAppointmentModel = new CancelAppointmentModel();
        cancelAppointmentModel.setApptDate("2020-03-01");
        cancelAppointmentModel.setDoctorId(123);
        cancelAppointmentModel.setPatientId(123);
        assertNotEquals(cancelAppointmentModel, null);
    }

    @Test
    void testEquals2() {
        CancelAppointmentModel cancelAppointmentModel = new CancelAppointmentModel();
        cancelAppointmentModel.setApptDate("2020-03-01");
        cancelAppointmentModel.setDoctorId(123);
        cancelAppointmentModel.setPatientId(123);
        assertNotEquals(cancelAppointmentModel, "Different type to CancelAppointmentModel");
    }

    @Test
    void testEquals3() {
        CancelAppointmentModel cancelAppointmentModel = new CancelAppointmentModel();
        cancelAppointmentModel.setApptDate("2020-03-01");
        cancelAppointmentModel.setDoctorId(123);
        cancelAppointmentModel.setPatientId(123);
        assertEquals(cancelAppointmentModel, cancelAppointmentModel);
        int expectedHashCodeResult = cancelAppointmentModel.hashCode();
        assertEquals(expectedHashCodeResult, cancelAppointmentModel.hashCode());
    }

    @Test
    void testEquals4() {
        CancelAppointmentModel cancelAppointmentModel = new CancelAppointmentModel();
        cancelAppointmentModel.setApptDate("2020-03-01");
        cancelAppointmentModel.setDoctorId(123);
        cancelAppointmentModel.setPatientId(123);

        CancelAppointmentModel cancelAppointmentModel1 = new CancelAppointmentModel();
        cancelAppointmentModel1.setApptDate("2020-03-01");
        cancelAppointmentModel1.setDoctorId(123);
        cancelAppointmentModel1.setPatientId(123);
        assertEquals(cancelAppointmentModel, cancelAppointmentModel1);
        int expectedHashCodeResult = cancelAppointmentModel.hashCode();
        assertEquals(expectedHashCodeResult, cancelAppointmentModel1.hashCode());
    }

    @Test
    void testEquals5() {
        CancelAppointmentModel cancelAppointmentModel = new CancelAppointmentModel();
        cancelAppointmentModel.setApptDate("2020/03/01");
        cancelAppointmentModel.setDoctorId(123);
        cancelAppointmentModel.setPatientId(123);

        CancelAppointmentModel cancelAppointmentModel1 = new CancelAppointmentModel();
        cancelAppointmentModel1.setApptDate("2020-03-01");
        cancelAppointmentModel1.setDoctorId(123);
        cancelAppointmentModel1.setPatientId(123);
        assertNotEquals(cancelAppointmentModel, cancelAppointmentModel1);
    }

    @Test
    void testEquals6() {
        CancelAppointmentModel cancelAppointmentModel = new CancelAppointmentModel();
        cancelAppointmentModel.setApptDate(null);
        cancelAppointmentModel.setDoctorId(123);
        cancelAppointmentModel.setPatientId(123);

        CancelAppointmentModel cancelAppointmentModel1 = new CancelAppointmentModel();
        cancelAppointmentModel1.setApptDate("2020-03-01");
        cancelAppointmentModel1.setDoctorId(123);
        cancelAppointmentModel1.setPatientId(123);
        assertNotEquals(cancelAppointmentModel, cancelAppointmentModel1);
    }

    @Test
    void testEquals7() {
        CancelAppointmentModel cancelAppointmentModel = new CancelAppointmentModel();
        cancelAppointmentModel.setApptDate("2020-03-01");
        cancelAppointmentModel.setDoctorId(1);
        cancelAppointmentModel.setPatientId(123);

        CancelAppointmentModel cancelAppointmentModel1 = new CancelAppointmentModel();
        cancelAppointmentModel1.setApptDate("2020-03-01");
        cancelAppointmentModel1.setDoctorId(123);
        cancelAppointmentModel1.setPatientId(123);
        assertNotEquals(cancelAppointmentModel, cancelAppointmentModel1);
    }

    @Test
    void testEquals8() {
        CancelAppointmentModel cancelAppointmentModel = new CancelAppointmentModel();
        cancelAppointmentModel.setApptDate("2020-03-01");
        cancelAppointmentModel.setDoctorId(null);
        cancelAppointmentModel.setPatientId(123);

        CancelAppointmentModel cancelAppointmentModel1 = new CancelAppointmentModel();
        cancelAppointmentModel1.setApptDate("2020-03-01");
        cancelAppointmentModel1.setDoctorId(123);
        cancelAppointmentModel1.setPatientId(123);
        assertNotEquals(cancelAppointmentModel, cancelAppointmentModel1);
    }

    @Test
    void testEquals9() {
        CancelAppointmentModel cancelAppointmentModel = new CancelAppointmentModel();
        cancelAppointmentModel.setApptDate("2020-03-01");
        cancelAppointmentModel.setDoctorId(123);
        cancelAppointmentModel.setPatientId(1);

        CancelAppointmentModel cancelAppointmentModel1 = new CancelAppointmentModel();
        cancelAppointmentModel1.setApptDate("2020-03-01");
        cancelAppointmentModel1.setDoctorId(123);
        cancelAppointmentModel1.setPatientId(123);
        assertNotEquals(cancelAppointmentModel, cancelAppointmentModel1);
    }

    @Test
    void testEquals10() {
        CancelAppointmentModel cancelAppointmentModel = new CancelAppointmentModel();
        cancelAppointmentModel.setApptDate("2020-03-01");
        cancelAppointmentModel.setDoctorId(123);
        cancelAppointmentModel.setPatientId(null);

        CancelAppointmentModel cancelAppointmentModel1 = new CancelAppointmentModel();
        cancelAppointmentModel1.setApptDate("2020-03-01");
        cancelAppointmentModel1.setDoctorId(123);
        cancelAppointmentModel1.setPatientId(123);
        assertNotEquals(cancelAppointmentModel, cancelAppointmentModel1);
    }

    @Test
    void testEquals11() {
        CancelAppointmentModel cancelAppointmentModel = new CancelAppointmentModel();
        cancelAppointmentModel.setApptDate(null);
        cancelAppointmentModel.setDoctorId(123);
        cancelAppointmentModel.setPatientId(123);

        CancelAppointmentModel cancelAppointmentModel1 = new CancelAppointmentModel();
        cancelAppointmentModel1.setApptDate(null);
        cancelAppointmentModel1.setDoctorId(123);
        cancelAppointmentModel1.setPatientId(123);
        assertEquals(cancelAppointmentModel, cancelAppointmentModel1);
        int expectedHashCodeResult = cancelAppointmentModel.hashCode();
        assertEquals(expectedHashCodeResult, cancelAppointmentModel1.hashCode());
    }

    @Test
    void testEquals12() {
        CancelAppointmentModel cancelAppointmentModel = new CancelAppointmentModel();
        cancelAppointmentModel.setApptDate("2020-03-01");
        cancelAppointmentModel.setDoctorId(null);
        cancelAppointmentModel.setPatientId(123);

        CancelAppointmentModel cancelAppointmentModel1 = new CancelAppointmentModel();
        cancelAppointmentModel1.setApptDate("2020-03-01");
        cancelAppointmentModel1.setDoctorId(null);
        cancelAppointmentModel1.setPatientId(123);
        assertEquals(cancelAppointmentModel, cancelAppointmentModel1);
        int expectedHashCodeResult = cancelAppointmentModel.hashCode();
        assertEquals(expectedHashCodeResult, cancelAppointmentModel1.hashCode());
    }

    @Test
    void testEquals13() {
        CancelAppointmentModel cancelAppointmentModel = new CancelAppointmentModel();
        cancelAppointmentModel.setApptDate("2020-03-01");
        cancelAppointmentModel.setDoctorId(123);
        cancelAppointmentModel.setPatientId(null);

        CancelAppointmentModel cancelAppointmentModel1 = new CancelAppointmentModel();
        cancelAppointmentModel1.setApptDate("2020-03-01");
        cancelAppointmentModel1.setDoctorId(123);
        cancelAppointmentModel1.setPatientId(null);
        assertEquals(cancelAppointmentModel, cancelAppointmentModel1);
        int expectedHashCodeResult = cancelAppointmentModel.hashCode();
        assertEquals(expectedHashCodeResult, cancelAppointmentModel1.hashCode());
    }
}

