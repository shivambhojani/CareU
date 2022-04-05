package com.group6.careu.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;

class UserDocumentModelTest {
    @Test
    void testCanEqual() {
        assertFalse((new UserDocumentModel()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        UserDocumentModel userDocumentModel = new UserDocumentModel();
        assertTrue(userDocumentModel.canEqual(new UserDocumentModel()));
    }

    @Test
    void testConstructor() throws UnsupportedEncodingException {
        UserDocumentModel actualUserDocumentModel = new UserDocumentModel();
        actualUserDocumentModel.setFileName("foo.txt");
        actualUserDocumentModel.setFileType("File Type");
        actualUserDocumentModel.setFileUrl("AAAAAAAA".getBytes("UTF-8"));
        actualUserDocumentModel.setId(1);
        assertEquals("foo.txt", actualUserDocumentModel.getFileName());
        assertEquals("File Type", actualUserDocumentModel.getFileType());
        assertEquals(1, actualUserDocumentModel.getId().intValue());
    }

    @Test
    void testConstructor2() throws UnsupportedEncodingException {
        UserDocumentModel actualUserDocumentModel = new UserDocumentModel(1, "foo.txt", "File Type",
                "AAAAAAAA".getBytes("UTF-8"));
        actualUserDocumentModel.setFileName("foo.txt");
        actualUserDocumentModel.setFileType("File Type");
        actualUserDocumentModel.setFileUrl("AAAAAAAA".getBytes("UTF-8"));
        actualUserDocumentModel.setId(1);
        assertEquals("foo.txt", actualUserDocumentModel.getFileName());
        assertEquals("File Type", actualUserDocumentModel.getFileType());
        assertEquals(1, actualUserDocumentModel.getId().intValue());
    }

    @Test
    void testEquals() {
        assertNotEquals(new UserDocumentModel(), null);
        assertNotEquals(new UserDocumentModel(), "Different type to UserDocumentModel");
    }

    @Test
    void testEquals2() {
        UserDocumentModel userDocumentModel = new UserDocumentModel();
        assertEquals(userDocumentModel, userDocumentModel);
        int expectedHashCodeResult = userDocumentModel.hashCode();
        assertEquals(expectedHashCodeResult, userDocumentModel.hashCode());
    }

    @Test
    void testEquals3() {
        UserDocumentModel userDocumentModel = new UserDocumentModel();
        UserDocumentModel userDocumentModel1 = new UserDocumentModel();
        assertEquals(userDocumentModel, userDocumentModel1);
        int expectedHashCodeResult = userDocumentModel.hashCode();
        assertEquals(expectedHashCodeResult, userDocumentModel1.hashCode());
    }

    @Test
    void testEquals4() throws UnsupportedEncodingException {
        UserDocumentModel userDocumentModel = new UserDocumentModel(1, "foo.txt", "File Type",
                "AAAAAAAA".getBytes("UTF-8"));
        assertNotEquals(userDocumentModel, new UserDocumentModel());
    }

    @Test
    void testEquals5() throws UnsupportedEncodingException {
        UserDocumentModel userDocumentModel = new UserDocumentModel();
        assertNotEquals(userDocumentModel, new UserDocumentModel(1, "foo.txt", "File Type", "AAAAAAAA".getBytes("UTF-8")));
    }

    @Test
    void testEquals6() {
        UserDocumentModel userDocumentModel = new UserDocumentModel();
        userDocumentModel.setFileName("foo.txt");
        assertNotEquals(userDocumentModel, new UserDocumentModel());
    }

    @Test
    void testEquals7() {
        UserDocumentModel userDocumentModel = new UserDocumentModel();
        userDocumentModel.setFileType("File Type");
        assertNotEquals(userDocumentModel, new UserDocumentModel());
    }

    @Test
    void testEquals8() throws UnsupportedEncodingException {
        UserDocumentModel userDocumentModel = new UserDocumentModel();
        userDocumentModel.setFileUrl("AAAAAAAA".getBytes("UTF-8"));
        assertNotEquals(userDocumentModel, new UserDocumentModel());
    }

    @Test
    void testEquals9() throws UnsupportedEncodingException {
        UserDocumentModel userDocumentModel = new UserDocumentModel(1, "foo.txt", "File Type",
                "AAAAAAAA".getBytes("UTF-8"));
        UserDocumentModel userDocumentModel1 = new UserDocumentModel(1, "foo.txt", "File Type",
                "AAAAAAAA".getBytes("UTF-8"));

        assertEquals(userDocumentModel, userDocumentModel1);
        int expectedHashCodeResult = userDocumentModel.hashCode();
        assertEquals(expectedHashCodeResult, userDocumentModel1.hashCode());
    }

    @Test
    void testEquals10() {
        UserDocumentModel userDocumentModel = new UserDocumentModel();

        UserDocumentModel userDocumentModel1 = new UserDocumentModel();
        userDocumentModel1.setFileName("foo.txt");
        assertNotEquals(userDocumentModel, userDocumentModel1);
    }

    @Test
    void testEquals11() {
        UserDocumentModel userDocumentModel = new UserDocumentModel();

        UserDocumentModel userDocumentModel1 = new UserDocumentModel();
        userDocumentModel1.setFileType("File Type");
        assertNotEquals(userDocumentModel, userDocumentModel1);
    }
}

