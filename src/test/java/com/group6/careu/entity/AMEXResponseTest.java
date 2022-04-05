package com.group6.careu.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

class AMEXResponseTest {
    @Test
    void testConstructor() {
        AMEXResponse actualAmexResponse = new AMEXResponse(123, 1, "Status Description", 123, mock(Timestamp.class),
                mock(Timestamp.class));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1970-01-01", simpleDateFormat.format(actualAmexResponse.getCreatedOn()));
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1970-01-01", simpleDateFormat1.format(actualAmexResponse.getUpdatedOn()));
        assertEquals(123, actualAmexResponse.getTransactionId().intValue());
        assertEquals("Status Description", actualAmexResponse.getStatusDescription());
        assertEquals(1, actualAmexResponse.getStatusCode().intValue());
        assertEquals(123, actualAmexResponse.getResponseId().intValue());
    }
}

