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
        Transactions actualTransactions = new Transactions("Dr Jane Doe", "42", "Card Type", "Transaction Status", 1, 1,
                "Status Description", 10.0f, mock(Timestamp.class), mock(Timestamp.class));

        assertEquals(10.0f, actualTransactions.getAmount());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1969-12-31", simpleDateFormat.format(actualTransactions.getUpdatedOn()));
        assertNull(actualTransactions.getTransaction_id());
        assertEquals("Transaction Status", actualTransactions.getTransactionStatus());
        assertEquals("Status Description", actualTransactions.getStatusDescription());
        assertEquals(1, actualTransactions.getStatusCode().intValue());
        assertEquals("Dr Jane Doe", actualTransactions.getFullName());
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1969-12-31", simpleDateFormat1.format(actualTransactions.getCreatedOn()));
        assertEquals("Card Type", actualTransactions.getCardType());
        assertEquals("42", actualTransactions.getCardNumber());
        assertEquals(1, actualTransactions.getBankReferenceCode().intValue());
    }
}

