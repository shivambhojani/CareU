package com.group6.careu.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

class AMEXRequestTest {
    @Test
    void testConstructor() {
        AMEXRequest actualAmexRequest = new AMEXRequest(123, "42", 10.0f, 1, "Card Type", mock(Timestamp.class),
                mock(Timestamp.class));

        assertEquals(10.0f, actualAmexRequest.getAmount());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1969-12-31", simpleDateFormat.format(actualAmexRequest.getUpdatedOn()));
        assertEquals(1, actualAmexRequest.getCvv().intValue());
        assertNull(actualAmexRequest.getCreditorName());
        assertNull(actualAmexRequest.getCreditorId());
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1969-12-31", simpleDateFormat1.format(actualAmexRequest.getCreatedOn()));
        assertEquals("Card Type", actualAmexRequest.getCardType());
        assertEquals("42", actualAmexRequest.getCardNumber());
        assertEquals(123, actualAmexRequest.getBankTransactionId().intValue());
    }
}

