package com.group6.careu.model.Request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class VISARequestModelTest {
    @Test
    void testConstructor() {
        VISARequestModel actualVisaRequestModel = new VISARequestModel();
        assertEquals(0.0f, actualVisaRequestModel.getAmount());
        assertNull(actualVisaRequestModel.getZipcode());
        assertEquals(0, actualVisaRequestModel.getUserId());
        assertNull(actualVisaRequestModel.getTypeOfCard());
        assertEquals(0, actualVisaRequestModel.getTransactionId());
        assertNull(actualVisaRequestModel.getNameOnCard());
        assertEquals(0, actualVisaRequestModel.getExpiryYear());
        assertEquals(0, actualVisaRequestModel.getExpiryMonth());
        assertNull(actualVisaRequestModel.getEmail());
        assertEquals(0, actualVisaRequestModel.getCvv());
        assertNull(actualVisaRequestModel.getCurrency());
        assertNull(actualVisaRequestModel.getCountry());
        assertNull(actualVisaRequestModel.getCardNumber());
    }
}

