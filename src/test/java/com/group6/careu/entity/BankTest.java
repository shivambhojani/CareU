package com.group6.careu.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class BankTest {
    @Test
    void testConstructor() {
        Bank actualBank = new Bank("jane.doe@example.org", "42 Main St", "Oxford", "MD", "21654", "Dr Jane Doe", "42", 1, 1,
                1, 10.0f);

        assertEquals("42 Main St", actualBank.getAddress());
        assertEquals("21654", actualBank.getZipcode());
        assertEquals("MD", actualBank.getState());
        assertNull(actualBank.getId());
        assertEquals("Dr Jane Doe", actualBank.getFullName());
        assertEquals(1, actualBank.getExpiryYear());
        assertEquals(1, actualBank.getExpiryMonth());
        assertEquals("jane.doe@example.org", actualBank.getEmail());
        assertEquals(1, actualBank.getCvv());
        assertEquals("Oxford", actualBank.getCity());
        assertEquals("42", actualBank.getCard_number());
        assertEquals(10.0f, actualBank.getAmount());
    }
}

