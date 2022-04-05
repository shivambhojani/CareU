package com.group6.careu.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class DoctorAvailabilityModelTest {
    @Test
    void testCanEqual() {
        assertFalse((new DoctorAvailabilityModel()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        DoctorAvailabilityModel doctorAvailabilityModel = new DoctorAvailabilityModel();
        assertTrue(doctorAvailabilityModel.canEqual(new DoctorAvailabilityModel()));
    }

    @Test
    void testConstructor() {
        DoctorAvailabilityModel actualDoctorAvailabilityModel = new DoctorAvailabilityModel();
        ArrayList<String> stringList = new ArrayList<>();
        actualDoctorAvailabilityModel.setAvailableDate(stringList);
        actualDoctorAvailabilityModel.setDoctorId(123);
        ArrayList<String> stringList1 = new ArrayList<>();
        actualDoctorAvailabilityModel.setEndTime(stringList1);
        ArrayList<String> stringList2 = new ArrayList<>();
        actualDoctorAvailabilityModel.setStartTime(stringList2);
        List<String> availableDate = actualDoctorAvailabilityModel.getAvailableDate();
        assertSame(stringList, availableDate);
        assertEquals(stringList1, availableDate);
        assertEquals(stringList2, availableDate);
        assertEquals(123, actualDoctorAvailabilityModel.getDoctorId().intValue());
        List<String> endTime = actualDoctorAvailabilityModel.getEndTime();
        assertSame(stringList1, endTime);
        assertEquals(availableDate, endTime);
        List<String> startTime = actualDoctorAvailabilityModel.getStartTime();
        assertEquals(startTime, endTime);
        assertSame(stringList2, startTime);
        assertEquals(stringList1, startTime);
        assertEquals(availableDate, startTime);
    }

    @Test
    void testConstructor2() {
        ArrayList<String> stringList = new ArrayList<>();
        ArrayList<String> stringList1 = new ArrayList<>();
        ArrayList<String> stringList2 = new ArrayList<>();
        DoctorAvailabilityModel actualDoctorAvailabilityModel = new DoctorAvailabilityModel(123, stringList, stringList1,
                stringList2);
        ArrayList<String> stringList3 = new ArrayList<>();
        actualDoctorAvailabilityModel.setAvailableDate(stringList3);
        actualDoctorAvailabilityModel.setDoctorId(123);
        ArrayList<String> stringList4 = new ArrayList<>();
        actualDoctorAvailabilityModel.setEndTime(stringList4);
        ArrayList<String> stringList5 = new ArrayList<>();
        actualDoctorAvailabilityModel.setStartTime(stringList5);
        List<String> availableDate = actualDoctorAvailabilityModel.getAvailableDate();
        assertSame(stringList3, availableDate);
        assertEquals(stringList, availableDate);
        assertEquals(stringList1, availableDate);
        assertEquals(stringList2, availableDate);
        assertEquals(stringList4, availableDate);
        assertEquals(stringList5, availableDate);
        assertEquals(123, actualDoctorAvailabilityModel.getDoctorId().intValue());
        List<String> endTime = actualDoctorAvailabilityModel.getEndTime();
        assertSame(stringList4, endTime);
        assertEquals(stringList, endTime);
        assertEquals(stringList1, endTime);
        assertEquals(stringList2, endTime);
        assertEquals(availableDate, endTime);
        List<String> startTime = actualDoctorAvailabilityModel.getStartTime();
        assertEquals(startTime, endTime);
        assertSame(stringList5, startTime);
        assertEquals(stringList, startTime);
        assertEquals(stringList1, startTime);
        assertEquals(stringList2, startTime);
        assertEquals(stringList4, startTime);
        assertEquals(availableDate, startTime);
    }

    @Test
    void testEquals() {
        assertNotEquals(new DoctorAvailabilityModel(), null);
        assertNotEquals(new DoctorAvailabilityModel(), "Different type to DoctorAvailabilityModel");
    }

    @Test
    void testEquals2() {
        DoctorAvailabilityModel doctorAvailabilityModel = new DoctorAvailabilityModel();
        assertEquals(doctorAvailabilityModel, doctorAvailabilityModel);
        int expectedHashCodeResult = doctorAvailabilityModel.hashCode();
        assertEquals(expectedHashCodeResult, doctorAvailabilityModel.hashCode());
    }

    @Test
    void testEquals3() {
        DoctorAvailabilityModel doctorAvailabilityModel = new DoctorAvailabilityModel();
        DoctorAvailabilityModel doctorAvailabilityModel1 = new DoctorAvailabilityModel();
        assertEquals(doctorAvailabilityModel, doctorAvailabilityModel1);
        int expectedHashCodeResult = doctorAvailabilityModel.hashCode();
        assertEquals(expectedHashCodeResult, doctorAvailabilityModel1.hashCode());
    }

    @Test
    void testEquals4() {
        ArrayList<String> availableDate = new ArrayList<>();
        ArrayList<String> startTime = new ArrayList<>();
        DoctorAvailabilityModel doctorAvailabilityModel = new DoctorAvailabilityModel(123, availableDate, startTime,
                new ArrayList<>());
        assertNotEquals(doctorAvailabilityModel, new DoctorAvailabilityModel());
    }

    @Test
    void testEquals5() {
        DoctorAvailabilityModel doctorAvailabilityModel = new DoctorAvailabilityModel();
        ArrayList<String> availableDate = new ArrayList<>();
        ArrayList<String> startTime = new ArrayList<>();
        assertNotEquals(doctorAvailabilityModel,
                new DoctorAvailabilityModel(123, availableDate, startTime, new ArrayList<>()));
    }

    @Test
    void testEquals6() {
        DoctorAvailabilityModel doctorAvailabilityModel = new DoctorAvailabilityModel();
        doctorAvailabilityModel.setAvailableDate(new ArrayList<>());
        assertNotEquals(doctorAvailabilityModel, new DoctorAvailabilityModel());
    }

    @Test
    void testEquals7() {
        DoctorAvailabilityModel doctorAvailabilityModel = new DoctorAvailabilityModel();
        doctorAvailabilityModel.setStartTime(new ArrayList<>());
        assertNotEquals(doctorAvailabilityModel, new DoctorAvailabilityModel());
    }

    @Test
    void testEquals8() {
        DoctorAvailabilityModel doctorAvailabilityModel = new DoctorAvailabilityModel();
        doctorAvailabilityModel.setEndTime(new ArrayList<>());
        assertNotEquals(doctorAvailabilityModel, new DoctorAvailabilityModel());
    }

    @Test
    void testEquals9() {
        ArrayList<String> availableDate = new ArrayList<>();
        ArrayList<String> startTime = new ArrayList<>();
        DoctorAvailabilityModel doctorAvailabilityModel = new DoctorAvailabilityModel(123, availableDate, startTime,
                new ArrayList<>());
        ArrayList<String> availableDate1 = new ArrayList<>();
        ArrayList<String> startTime1 = new ArrayList<>();
        DoctorAvailabilityModel doctorAvailabilityModel1 = new DoctorAvailabilityModel(123, availableDate1, startTime1,
                new ArrayList<>());

        assertEquals(doctorAvailabilityModel, doctorAvailabilityModel1);
        int expectedHashCodeResult = doctorAvailabilityModel.hashCode();
        assertEquals(expectedHashCodeResult, doctorAvailabilityModel1.hashCode());
    }

    @Test
    void testEquals10() {
        DoctorAvailabilityModel doctorAvailabilityModel = new DoctorAvailabilityModel();

        DoctorAvailabilityModel doctorAvailabilityModel1 = new DoctorAvailabilityModel();
        doctorAvailabilityModel1.setAvailableDate(new ArrayList<>());
        assertNotEquals(doctorAvailabilityModel, doctorAvailabilityModel1);
    }

    @Test
    void testEquals11() {
        DoctorAvailabilityModel doctorAvailabilityModel = new DoctorAvailabilityModel();

        DoctorAvailabilityModel doctorAvailabilityModel1 = new DoctorAvailabilityModel();
        doctorAvailabilityModel1.setStartTime(new ArrayList<>());
        assertNotEquals(doctorAvailabilityModel, doctorAvailabilityModel1);
    }

    @Test
    void testEquals12() {
        DoctorAvailabilityModel doctorAvailabilityModel = new DoctorAvailabilityModel();

        DoctorAvailabilityModel doctorAvailabilityModel1 = new DoctorAvailabilityModel();
        doctorAvailabilityModel1.setEndTime(new ArrayList<>());
        assertNotEquals(doctorAvailabilityModel, doctorAvailabilityModel1);
    }
}

