package com.group6.careu.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;

class UserDocumentTest {
    @Test
    void testConstructor() throws UnsupportedEncodingException {
        UserDocument actualUserDocument = new UserDocument("foo.txt", "File Type", "AAAAAAAA".getBytes("UTF-8"), 123);
        actualUserDocument.setFileContent("AAAAAAAA".getBytes("UTF-8"));
        actualUserDocument.setFileName("foo.txt");
        actualUserDocument.setFileType("File Type");
        actualUserDocument.setUserId(123);
        assertEquals("foo.txt", actualUserDocument.getFileName());
        assertEquals("File Type", actualUserDocument.getFileType());
        assertEquals(123, actualUserDocument.getUserId().intValue());
    }
}

