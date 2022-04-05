package com.group6.careu.model.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GenericResponseModelTest {
    @Test
    void testConstructor() {
        GenericResponseModel actualGenericResponseModel = new GenericResponseModel();
        actualGenericResponseModel.setResponseId(123);
        actualGenericResponseModel.setStatusCode(1);
        actualGenericResponseModel.setStatusDescription("Status Description");
        actualGenericResponseModel.setTransactionId(123);
        assertEquals(123, actualGenericResponseModel.getResponseId().intValue());
        assertEquals(1, actualGenericResponseModel.getStatusCode());
        assertEquals("Status Description", actualGenericResponseModel.getStatusDescription());
        assertEquals(123, actualGenericResponseModel.getTransactionId());
    }
}

