package com.group6.careu.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

class TransactionsTest {
    @Test
    void testConstructor() {
        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf("1970-01-01 10:10:10.0");
        Transactions actualTransactions = new Transactions("Dr Jane Doe", "42", "Card Type", "Transaction Status", 1, 1,
                "Status Description", 10.0f, timestamp, timestamp);

        assertEquals(10.0f, actualTransactions.getAmount());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1970-01-01", simpleDateFormat.format(actualTransactions.getUpdatedOn()));
        assertNull(actualTransactions.getTransaction_id());
        assertEquals("Transaction Status", actualTransactions.getTransactionStatus());
        assertEquals("Status Description", actualTransactions.getStatusDescription());
        assertEquals(1, actualTransactions.getStatusCode().intValue());
        assertEquals("Dr Jane Doe", actualTransactions.getFullName());
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1970-01-01", simpleDateFormat1.format(actualTransactions.getCreatedOn()));
        assertEquals("Card Type", actualTransactions.getCardType());
        assertEquals("42", actualTransactions.getCardNumber());
        assertEquals(1, actualTransactions.getBankReferenceCode().intValue());
    }
}

