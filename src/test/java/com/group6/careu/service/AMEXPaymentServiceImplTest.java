package com.group6.careu.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group6.careu.entity.AMEXRequest;
import com.group6.careu.entity.AMEXResponse;
import com.group6.careu.entity.Bank;
import com.group6.careu.model.Request.AMEXRequestModel;
import com.group6.careu.model.Response.AMEXResponseModel;
import com.group6.careu.repository.AMEXRequestRepository;
import com.group6.careu.repository.AMEXResponseRepository;
import com.group6.careu.repository.BankRepository;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
/**
 * Created by Bijitashya on 04, 2022
 */

@ContextConfiguration(classes = {AMEXPaymentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AMEXPaymentServiceImplTest {
    @Autowired
    private AMEXPaymentServiceImpl aMEXPaymentServiceImpl;

    @MockBean
    private AMEXRequestRepository aMEXRequestRepository;

    @MockBean
    private AMEXResponseRepository aMEXResponseRepository;

    @MockBean
    private BankRepository bankRepository;

    @Test
    void testMakePayment() {
        Bank bank = new Bank();
        bank.setAddress("42 Main St");
        bank.setAmount(10.0f);
        bank.setCard_number("42");
        bank.setCity("Oxford");
        bank.setCvv(1);
        bank.setEmail("jane.doe@example.org");
        bank.setExpiryMonth(04);
        bank.setExpiryYear(22);
        bank.setFullName("Dr Jane Doe");
        bank.setId(1);
        bank.setState("MD");
        bank.setZipcode("21654");

        Bank bank1 = new Bank();
        bank1.setAddress("42 Main St");
        bank1.setAmount(10.0f);
        bank1.setCard_number("42");
        bank1.setCity("Oxford");
        bank1.setCvv(1);
        bank1.setEmail("jane.doe@example.org");
        bank1.setExpiryMonth(1);
        bank1.setExpiryYear(1);
        bank1.setFullName("Dr Jane Doe");
        bank1.setId(1);
        bank1.setState("MD");
        bank1.setZipcode("21654");
        when(this.bankRepository.save((Bank) any())).thenReturn(bank1);
        when(this.bankRepository.getUserByCardNumber((String) any())).thenReturn(bank);

        AMEXResponse amexResponse = new AMEXResponse();
        amexResponse.setCreatedOn(mock(Timestamp.class));
        amexResponse.setResponseId(123);
        amexResponse.setStatusCode(1);
        amexResponse.setStatusDescription("Status Description");
        amexResponse.setTransactionId(123);
        amexResponse.setUpdatedOn(mock(Timestamp.class));
        when(this.aMEXResponseRepository.save((AMEXResponse) any())).thenReturn(amexResponse);

        AMEXRequest amexRequest = new AMEXRequest();
        amexRequest.setAmount(10.0f);
        amexRequest.setBankTransactionId(123);
        amexRequest.setCardNumber("42");
        amexRequest.setCardType("Card Type");
        amexRequest.setCreatedOn(mock(Timestamp.class));
        amexRequest.setCreditorId("42");
        amexRequest.setCreditorName("Creditor Name");
        amexRequest.setCvv(1);
        amexRequest.setUpdatedOn(mock(Timestamp.class));
        when(this.aMEXRequestRepository.save((AMEXRequest) any())).thenReturn(amexRequest);

        AMEXRequestModel amexRequestModel = new AMEXRequestModel();
        amexRequestModel.setAmount(10.0f);
        amexRequestModel.setCardNumber("42");
        amexRequestModel.setCountry("GB");
        amexRequestModel.setCreditorId("42");
        amexRequestModel.setCreditorName("Creditor Name");
        amexRequestModel.setCurrency("GBP");
        amexRequestModel.setCvv(1);
        amexRequestModel.setEmail("jane.doe@example.org");
        amexRequestModel.setExpiryMonth(04);
        amexRequestModel.setExpiryYear(22);
        amexRequestModel.setNameOnCard("Name On Card");
        amexRequestModel.setTransactionId(123);
        amexRequestModel.setTypeOfCard("Type Of Card");
        amexRequestModel.setUserId(123);
        amexRequestModel.setZipcode("21654");
        AMEXResponseModel actualMakePaymentResult = this.aMEXPaymentServiceImpl.makePayment(amexRequestModel);
        assertEquals(123, actualMakePaymentResult.getTransactionId());
        assertEquals("Success", actualMakePaymentResult.getStatusDescription());
        assertEquals(200, actualMakePaymentResult.getStatusCode());
        verify(this.bankRepository).getUserByCardNumber((String) any());
        verify(this.bankRepository).save((Bank) any());
        verify(this.aMEXResponseRepository).save((AMEXResponse) any());
        verify(this.aMEXRequestRepository).save((AMEXRequest) any());
    }

    @Test
    void testMakePayment2() {
        Bank bank = new Bank();
        bank.setAddress("42 Main St");
        bank.setAmount(10.0f);
        bank.setCard_number("42");
        bank.setCity("Oxford");
        bank.setCvv(200);
        bank.setEmail("jane.doe@example.org");
        bank.setExpiryMonth(1);
        bank.setExpiryYear(1);
        bank.setFullName("Dr Jane Doe");
        bank.setId(1);
        bank.setState("MD");
        bank.setZipcode("21654");

        Bank bank1 = new Bank();
        bank1.setAddress("42 Main St");
        bank1.setAmount(10.0f);
        bank1.setCard_number("42");
        bank1.setCity("Oxford");
        bank1.setCvv(1);
        bank1.setEmail("jane.doe@example.org");
        bank1.setExpiryMonth(1);
        bank1.setExpiryYear(1);
        bank1.setFullName("Dr Jane Doe");
        bank1.setId(1);
        bank1.setState("MD");
        bank1.setZipcode("21654");
        when(this.bankRepository.save((Bank) any())).thenReturn(bank1);
        when(this.bankRepository.getUserByCardNumber((String) any())).thenReturn(bank);

        AMEXResponse amexResponse = new AMEXResponse();
        amexResponse.setCreatedOn(mock(Timestamp.class));
        amexResponse.setResponseId(123);
        amexResponse.setStatusCode(1);
        amexResponse.setStatusDescription("Status Description");
        amexResponse.setTransactionId(123);
        amexResponse.setUpdatedOn(mock(Timestamp.class));
        when(this.aMEXResponseRepository.save((AMEXResponse) any())).thenReturn(amexResponse);

        AMEXRequest amexRequest = new AMEXRequest();
        amexRequest.setAmount(10.0f);
        amexRequest.setBankTransactionId(123);
        amexRequest.setCardNumber("42");
        amexRequest.setCardType("Card Type");
        amexRequest.setCreatedOn(mock(Timestamp.class));
        amexRequest.setCreditorId("42");
        amexRequest.setCreditorName("Creditor Name");
        amexRequest.setCvv(1);
        amexRequest.setUpdatedOn(mock(Timestamp.class));
        when(this.aMEXRequestRepository.save((AMEXRequest) any())).thenReturn(amexRequest);

        AMEXRequestModel amexRequestModel = new AMEXRequestModel();
        amexRequestModel.setAmount(10.0f);
        amexRequestModel.setCardNumber("42");
        amexRequestModel.setCountry("GB");
        amexRequestModel.setCreditorId("42");
        amexRequestModel.setCreditorName("Creditor Name");
        amexRequestModel.setCurrency("GBP");
        amexRequestModel.setCvv(1);
        amexRequestModel.setEmail("jane.doe@example.org");
        amexRequestModel.setExpiryMonth(1);
        amexRequestModel.setExpiryYear(1);
        amexRequestModel.setNameOnCard("Name On Card");
        amexRequestModel.setTransactionId(123);
        amexRequestModel.setTypeOfCard("Type Of Card");
        amexRequestModel.setUserId(123);
        amexRequestModel.setZipcode("21654");
        AMEXResponseModel actualMakePaymentResult = this.aMEXPaymentServiceImpl.makePayment(amexRequestModel);
        assertEquals(123, actualMakePaymentResult.getTransactionId());
        assertEquals("Failure", actualMakePaymentResult.getStatusDescription());
        assertEquals(400, actualMakePaymentResult.getStatusCode());
        verify(this.bankRepository).getUserByCardNumber((String) any());
        verify(this.aMEXResponseRepository).save((AMEXResponse) any());
        verify(this.aMEXRequestRepository).save((AMEXRequest) any());
    }

    @Test
    void testMakePayment3() {
        Bank bank = new Bank();
        bank.setAddress("42 Main St");
        bank.setAmount(10.0f);
        bank.setCard_number("42");
        bank.setCity("Oxford");
        bank.setCvv(1);
        bank.setEmail("jane.doe@example.org");
        bank.setExpiryMonth(0);
        bank.setExpiryYear(1);
        bank.setFullName("Dr Jane Doe");
        bank.setId(1);
        bank.setState("MD");
        bank.setZipcode("21654");

        Bank bank1 = new Bank();
        bank1.setAddress("42 Main St");
        bank1.setAmount(10.0f);
        bank1.setCard_number("42");
        bank1.setCity("Oxford");
        bank1.setCvv(1);
        bank1.setEmail("jane.doe@example.org");
        bank1.setExpiryMonth(1);
        bank1.setExpiryYear(1);
        bank1.setFullName("Dr Jane Doe");
        bank1.setId(1);
        bank1.setState("MD");
        bank1.setZipcode("21654");
        when(this.bankRepository.save((Bank) any())).thenReturn(bank1);
        when(this.bankRepository.getUserByCardNumber((String) any())).thenReturn(bank);

        AMEXResponse amexResponse = new AMEXResponse();
        amexResponse.setCreatedOn(mock(Timestamp.class));
        amexResponse.setResponseId(123);
        amexResponse.setStatusCode(1);
        amexResponse.setStatusDescription("Status Description");
        amexResponse.setTransactionId(123);
        amexResponse.setUpdatedOn(mock(Timestamp.class));
        when(this.aMEXResponseRepository.save((AMEXResponse) any())).thenReturn(amexResponse);

        AMEXRequest amexRequest = new AMEXRequest();
        amexRequest.setAmount(10.0f);
        amexRequest.setBankTransactionId(123);
        amexRequest.setCardNumber("42");
        amexRequest.setCardType("Card Type");
        amexRequest.setCreatedOn(mock(Timestamp.class));
        amexRequest.setCreditorId("42");
        amexRequest.setCreditorName("Creditor Name");
        amexRequest.setCvv(1);
        amexRequest.setUpdatedOn(mock(Timestamp.class));
        when(this.aMEXRequestRepository.save((AMEXRequest) any())).thenReturn(amexRequest);

        AMEXRequestModel amexRequestModel = new AMEXRequestModel();
        amexRequestModel.setAmount(10.0f);
        amexRequestModel.setCardNumber("42");
        amexRequestModel.setCountry("GB");
        amexRequestModel.setCreditorId("42");
        amexRequestModel.setCreditorName("Creditor Name");
        amexRequestModel.setCurrency("GBP");
        amexRequestModel.setCvv(1);
        amexRequestModel.setEmail("jane.doe@example.org");
        amexRequestModel.setExpiryMonth(1);
        amexRequestModel.setExpiryYear(1);
        amexRequestModel.setNameOnCard("Name On Card");
        amexRequestModel.setTransactionId(123);
        amexRequestModel.setTypeOfCard("Type Of Card");
        amexRequestModel.setUserId(123);
        amexRequestModel.setZipcode("21654");
        AMEXResponseModel actualMakePaymentResult = this.aMEXPaymentServiceImpl.makePayment(amexRequestModel);
        assertEquals(123, actualMakePaymentResult.getTransactionId());
        assertEquals("Failure", actualMakePaymentResult.getStatusDescription());
        assertEquals(400, actualMakePaymentResult.getStatusCode());
        verify(this.bankRepository).getUserByCardNumber((String) any());
        verify(this.aMEXResponseRepository).save((AMEXResponse) any());
        verify(this.aMEXRequestRepository).save((AMEXRequest) any());
    }

    @Test
    void testMakePayment4() {
        Bank bank = new Bank();
        bank.setAddress("42 Main St");
        bank.setAmount(10.0f);
        bank.setCard_number("42");
        bank.setCity("Oxford");
        bank.setCvv(1);
        bank.setEmail("jane.doe@example.org");
        bank.setExpiryMonth(04);
        bank.setExpiryYear(22);
        bank.setFullName("Dr Jane Doe");
        bank.setId(1);
        bank.setState("MD");
        bank.setZipcode("21654");

        Bank bank1 = new Bank();
        bank1.setAddress("42 Main St");
        bank1.setAmount(10.0f);
        bank1.setCard_number("42");
        bank1.setCity("Oxford");
        bank1.setCvv(1);
        bank1.setEmail("jane.doe@example.org");
        bank1.setExpiryMonth(04);
        bank1.setExpiryYear(22);
        bank1.setFullName("Dr Jane Doe");
        bank1.setId(1);
        bank1.setState("MD");
        bank1.setZipcode("21654");
        when(this.bankRepository.save((Bank) any())).thenReturn(bank1);
        when(this.bankRepository.getUserByCardNumber((String) any())).thenReturn(bank);

        AMEXResponse amexResponse = new AMEXResponse();
        amexResponse.setCreatedOn(mock(Timestamp.class));
        amexResponse.setResponseId(123);
        amexResponse.setStatusCode(1);
        amexResponse.setStatusDescription("Status Description");
        amexResponse.setTransactionId(123);
        amexResponse.setUpdatedOn(mock(Timestamp.class));
        when(this.aMEXResponseRepository.save((AMEXResponse) any())).thenReturn(amexResponse);

        AMEXRequest amexRequest = new AMEXRequest();
        amexRequest.setAmount(10.0f);
        amexRequest.setBankTransactionId(123);
        amexRequest.setCardNumber("42");
        amexRequest.setCardType("Card Type");
        amexRequest.setCreatedOn(mock(Timestamp.class));
        amexRequest.setCreditorId("42");
        amexRequest.setCreditorName("Creditor Name");
        amexRequest.setCvv(1);
        amexRequest.setUpdatedOn(mock(Timestamp.class));
        when(this.aMEXRequestRepository.save((AMEXRequest) any())).thenReturn(amexRequest);

        AMEXRequestModel amexRequestModel = new AMEXRequestModel();
        amexRequestModel.setAmount(10.0f);
        amexRequestModel.setCardNumber("42");
        amexRequestModel.setCountry("GB");
        amexRequestModel.setCreditorId("42");
        amexRequestModel.setCreditorName("Creditor Name");
        amexRequestModel.setCurrency("GBP");
        amexRequestModel.setCvv(1);
        amexRequestModel.setEmail("jane.doe@example.org");
        amexRequestModel.setExpiryMonth(04);
        amexRequestModel.setExpiryYear(22);
        amexRequestModel.setNameOnCard("Name On Card");
        amexRequestModel.setTransactionId(123);
        amexRequestModel.setTypeOfCard("Type Of Card");
        amexRequestModel.setUserId(123);
        amexRequestModel.setZipcode("21654");
        AMEXResponseModel actualMakePaymentResult = this.aMEXPaymentServiceImpl.makePayment(amexRequestModel);
        assertEquals(123, actualMakePaymentResult.getTransactionId());
        assertEquals("Success", actualMakePaymentResult.getStatusDescription());
        assertEquals(200, actualMakePaymentResult.getStatusCode());
        verify(this.bankRepository).getUserByCardNumber((String) any());
        verify(this.bankRepository).save((Bank) any());
        verify(this.aMEXResponseRepository).save((AMEXResponse) any());
        verify(this.aMEXRequestRepository).save((AMEXRequest) any());
    }

    @Test
    void testMakePayment5() {
        Bank bank = new Bank();
        bank.setAddress("42 Main St");
        bank.setAmount(10.0f);
        bank.setCard_number("42");
        bank.setCity("Oxford");
        bank.setCvv(1);
        bank.setEmail("jane.doe@example.org");
        bank.setExpiryMonth(1);
        bank.setExpiryYear(0);
        bank.setFullName("Dr Jane Doe");
        bank.setId(1);
        bank.setState("MD");
        bank.setZipcode("21654");

        Bank bank1 = new Bank();
        bank1.setAddress("42 Main St");
        bank1.setAmount(10.0f);
        bank1.setCard_number("42");
        bank1.setCity("Oxford");
        bank1.setCvv(1);
        bank1.setEmail("jane.doe@example.org");
        bank1.setExpiryMonth(1);
        bank1.setExpiryYear(1);
        bank1.setFullName("Dr Jane Doe");
        bank1.setId(1);
        bank1.setState("MD");
        bank1.setZipcode("21654");
        when(this.bankRepository.save((Bank) any())).thenReturn(bank1);
        when(this.bankRepository.getUserByCardNumber((String) any())).thenReturn(bank);

        AMEXResponse amexResponse = new AMEXResponse();
        amexResponse.setCreatedOn(mock(Timestamp.class));
        amexResponse.setResponseId(123);
        amexResponse.setStatusCode(1);
        amexResponse.setStatusDescription("Status Description");
        amexResponse.setTransactionId(123);
        amexResponse.setUpdatedOn(mock(Timestamp.class));
        when(this.aMEXResponseRepository.save((AMEXResponse) any())).thenReturn(amexResponse);

        AMEXRequest amexRequest = new AMEXRequest();
        amexRequest.setAmount(10.0f);
        amexRequest.setBankTransactionId(123);
        amexRequest.setCardNumber("42");
        amexRequest.setCardType("Card Type");
        amexRequest.setCreatedOn(mock(Timestamp.class));
        amexRequest.setCreditorId("42");
        amexRequest.setCreditorName("Creditor Name");
        amexRequest.setCvv(1);
        amexRequest.setUpdatedOn(mock(Timestamp.class));
        when(this.aMEXRequestRepository.save((AMEXRequest) any())).thenReturn(amexRequest);

        AMEXRequestModel amexRequestModel = new AMEXRequestModel();
        amexRequestModel.setAmount(10.0f);
        amexRequestModel.setCardNumber("42");
        amexRequestModel.setCountry("GB");
        amexRequestModel.setCreditorId("42");
        amexRequestModel.setCreditorName("Creditor Name");
        amexRequestModel.setCurrency("GBP");
        amexRequestModel.setCvv(1);
        amexRequestModel.setEmail("jane.doe@example.org");
        amexRequestModel.setExpiryMonth(1);
        amexRequestModel.setExpiryYear(1);
        amexRequestModel.setNameOnCard("Name On Card");
        amexRequestModel.setTransactionId(123);
        amexRequestModel.setTypeOfCard("Type Of Card");
        amexRequestModel.setUserId(123);
        amexRequestModel.setZipcode("21654");
        AMEXResponseModel actualMakePaymentResult = this.aMEXPaymentServiceImpl.makePayment(amexRequestModel);
        assertEquals(123, actualMakePaymentResult.getTransactionId());
        assertEquals("Failure", actualMakePaymentResult.getStatusDescription());
        assertEquals(400, actualMakePaymentResult.getStatusCode());
        verify(this.bankRepository).getUserByCardNumber((String) any());
        verify(this.aMEXResponseRepository).save((AMEXResponse) any());
        verify(this.aMEXRequestRepository).save((AMEXRequest) any());
    }

    @Test
    void testPay() {
        Bank bank = new Bank();
        bank.setAddress("42 Main St");
        bank.setAmount(10.0f);
        bank.setCard_number("42");
        bank.setCity("Oxford");
        bank.setCvv(1);
        bank.setEmail("jane.doe@example.org");
        bank.setExpiryMonth(04);
        bank.setExpiryYear(22);
        bank.setFullName("Dr Jane Doe");
        bank.setId(1);
        bank.setState("MD");
        bank.setZipcode("21654");

        Bank bank1 = new Bank();
        bank1.setAddress("42 Main St");
        bank1.setAmount(10.0f);
        bank1.setCard_number("42");
        bank1.setCity("Oxford");
        bank1.setCvv(1);
        bank1.setEmail("jane.doe@example.org");
        bank1.setExpiryMonth(1);
        bank1.setExpiryYear(1);
        bank1.setFullName("Dr Jane Doe");
        bank1.setId(1);
        bank1.setState("MD");
        bank1.setZipcode("21654");
        when(this.bankRepository.save((Bank) any())).thenReturn(bank1);
        when(this.bankRepository.getUserByCardNumber((String) any())).thenReturn(bank);

        AMEXRequestModel amexRequestModel = new AMEXRequestModel();
        amexRequestModel.setAmount(10.0f);
        amexRequestModel.setCardNumber("42");
        amexRequestModel.setCountry("GB");
        amexRequestModel.setCreditorId("42");
        amexRequestModel.setCreditorName("Creditor Name");
        amexRequestModel.setCurrency("GBP");
        amexRequestModel.setCvv(1);
        amexRequestModel.setEmail("jane.doe@example.org");
        amexRequestModel.setExpiryMonth(04);
        amexRequestModel.setExpiryYear(22);
        amexRequestModel.setNameOnCard("Name On Card");
        amexRequestModel.setTransactionId(123);
        amexRequestModel.setTypeOfCard("Type Of Card");
        amexRequestModel.setUserId(123);
        amexRequestModel.setZipcode("21654");
        assertTrue(this.aMEXPaymentServiceImpl.pay(amexRequestModel));
        verify(this.bankRepository).getUserByCardNumber((String) any());
        verify(this.bankRepository).save((Bank) any());
    }

    @Test
    void testPay2() {
        Bank bank = new Bank();
        bank.setAddress("42 Main St");
        bank.setAmount(10.0f);
        bank.setCard_number("42");
        bank.setCity("Oxford");
        bank.setCvv(0);
        bank.setEmail("jane.doe@example.org");
        bank.setExpiryMonth(1);
        bank.setExpiryYear(1);
        bank.setFullName("Dr Jane Doe");
        bank.setId(1);
        bank.setState("MD");
        bank.setZipcode("21654");

        Bank bank1 = new Bank();
        bank1.setAddress("42 Main St");
        bank1.setAmount(10.0f);
        bank1.setCard_number("42");
        bank1.setCity("Oxford");
        bank1.setCvv(1);
        bank1.setEmail("jane.doe@example.org");
        bank1.setExpiryMonth(1);
        bank1.setExpiryYear(1);
        bank1.setFullName("Dr Jane Doe");
        bank1.setId(1);
        bank1.setState("MD");
        bank1.setZipcode("21654");
        when(this.bankRepository.save((Bank) any())).thenReturn(bank1);
        when(this.bankRepository.getUserByCardNumber((String) any())).thenReturn(bank);

        AMEXRequestModel amexRequestModel = new AMEXRequestModel();
        amexRequestModel.setAmount(10.0f);
        amexRequestModel.setCardNumber("42");
        amexRequestModel.setCountry("GB");
        amexRequestModel.setCreditorId("42");
        amexRequestModel.setCreditorName("Creditor Name");
        amexRequestModel.setCurrency("GBP");
        amexRequestModel.setCvv(1);
        amexRequestModel.setEmail("jane.doe@example.org");
        amexRequestModel.setExpiryMonth(1);
        amexRequestModel.setExpiryYear(1);
        amexRequestModel.setNameOnCard("Name On Card");
        amexRequestModel.setTransactionId(123);
        amexRequestModel.setTypeOfCard("Type Of Card");
        amexRequestModel.setUserId(123);
        amexRequestModel.setZipcode("21654");
        assertFalse(this.aMEXPaymentServiceImpl.pay(amexRequestModel));
        verify(this.bankRepository).getUserByCardNumber((String) any());
    }

    @Test
    void testPay3() {
        Bank bank = new Bank();
        bank.setAddress("42 Main St");
        bank.setAmount(10.0f);
        bank.setCard_number("42");
        bank.setCity("Oxford");
        bank.setCvv(1);
        bank.setEmail("jane.doe@example.org");
        bank.setExpiryMonth(0);
        bank.setExpiryYear(1);
        bank.setFullName("Dr Jane Doe");
        bank.setId(1);
        bank.setState("MD");
        bank.setZipcode("21654");

        Bank bank1 = new Bank();
        bank1.setAddress("42 Main St");
        bank1.setAmount(10.0f);
        bank1.setCard_number("42");
        bank1.setCity("Oxford");
        bank1.setCvv(1);
        bank1.setEmail("jane.doe@example.org");
        bank1.setExpiryMonth(1);
        bank1.setExpiryYear(1);
        bank1.setFullName("Dr Jane Doe");
        bank1.setId(1);
        bank1.setState("MD");
        bank1.setZipcode("21654");
        when(this.bankRepository.save((Bank) any())).thenReturn(bank1);
        when(this.bankRepository.getUserByCardNumber((String) any())).thenReturn(bank);

        AMEXRequestModel amexRequestModel = new AMEXRequestModel();
        amexRequestModel.setAmount(10.0f);
        amexRequestModel.setCardNumber("42");
        amexRequestModel.setCountry("GB");
        amexRequestModel.setCreditorId("42");
        amexRequestModel.setCreditorName("Creditor Name");
        amexRequestModel.setCurrency("GBP");
        amexRequestModel.setCvv(1);
        amexRequestModel.setEmail("jane.doe@example.org");
        amexRequestModel.setExpiryMonth(1);
        amexRequestModel.setExpiryYear(1);
        amexRequestModel.setNameOnCard("Name On Card");
        amexRequestModel.setTransactionId(123);
        amexRequestModel.setTypeOfCard("Type Of Card");
        amexRequestModel.setUserId(123);
        amexRequestModel.setZipcode("21654");
        assertFalse(this.aMEXPaymentServiceImpl.pay(amexRequestModel));
        verify(this.bankRepository).getUserByCardNumber((String) any());
    }

    @Test
    void testPay4() {
        Bank bank = new Bank();
        bank.setAddress("42 Main St");
        bank.setAmount(10.0f);
        bank.setCard_number("42");
        bank.setCity("Oxford");
        bank.setCvv(1);
        bank.setEmail("jane.doe@example.org");
        bank.setExpiryMonth(1);
        bank.setExpiryYear(0);
        bank.setFullName("Dr Jane Doe");
        bank.setId(1);
        bank.setState("MD");
        bank.setZipcode("21654");

        Bank bank1 = new Bank();
        bank1.setAddress("42 Main St");
        bank1.setAmount(10.0f);
        bank1.setCard_number("42");
        bank1.setCity("Oxford");
        bank1.setCvv(1);
        bank1.setEmail("jane.doe@example.org");
        bank1.setExpiryMonth(1);
        bank1.setExpiryYear(1);
        bank1.setFullName("Dr Jane Doe");
        bank1.setId(1);
        bank1.setState("MD");
        bank1.setZipcode("21654");
        when(this.bankRepository.save((Bank) any())).thenReturn(bank1);
        when(this.bankRepository.getUserByCardNumber((String) any())).thenReturn(bank);

        AMEXRequestModel amexRequestModel = new AMEXRequestModel();
        amexRequestModel.setAmount(10.0f);
        amexRequestModel.setCardNumber("42");
        amexRequestModel.setCountry("GB");
        amexRequestModel.setCreditorId("42");
        amexRequestModel.setCreditorName("Creditor Name");
        amexRequestModel.setCurrency("GBP");
        amexRequestModel.setCvv(1);
        amexRequestModel.setEmail("jane.doe@example.org");
        amexRequestModel.setExpiryMonth(1);
        amexRequestModel.setExpiryYear(1);
        amexRequestModel.setNameOnCard("Name On Card");
        amexRequestModel.setTransactionId(123);
        amexRequestModel.setTypeOfCard("Type Of Card");
        amexRequestModel.setUserId(123);
        amexRequestModel.setZipcode("21654");
        assertFalse(this.aMEXPaymentServiceImpl.pay(amexRequestModel));
        verify(this.bankRepository).getUserByCardNumber((String) any());
    }

    @Test
    void testPay5() {
        Bank bank = new Bank();
        bank.setAddress("42 Main St");
        bank.setAmount(10.0f);
        bank.setCard_number("42");
        bank.setCity("Oxford");
        bank.setCvv(1);
        bank.setEmail("jane.doe@example.org");
        bank.setExpiryMonth(04);
        bank.setExpiryYear(22);
        bank.setFullName("Dr Jane Doe");
        bank.setId(1);
        bank.setState("MD");
        bank.setZipcode("21654");

        Bank bank1 = new Bank();
        bank1.setAddress("42 Main St");
        bank1.setAmount(10.0f);
        bank1.setCard_number("42");
        bank1.setCity("Oxford");
        bank1.setCvv(1);
        bank1.setEmail("jane.doe@example.org");
        bank1.setExpiryMonth(1);
        bank1.setExpiryYear(1);
        bank1.setFullName("Dr Jane Doe");
        bank1.setId(1);
        bank1.setState("MD");
        bank1.setZipcode("21654");
        when(this.bankRepository.save((Bank) any())).thenReturn(bank1);
        when(this.bankRepository.getUserByCardNumber((String) any())).thenReturn(bank);

        AMEXRequestModel amexRequestModel = new AMEXRequestModel();
        amexRequestModel.setAmount(10.0f);
        amexRequestModel.setCardNumber("42");
        amexRequestModel.setCountry("GB");
        amexRequestModel.setCreditorId("42");
        amexRequestModel.setCreditorName("Creditor Name");
        amexRequestModel.setCurrency("GBP");
        amexRequestModel.setCvv(1);
        amexRequestModel.setEmail("jane.doe@example.org");
        amexRequestModel.setExpiryMonth(04);
        amexRequestModel.setExpiryYear(22);
        amexRequestModel.setNameOnCard("Name On Card");
        amexRequestModel.setTransactionId(123);
        amexRequestModel.setTypeOfCard("Type Of Card");
        amexRequestModel.setUserId(123);
        amexRequestModel.setZipcode("21654");
        assertTrue(this.aMEXPaymentServiceImpl.pay(amexRequestModel));
        verify(this.bankRepository).getUserByCardNumber((String) any());
        verify(this.bankRepository).save((Bank) any());
    }
}

