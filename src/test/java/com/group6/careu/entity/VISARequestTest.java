package com.group6.careu.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

class VISARequestTest {
    @Test
    void testConstructor() {
        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf("1970-01-01 10:10:10.0");
        VISARequest actualVisaRequest = new VISARequest(123, "42", 10.0f, 1, "Card Type", timestamp,
                timestamp);

        assertEquals(10.0f, actualVisaRequest.getAmount());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1970-01-01", simpleDateFormat.format(actualVisaRequest.getUpdatedOn()));
        assertEquals(1, actualVisaRequest.getCvv().intValue());
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1970-01-01", simpleDateFormat1.format(actualVisaRequest.getCreatedOn()));
        assertEquals("Card Type", actualVisaRequest.getCardType());
        assertEquals("42", actualVisaRequest.getCardNumber());
        assertEquals(123, actualVisaRequest.getBankTransactionId().intValue());
    }
}

