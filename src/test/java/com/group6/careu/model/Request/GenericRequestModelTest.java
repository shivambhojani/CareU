package com.group6.careu.model.Request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GenericRequestModelTest {
    @Test
    void testConstructor() {
        GenericRequestModel actualGenericRequestModel = new GenericRequestModel();
        actualGenericRequestModel.setAmount(10.0f);
        actualGenericRequestModel.setCardNumber("42");
        actualGenericRequestModel.setCountry("GB");
        actualGenericRequestModel.setCurrency("GBP");
        actualGenericRequestModel.setCvv(1);
        actualGenericRequestModel.setEmail("jane.doe@example.org");
        actualGenericRequestModel.setExpiryMonth(1);
        actualGenericRequestModel.setExpiryYear(1);
        actualGenericRequestModel.setNameOnCard("Name On Card");
        actualGenericRequestModel.setTransactionId(123);
        actualGenericRequestModel.setTypeOfCard("Type Of Card");
        actualGenericRequestModel.setUserId(123);
        actualGenericRequestModel.setZipcode("21654");
        assertEquals(10.0f, actualGenericRequestModel.getAmount());
        assertEquals("42", actualGenericRequestModel.getCardNumber());
        assertEquals("GB", actualGenericRequestModel.getCountry());
        assertEquals("GBP", actualGenericRequestModel.getCurrency());
        assertEquals(1, actualGenericRequestModel.getCvv());
        assertEquals("jane.doe@example.org", actualGenericRequestModel.getEmail());
        assertEquals(1, actualGenericRequestModel.getExpiryMonth());
        assertEquals(1, actualGenericRequestModel.getExpiryYear());
        assertEquals("Name On Card", actualGenericRequestModel.getNameOnCard());
        assertEquals(123, actualGenericRequestModel.getTransactionId());
        assertEquals("Type Of Card", actualGenericRequestModel.getTypeOfCard());
        assertEquals(123, actualGenericRequestModel.getUserId());
        assertEquals("21654", actualGenericRequestModel.getZipcode());
    }
}

