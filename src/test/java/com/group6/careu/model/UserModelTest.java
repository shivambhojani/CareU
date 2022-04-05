package com.group6.careu.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UserModelTest {
    @Test
    void testCanEqual() {
        assertFalse((new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role"))
                .canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        UserModel userModel = new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role");
        assertTrue(userModel
                .canEqual(new UserModel("Jane", "Doe", 3, "Gender", "jane.doe@example.org", "4455", true, "Role")));
    }

    @Test
    void testConstructor() {
        UserModel actualUserModel = new UserModel();
        actualUserModel.setEmail("jane.doe@example.org");
        actualUserModel.setEnabled(true);
        actualUserModel.setFirstName("Jane");
        actualUserModel.setGender("Gender");
        actualUserModel.setLastName("Doe");
        actualUserModel.setPassword("4455");
        actualUserModel.setPhone(1);
        actualUserModel.setRole("Role");
        assertEquals("jane.doe@example.org", actualUserModel.getEmail());
        assertEquals("Jane", actualUserModel.getFirstName());
        assertEquals("Gender", actualUserModel.getGender());
        assertEquals("Doe", actualUserModel.getLastName());
        assertEquals("4455", actualUserModel.getPassword());
        assertEquals(1, actualUserModel.getPhone());
        assertEquals("Role", actualUserModel.getRole());
        assertTrue(actualUserModel.isEnabled());
    }

    @Test
    void testConstructor2() {
        UserModel actualUserModel = new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true,
                "Role");
        actualUserModel.setEmail("jane.doe@example.org");
        actualUserModel.setEnabled(true);
        actualUserModel.setFirstName("Jane");
        actualUserModel.setGender("Gender");
        actualUserModel.setLastName("Doe");
        actualUserModel.setPassword("4455");
        actualUserModel.setPhone(1);
        actualUserModel.setRole("Role");
        assertEquals("jane.doe@example.org", actualUserModel.getEmail());
        assertEquals("Jane", actualUserModel.getFirstName());
        assertEquals("Gender", actualUserModel.getGender());
        assertEquals("Doe", actualUserModel.getLastName());
        assertEquals("4455", actualUserModel.getPassword());
        assertEquals(1, actualUserModel.getPhone());
        assertEquals("Role", actualUserModel.getRole());
        assertTrue(actualUserModel.isEnabled());
    }

    @Test
    void testEquals() {
        assertNotEquals(new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role"), null);
        assertNotEquals(new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role"),
                "Different type to UserModel");
    }

    @Test
    void testEquals2() {
        UserModel userModel = new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role");
        assertEquals(userModel, userModel);
        int expectedHashCodeResult = userModel.hashCode();
        assertEquals(expectedHashCodeResult, userModel.hashCode());
    }

    @Test
    void testEquals3() {
        UserModel userModel = new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role");
        UserModel userModel1 = new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role");

        assertEquals(userModel, userModel1);
        int expectedHashCodeResult = userModel.hashCode();
        assertEquals(expectedHashCodeResult, userModel1.hashCode());
    }

    @Test
    void testEquals4() {
        UserModel userModel = new UserModel("Doe", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role");
        assertNotEquals(userModel,
                new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role"));
    }

    @Test
    void testEquals5() {
        UserModel userModel = new UserModel(null, "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role");
        assertNotEquals(userModel,
                new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role"));
    }

    @Test
    void testEquals6() {
        UserModel userModel = new UserModel("Jane", "Jane", 1, "Gender", "jane.doe@example.org", "4455", true, "Role");
        assertNotEquals(userModel,
                new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role"));
    }

    @Test
    void testEquals7() {
        UserModel userModel = new UserModel("Jane", null, 1, "Gender", "jane.doe@example.org", "4455", true, "Role");
        assertNotEquals(userModel,
                new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role"));
    }

    @Test
    void testEquals8() {
        UserModel userModel = new UserModel("Jane", "Doe", 3, "Gender", "jane.doe@example.org", "4455", true, "Role");
        assertNotEquals(userModel,
                new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role"));
    }

    @Test
    void testEquals9() {
        UserModel userModel = new UserModel("Jane", "Doe", 1, "Jane", "jane.doe@example.org", "4455", true, "Role");
        assertNotEquals(userModel,
                new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role"));
    }

    @Test
    void testEquals10() {
        UserModel userModel = new UserModel("Jane", "Doe", 1, null, "jane.doe@example.org", "4455", true, "Role");
        assertNotEquals(userModel,
                new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role"));
    }

    @Test
    void testEquals11() {
        UserModel userModel = new UserModel("Jane", "Doe", 1, "Gender", "Jane", "4455", true, "Role");
        assertNotEquals(userModel,
                new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role"));
    }

    @Test
    void testEquals12() {
        UserModel userModel = new UserModel("Jane", "Doe", 1, "Gender", null, "4455", true, "Role");
        assertNotEquals(userModel,
                new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role"));
    }

    @Test
    void testEquals13() {
        UserModel userModel = new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "Jane", true, "Role");
        assertNotEquals(userModel,
                new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role"));
    }

    @Test
    void testEquals14() {
        UserModel userModel = new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", null, true, "Role");
        assertNotEquals(userModel,
                new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role"));
    }

    @Test
    void testEquals15() {
        UserModel userModel = new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", false, "Role");
        assertNotEquals(userModel,
                new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role"));
    }

    @Test
    void testEquals16() {
        UserModel userModel = new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Jane");
        assertNotEquals(userModel,
                new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role"));
    }

    @Test
    void testEquals17() {
        UserModel userModel = new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, null);
        assertNotEquals(userModel,
                new UserModel("Jane", "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role"));
    }

    @Test
    void testEquals18() {
        UserModel userModel = new UserModel(null, "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role");
        UserModel userModel1 = new UserModel(null, "Doe", 1, "Gender", "jane.doe@example.org", "4455", true, "Role");

        assertEquals(userModel, userModel1);
        int expectedHashCodeResult = userModel.hashCode();
        assertEquals(expectedHashCodeResult, userModel1.hashCode());
    }

    @Test
    void testEquals19() {
        UserModel userModel = new UserModel("Jane", null, 1, "Gender", "jane.doe@example.org", "4455", true, "Role");
        UserModel userModel1 = new UserModel("Jane", null, 1, "Gender", "jane.doe@example.org", "4455", true, "Role");

        assertEquals(userModel, userModel1);
        int expectedHashCodeResult = userModel.hashCode();
        assertEquals(expectedHashCodeResult, userModel1.hashCode());
    }
}

