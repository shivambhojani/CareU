package com.group6.careu.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

class VISAResponseTest {
    @Test
    void testConstructor() {
        VISAResponse actualVisaResponse = new VISAResponse(123, 1, "Status Description", 123, mock(Timestamp.class),
                mock(Timestamp.class));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1970-01-01", simpleDateFormat.format(actualVisaResponse.getCreatedOn()));
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1970-01-01", simpleDateFormat1.format(actualVisaResponse.getUpdatedOn()));
        assertEquals(123, actualVisaResponse.getTransactionId().intValue());
        assertEquals("Status Description", actualVisaResponse.getStatusDescription());
        assertEquals(1, actualVisaResponse.getStatusCode().intValue());
        assertEquals(123, actualVisaResponse.getResponseId().intValue());
    }
}

