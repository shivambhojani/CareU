package com.group6.careu.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PatientSettingsModelTest {
    @Test
    void testCanEqual() {
        assertFalse((new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender", "jane.doe@example.org", "Disease"))
                .canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        PatientSettingsModel patientSettingsModel = new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender",
                "jane.doe@example.org", "Disease");
        assertTrue(patientSettingsModel
                .canEqual(new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender", "jane.doe@example.org", "Disease")));
    }

    @Test
    void testConstructor() {
        PatientSettingsModel actualPatientSettingsModel = new PatientSettingsModel();
        actualPatientSettingsModel.setDisease("Disease");
        actualPatientSettingsModel.setEmail("jane.doe@example.org");
        actualPatientSettingsModel.setFirstName("Jane");
        actualPatientSettingsModel.setGender("Gender");
        actualPatientSettingsModel.setLastName("Doe");
        actualPatientSettingsModel.setPhone("4105551212");
        assertEquals("Disease", actualPatientSettingsModel.getDisease());
        assertEquals("jane.doe@example.org", actualPatientSettingsModel.getEmail());
        assertEquals("Jane", actualPatientSettingsModel.getFirstName());
        assertEquals("Gender", actualPatientSettingsModel.getGender());
        assertEquals("Doe", actualPatientSettingsModel.getLastName());
        assertEquals("4105551212", actualPatientSettingsModel.getPhone());
    }

    @Test
    void testConstructor2() {
        PatientSettingsModel actualPatientSettingsModel = new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender",
                "jane.doe@example.org", "Disease");
        actualPatientSettingsModel.setDisease("Disease");
        actualPatientSettingsModel.setEmail("jane.doe@example.org");
        actualPatientSettingsModel.setFirstName("Jane");
        actualPatientSettingsModel.setGender("Gender");
        actualPatientSettingsModel.setLastName("Doe");
        actualPatientSettingsModel.setPhone("4105551212");
        assertEquals("Disease", actualPatientSettingsModel.getDisease());
        assertEquals("jane.doe@example.org", actualPatientSettingsModel.getEmail());
        assertEquals("Jane", actualPatientSettingsModel.getFirstName());
        assertEquals("Gender", actualPatientSettingsModel.getGender());
        assertEquals("Doe", actualPatientSettingsModel.getLastName());
        assertEquals("4105551212", actualPatientSettingsModel.getPhone());
    }

    @Test
    void testEquals() {
        assertNotEquals(new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender", "jane.doe@example.org", "Disease"),
                null);
        assertNotEquals(new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender", "jane.doe@example.org", "Disease"),
                "Different type to PatientSettingsModel");
    }

    @Test
    void testEquals2() {
        PatientSettingsModel patientSettingsModel = new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender",
                "jane.doe@example.org", "Disease");
        assertEquals(patientSettingsModel, patientSettingsModel);
        int expectedHashCodeResult = patientSettingsModel.hashCode();
        assertEquals(expectedHashCodeResult, patientSettingsModel.hashCode());
    }

    @Test
    void testEquals3() {
        PatientSettingsModel patientSettingsModel = new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender",
                "jane.doe@example.org", "Disease");
        PatientSettingsModel patientSettingsModel1 = new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender",
                "jane.doe@example.org", "Disease");

        assertEquals(patientSettingsModel, patientSettingsModel1);
        int expectedHashCodeResult = patientSettingsModel.hashCode();
        assertEquals(expectedHashCodeResult, patientSettingsModel1.hashCode());
    }

    @Test
    void testEquals4() {
        PatientSettingsModel patientSettingsModel = new PatientSettingsModel("Doe", "Doe", "4105551212", "Gender",
                "jane.doe@example.org", "Disease");
        assertNotEquals(patientSettingsModel,
                new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender", "jane.doe@example.org", "Disease"));
    }

    @Test
    void testEquals5() {
        PatientSettingsModel patientSettingsModel = new PatientSettingsModel(null, "Doe", "4105551212", "Gender",
                "jane.doe@example.org", "Disease");
        assertNotEquals(patientSettingsModel,
                new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender", "jane.doe@example.org", "Disease"));
    }

    @Test
    void testEquals6() {
        PatientSettingsModel patientSettingsModel = new PatientSettingsModel("Jane", "Jane", "4105551212", "Gender",
                "jane.doe@example.org", "Disease");
        assertNotEquals(patientSettingsModel,
                new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender", "jane.doe@example.org", "Disease"));
    }

    @Test
    void testEquals7() {
        PatientSettingsModel patientSettingsModel = new PatientSettingsModel("Jane", null, "4105551212", "Gender",
                "jane.doe@example.org", "Disease");
        assertNotEquals(patientSettingsModel,
                new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender", "jane.doe@example.org", "Disease"));
    }

    @Test
    void testEquals8() {
        PatientSettingsModel patientSettingsModel = new PatientSettingsModel("Jane", "Doe", "+44 1865 4960636", "Gender",
                "jane.doe@example.org", "Disease");
        assertNotEquals(patientSettingsModel,
                new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender", "jane.doe@example.org", "Disease"));
    }

    @Test
    void testEquals9() {
        PatientSettingsModel patientSettingsModel = new PatientSettingsModel("Jane", "Doe", null, "Gender",
                "jane.doe@example.org", "Disease");
        assertNotEquals(patientSettingsModel,
                new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender", "jane.doe@example.org", "Disease"));
    }

    @Test
    void testEquals10() {
        PatientSettingsModel patientSettingsModel = new PatientSettingsModel("Jane", "Doe", "4105551212", "Jane",
                "jane.doe@example.org", "Disease");
        assertNotEquals(patientSettingsModel,
                new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender", "jane.doe@example.org", "Disease"));
    }

    @Test
    void testEquals11() {
        PatientSettingsModel patientSettingsModel = new PatientSettingsModel("Jane", "Doe", "4105551212", null,
                "jane.doe@example.org", "Disease");
        assertNotEquals(patientSettingsModel,
                new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender", "jane.doe@example.org", "Disease"));
    }

    @Test
    void testEquals12() {
        PatientSettingsModel patientSettingsModel = new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender", "Jane",
                "Disease");
        assertNotEquals(patientSettingsModel,
                new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender", "jane.doe@example.org", "Disease"));
    }

    @Test
    void testEquals13() {
        PatientSettingsModel patientSettingsModel = new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender", null,
                "Disease");
        assertNotEquals(patientSettingsModel,
                new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender", "jane.doe@example.org", "Disease"));
    }

    @Test
    void testEquals14() {
        PatientSettingsModel patientSettingsModel = new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender",
                "jane.doe@example.org", "Jane");
        assertNotEquals(patientSettingsModel,
                new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender", "jane.doe@example.org", "Disease"));
    }

    @Test
    void testEquals15() {
        PatientSettingsModel patientSettingsModel = new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender",
                "jane.doe@example.org", null);
        assertNotEquals(patientSettingsModel,
                new PatientSettingsModel("Jane", "Doe", "4105551212", "Gender", "jane.doe@example.org", "Disease"));
    }

    @Test
    void testEquals16() {
        PatientSettingsModel patientSettingsModel = new PatientSettingsModel(null, "Doe", "4105551212", "Gender",
                "jane.doe@example.org", "Disease");
        PatientSettingsModel patientSettingsModel1 = new PatientSettingsModel(null, "Doe", "4105551212", "Gender",
                "jane.doe@example.org", "Disease");

        assertEquals(patientSettingsModel, patientSettingsModel1);
        int expectedHashCodeResult = patientSettingsModel.hashCode();
        assertEquals(expectedHashCodeResult, patientSettingsModel1.hashCode());
    }

    @Test
    void testEquals17() {
        PatientSettingsModel patientSettingsModel = new PatientSettingsModel("Jane", null, "4105551212", "Gender",
                "jane.doe@example.org", "Disease");
        PatientSettingsModel patientSettingsModel1 = new PatientSettingsModel("Jane", null, "4105551212", "Gender",
                "jane.doe@example.org", "Disease");

        assertEquals(patientSettingsModel, patientSettingsModel1);
        int expectedHashCodeResult = patientSettingsModel.hashCode();
        assertEquals(expectedHashCodeResult, patientSettingsModel1.hashCode());
    }

    @Test
    void testEquals18() {
        PatientSettingsModel patientSettingsModel = new PatientSettingsModel("Jane", "Doe", null, "Gender",
                "jane.doe@example.org", "Disease");
        PatientSettingsModel patientSettingsModel1 = new PatientSettingsModel("Jane", "Doe", null, "Gender",
                "jane.doe@example.org", "Disease");

        assertEquals(patientSettingsModel, patientSettingsModel1);
        int expectedHashCodeResult = patientSettingsModel.hashCode();
        assertEquals(expectedHashCodeResult, patientSettingsModel1.hashCode());
    }
}

