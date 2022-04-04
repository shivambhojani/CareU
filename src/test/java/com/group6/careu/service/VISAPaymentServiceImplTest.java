package com.group6.careu.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group6.careu.entity.Bank;
import com.group6.careu.entity.VISARequest;
import com.group6.careu.entity.VISAResponse;
import com.group6.careu.model.Request.VISARequestModel;
import com.group6.careu.model.Response.VISAResponseModel;
import com.group6.careu.repository.BankRepository;
import com.group6.careu.repository.TransactionsRepository;
import com.group6.careu.repository.VISARequestRepository;
import com.group6.careu.repository.VISAResponseRepository;

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
@ContextConfiguration(classes = {VISAPaymentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class VISAPaymentServiceImplTest {
    @MockBean
    private BankRepository bankRepository;

    @MockBean
    private TransactionsRepository transactionsRepository;

    @Autowired
    private VISAPaymentServiceImpl vISAPaymentServiceImpl;

    @MockBean
    private VISARequestRepository vISARequestRepository;

    @MockBean
    private VISAResponseRepository vISAResponseRepository;

    @Test
    void testMakePayment() {
        VISAResponse visaResponse = new VISAResponse();
        visaResponse.setCreatedOn(mock(Timestamp.class));
        visaResponse.setResponseId(123);
        visaResponse.setStatusCode(1);
        visaResponse.setStatusDescription("Status Description");
        visaResponse.setTransactionId(123);
        visaResponse.setUpdatedOn(mock(Timestamp.class));
        when(this.vISAResponseRepository.save((VISAResponse) any())).thenReturn(visaResponse);

        VISARequest visaRequest = new VISARequest();
        visaRequest.setAmount(10.0f);
        visaRequest.setBankTransactionId(123);
        visaRequest.setCardNumber("42");
        visaRequest.setCardType("Card Type");
        visaRequest.setCreatedOn(mock(Timestamp.class));
        visaRequest.setCvv(1);
        visaRequest.setUpdatedOn(mock(Timestamp.class));
        when(this.vISARequestRepository.save((VISARequest) any())).thenReturn(visaRequest);

        Bank bank = new Bank();
        bank.setAddress("42 Main St");
        bank.setAmount(10.0f);
        bank.setCard_number("42");
        bank.setCity("Oxford");
        bank.setCvv(1);
        bank.setEmail("doe@example.org");
        bank.setExpiryMonth(1);
        bank.setExpiryYear(1);
        bank.setFullName("Dr Jane Doe");
        bank.setId(1);
        bank.setState("MD");
        bank.setZipcode("21654");
        when(this.bankRepository.getUserByCardNumber((String) any())).thenReturn(bank);

        VISARequestModel visaRequestModel = new VISARequestModel();
        visaRequestModel.setAmount(10.0f);
        visaRequestModel.setCardNumber("42");
        visaRequestModel.setCountry("GB");
        visaRequestModel.setCurrency("GBP");
        visaRequestModel.setCvv(1);
        visaRequestModel.setEmail("doe@example.org");
        visaRequestModel.setExpiryMonth(1);
        visaRequestModel.setExpiryYear(1);
        visaRequestModel.setNameOnCard("Name On Card");
        visaRequestModel.setTransactionId(123);
        visaRequestModel.setTypeOfCard("Type Of Card");
        visaRequestModel.setUserId(123);
        visaRequestModel.setZipcode("21654");
        VISAResponseModel actualMakePaymentResult = this.vISAPaymentServiceImpl.makePayment(visaRequestModel);
        assertEquals(123, actualMakePaymentResult.getResponseId().intValue());
        assertEquals(123, actualMakePaymentResult.getTransactionId());
        assertEquals("Failure", actualMakePaymentResult.getStatusDescription());
        assertEquals(400, actualMakePaymentResult.getStatusCode());
        verify(this.vISAResponseRepository).save((VISAResponse) any());
        verify(this.vISARequestRepository).save((VISARequest) any());
        verify(this.bankRepository).getUserByCardNumber((String) any());
    }

    @Test
    void testMakePayment2() {
        VISAResponse visaResponse = new VISAResponse();
        visaResponse.setCreatedOn(mock(Timestamp.class));
        visaResponse.setResponseId(123);
        visaResponse.setStatusCode(1);
        visaResponse.setStatusDescription("Status Description");
        visaResponse.setTransactionId(123);
        visaResponse.setUpdatedOn(mock(Timestamp.class));
        when(this.vISAResponseRepository.save((VISAResponse) any())).thenReturn(visaResponse);

        VISARequest visaRequest = new VISARequest();
        visaRequest.setAmount(10.0f);
        visaRequest.setBankTransactionId(123);
        visaRequest.setCardNumber("42");
        visaRequest.setCardType("Card Type");
        visaRequest.setCreatedOn(mock(Timestamp.class));
        visaRequest.setCvv(1);
        visaRequest.setUpdatedOn(mock(Timestamp.class));
        when(this.vISARequestRepository.save((VISARequest) any())).thenReturn(visaRequest);

        Bank bank = new Bank();
        bank.setAddress("42 Main St");
        bank.setAmount(10.0f);
        bank.setCard_number("42");
        bank.setCity("Oxford");
        bank.setCvv(100);
        bank.setEmail("doe@example.org");
        bank.setExpiryMonth(1);
        bank.setExpiryYear(1);
        bank.setFullName("Dr Jane Doe");
        bank.setId(1);
        bank.setState("MD");
        bank.setZipcode("21654");
        when(this.bankRepository.getUserByCardNumber((String) any())).thenReturn(bank);

        VISARequestModel visaRequestModel = new VISARequestModel();
        visaRequestModel.setAmount(10.0f);
        visaRequestModel.setCardNumber("42");
        visaRequestModel.setCountry("GB");
        visaRequestModel.setCurrency("GBP");
        visaRequestModel.setCvv(1);
        visaRequestModel.setEmail("doe@example.org");
        visaRequestModel.setExpiryMonth(1);
        visaRequestModel.setExpiryYear(1);
        visaRequestModel.setNameOnCard("Name On Card");
        visaRequestModel.setTransactionId(123);
        visaRequestModel.setTypeOfCard("Type Of Card");
        visaRequestModel.setUserId(123);
        visaRequestModel.setZipcode("21654");
        VISAResponseModel actualMakePaymentResult = this.vISAPaymentServiceImpl.makePayment(visaRequestModel);
        assertEquals(123, actualMakePaymentResult.getResponseId().intValue());
        assertEquals(123, actualMakePaymentResult.getTransactionId());
        assertEquals("Failure", actualMakePaymentResult.getStatusDescription());
        assertEquals(400, actualMakePaymentResult.getStatusCode());
        verify(this.vISAResponseRepository).save((VISAResponse) any());
        verify(this.vISARequestRepository).save((VISARequest) any());
        verify(this.bankRepository).getUserByCardNumber((String) any());
    }

    @Test
    void testMakePayment3() {
        VISAResponse visaResponse = new VISAResponse();
        visaResponse.setCreatedOn(mock(Timestamp.class));
        visaResponse.setResponseId(123);
        visaResponse.setStatusCode(1);
        visaResponse.setStatusDescription("Status Description");
        visaResponse.setTransactionId(123);
        visaResponse.setUpdatedOn(mock(Timestamp.class));
        when(this.vISAResponseRepository.save((VISAResponse) any())).thenReturn(visaResponse);

        VISARequest visaRequest = new VISARequest();
        visaRequest.setAmount(10.0f);
        visaRequest.setBankTransactionId(123);
        visaRequest.setCardNumber("42");
        visaRequest.setCardType("Card Type");
        visaRequest.setCreatedOn(mock(Timestamp.class));
        visaRequest.setCvv(1);
        visaRequest.setUpdatedOn(mock(Timestamp.class));
        when(this.vISARequestRepository.save((VISARequest) any())).thenReturn(visaRequest);

        Bank bank = new Bank();
        bank.setAddress("42 Main St");
        bank.setAmount(10.0f);
        bank.setCard_number("42");
        bank.setCity("Oxford");
        bank.setCvv(1);
        bank.setEmail("doe@example.org");
        bank.setExpiryMonth(100);
        bank.setExpiryYear(1);
        bank.setFullName("Dr Jane Doe");
        bank.setId(1);
        bank.setState("MD");
        bank.setZipcode("21654");
        when(this.bankRepository.getUserByCardNumber((String) any())).thenReturn(bank);

        VISARequestModel visaRequestModel = new VISARequestModel();
        visaRequestModel.setAmount(10.0f);
        visaRequestModel.setCardNumber("42");
        visaRequestModel.setCountry("GB");
        visaRequestModel.setCurrency("GBP");
        visaRequestModel.setCvv(1);
        visaRequestModel.setEmail("doe@example.org");
        visaRequestModel.setExpiryMonth(1);
        visaRequestModel.setExpiryYear(1);
        visaRequestModel.setNameOnCard("Name On Card");
        visaRequestModel.setTransactionId(123);
        visaRequestModel.setTypeOfCard("Type Of Card");
        visaRequestModel.setUserId(123);
        visaRequestModel.setZipcode("21654");
        VISAResponseModel actualMakePaymentResult = this.vISAPaymentServiceImpl.makePayment(visaRequestModel);
        assertEquals(123, actualMakePaymentResult.getResponseId().intValue());
        assertEquals(123, actualMakePaymentResult.getTransactionId());
        assertEquals("Failure", actualMakePaymentResult.getStatusDescription());
        assertEquals(400, actualMakePaymentResult.getStatusCode());
        verify(this.vISAResponseRepository).save((VISAResponse) any());
        verify(this.vISARequestRepository).save((VISARequest) any());
        verify(this.bankRepository).getUserByCardNumber((String) any());
    }

    @Test
    void testMakePayment4() {
        VISAResponse visaResponse = new VISAResponse();
        visaResponse.setCreatedOn(mock(Timestamp.class));
        visaResponse.setResponseId(123);
        visaResponse.setStatusCode(1);
        visaResponse.setStatusDescription("Status Description");
        visaResponse.setTransactionId(123);
        visaResponse.setUpdatedOn(mock(Timestamp.class));
        when(this.vISAResponseRepository.save((VISAResponse) any())).thenReturn(visaResponse);

        VISARequest visaRequest = new VISARequest();
        visaRequest.setAmount(10.0f);
        visaRequest.setBankTransactionId(123);
        visaRequest.setCardNumber("42");
        visaRequest.setCardType("Card Type");
        visaRequest.setCreatedOn(mock(Timestamp.class));
        visaRequest.setCvv(1);
        visaRequest.setUpdatedOn(mock(Timestamp.class));
        when(this.vISARequestRepository.save((VISARequest) any())).thenReturn(visaRequest);

        Bank bank = new Bank();
        bank.setAddress("42 Main St");
        bank.setAmount(10.0f);
        bank.setCard_number("42");
        bank.setCity("Oxford");
        bank.setCvv(1);
        bank.setEmail("doe@example.org");
        bank.setExpiryMonth(1);
        bank.setExpiryYear(100);
        bank.setFullName("Dr Jane Doe");
        bank.setId(1);
        bank.setState("MD");
        bank.setZipcode("21654");
        when(this.bankRepository.getUserByCardNumber((String) any())).thenReturn(bank);

        VISARequestModel visaRequestModel = new VISARequestModel();
        visaRequestModel.setAmount(10.0f);
        visaRequestModel.setCardNumber("42");
        visaRequestModel.setCountry("GB");
        visaRequestModel.setCurrency("GBP");
        visaRequestModel.setCvv(1);
        visaRequestModel.setEmail("doe@example.org");
        visaRequestModel.setExpiryMonth(1);
        visaRequestModel.setExpiryYear(1);
        visaRequestModel.setNameOnCard("Name On Card");
        visaRequestModel.setTransactionId(123);
        visaRequestModel.setTypeOfCard("Type Of Card");
        visaRequestModel.setUserId(123);
        visaRequestModel.setZipcode("21654");
        VISAResponseModel actualMakePaymentResult = this.vISAPaymentServiceImpl.makePayment(visaRequestModel);
        assertEquals(123, actualMakePaymentResult.getResponseId().intValue());
        assertEquals(123, actualMakePaymentResult.getTransactionId());
        assertEquals("Failure", actualMakePaymentResult.getStatusDescription());
        assertEquals(400, actualMakePaymentResult.getStatusCode());
        verify(this.vISAResponseRepository).save((VISAResponse) any());
        verify(this.vISARequestRepository).save((VISARequest) any());
        verify(this.bankRepository).getUserByCardNumber((String) any());
    }

    @Test
    void testPay() {
        Bank bank = new Bank();
        bank.setAddress("42 Main St");
        bank.setAmount(10.0f);
        bank.setCard_number("42");
        bank.setCity("Oxford");
        bank.setCvv(1);
        bank.setEmail("doe@example.org");
        bank.setExpiryMonth(1);
        bank.setExpiryYear(1);
        bank.setFullName("Dr Jane Doe");
        bank.setId(1);
        bank.setState("MD");
        bank.setZipcode("21654");
        when(this.bankRepository.getUserByCardNumber((String) any())).thenReturn(bank);

        VISARequestModel visaRequestModel = new VISARequestModel();
        visaRequestModel.setAmount(10.0f);
        visaRequestModel.setCardNumber("42");
        visaRequestModel.setCountry("GB");
        visaRequestModel.setCurrency("GBP");
        visaRequestModel.setCvv(1);
        visaRequestModel.setEmail("doe@example.org");
        visaRequestModel.setExpiryMonth(1);
        visaRequestModel.setExpiryYear(1);
        visaRequestModel.setNameOnCard("Name On Card");
        visaRequestModel.setTransactionId(123);
        visaRequestModel.setTypeOfCard("Type Of Card");
        visaRequestModel.setUserId(123);
        visaRequestModel.setZipcode("21654");
        assertFalse(this.vISAPaymentServiceImpl.pay(visaRequestModel));
        verify(this.bankRepository).getUserByCardNumber((String) any());
    }

    @Test
    void testPay2() {
        Bank bank = new Bank();
        bank.setAddress("42 Main St");
        bank.setAmount(10.0f);
        bank.setCard_number("42");
        bank.setCity("Oxford");
        bank.setCvv(100);
        bank.setEmail("doe@example.org");
        bank.setExpiryMonth(1);
        bank.setExpiryYear(1);
        bank.setFullName("Dr Jane Doe");
        bank.setId(1);
        bank.setState("MD");
        bank.setZipcode("21654");
        when(this.bankRepository.getUserByCardNumber((String) any())).thenReturn(bank);

        VISARequestModel visaRequestModel = new VISARequestModel();
        visaRequestModel.setAmount(10.0f);
        visaRequestModel.setCardNumber("42");
        visaRequestModel.setCountry("GB");
        visaRequestModel.setCurrency("GBP");
        visaRequestModel.setCvv(1);
        visaRequestModel.setEmail("doe@example.org");
        visaRequestModel.setExpiryMonth(1);
        visaRequestModel.setExpiryYear(1);
        visaRequestModel.setNameOnCard("Name On Card");
        visaRequestModel.setTransactionId(123);
        visaRequestModel.setTypeOfCard("Type Of Card");
        visaRequestModel.setUserId(123);
        visaRequestModel.setZipcode("21654");
        assertFalse(this.vISAPaymentServiceImpl.pay(visaRequestModel));
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
        bank.setEmail("doe@example.org");
        bank.setExpiryMonth(100);
        bank.setExpiryYear(1);
        bank.setFullName("Dr Jane Doe");
        bank.setId(1);
        bank.setState("MD");
        bank.setZipcode("21654");
        when(this.bankRepository.getUserByCardNumber((String) any())).thenReturn(bank);

        VISARequestModel visaRequestModel = new VISARequestModel();
        visaRequestModel.setAmount(10.0f);
        visaRequestModel.setCardNumber("42");
        visaRequestModel.setCountry("GB");
        visaRequestModel.setCurrency("GBP");
        visaRequestModel.setCvv(1);
        visaRequestModel.setEmail("jane.doe@example.org");
        visaRequestModel.setExpiryMonth(1);
        visaRequestModel.setExpiryYear(1);
        visaRequestModel.setNameOnCard("Name On Card");
        visaRequestModel.setTransactionId(123);
        visaRequestModel.setTypeOfCard("Type Of Card");
        visaRequestModel.setUserId(123);
        visaRequestModel.setZipcode("21654");
        assertFalse(this.vISAPaymentServiceImpl.pay(visaRequestModel));
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
        bank.setEmail("doe@example.org");
        bank.setExpiryMonth(1);
        bank.setExpiryYear(100);
        bank.setFullName("Dr Jane Doe");
        bank.setId(1);
        bank.setState("MD");
        bank.setZipcode("21654");
        when(this.bankRepository.getUserByCardNumber((String) any())).thenReturn(bank);

        VISARequestModel visaRequestModel = new VISARequestModel();
        visaRequestModel.setAmount(10.0f);
        visaRequestModel.setCardNumber("42");
        visaRequestModel.setCountry("GB");
        visaRequestModel.setCurrency("GBP");
        visaRequestModel.setCvv(1);
        visaRequestModel.setEmail("doe@example.org");
        visaRequestModel.setExpiryMonth(1);
        visaRequestModel.setExpiryYear(1);
        visaRequestModel.setNameOnCard("Name On Card");
        visaRequestModel.setTransactionId(123);
        visaRequestModel.setTypeOfCard("Type Of Card");
        visaRequestModel.setUserId(123);
        visaRequestModel.setZipcode("21654");
        assertFalse(this.vISAPaymentServiceImpl.pay(visaRequestModel));
        verify(this.bankRepository).getUserByCardNumber((String) any());
    }
}

