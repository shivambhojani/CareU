package com.group6.careu.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group6.careu.entity.Transactions;
import com.group6.careu.model.Response.AMEXResponseModel;
import com.group6.careu.model.Response.GenericResponseModel;
import com.group6.careu.model.TransactionsModel;
import com.group6.careu.repository.TransactionsRepository;

import java.sql.Timestamp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
/**
 * Created by Bijitashya on 04, 2022
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class BankServiceImplTest {
    @Autowired
    private BankServiceImpl bankServiceImpl;

    @MockBean
    private TransactionsRepository transactionsRepository;

    @Test
    void testUpdateBalance() {
        assertFalse(this.bankServiceImpl.updateBalance(1));
    }


    @Test
    void testSaveTransaction() {
        Transactions transactions = new Transactions();
        transactions.setAmount(10.0f);
        transactions.setBankReferenceCode(1);
        transactions.setCardNumber("42");
        transactions.setCardType("Card Type");
        transactions.setCreatedOn(mock(Timestamp.class));
        transactions.setFullName("Dr Jane Doe");
        transactions.setStatusCode(1);
        transactions.setStatusDescription("Status Description");
        transactions.setTransactionStatus("Transaction Status");
        transactions.setTransaction_id(1);
        transactions.setUpdatedOn(mock(Timestamp.class));
        when(this.transactionsRepository.save((Transactions) any())).thenReturn(transactions);

        Transactions transactions1 = new Transactions();
        transactions1.setAmount(10.0f);
        transactions1.setBankReferenceCode(1);
        transactions1.setCardNumber("42");
        transactions1.setCardType("Card Type");
        transactions1.setCreatedOn(mock(Timestamp.class));
        transactions1.setFullName("Dr Jane Doe");
        transactions1.setStatusCode(1);
        transactions1.setStatusDescription("Status Description");
        transactions1.setTransactionStatus("Transaction Status");
        transactions1.setTransaction_id(1);
        transactions1.setUpdatedOn(mock(Timestamp.class));
        assertSame(transactions, this.bankServiceImpl.saveTransaction(transactions1));
        verify(this.transactionsRepository).save((Transactions) any());
        assertEquals("42", transactions1.getCardNumber());
    }

    @Test
    void testSaveTransaction2() {
        Transactions transactions = new Transactions();
        transactions.setAmount(10.0f);
        transactions.setBankReferenceCode(1);
        transactions.setCardNumber("42");
        transactions.setCardType("Card Type");
        transactions.setCreatedOn(mock(Timestamp.class));
        transactions.setFullName("Dr Jane Doe");
        transactions.setStatusCode(1);
        transactions.setStatusDescription("Status Description");
        transactions.setTransactionStatus("Transaction Status");
        transactions.setTransaction_id(1);
        transactions.setUpdatedOn(mock(Timestamp.class));
        when(this.transactionsRepository.save((Transactions) any())).thenReturn(transactions);

        Transactions transactions1 = new Transactions();
        transactions1.setAmount(10.0f);
        transactions1.setBankReferenceCode(1);
        transactions1.setCardNumber("Masked Card Number: ");
        transactions1.setCardType("Card Type");
        transactions1.setCreatedOn(mock(Timestamp.class));
        transactions1.setFullName("Dr Jane Doe");
        transactions1.setStatusCode(1);
        transactions1.setStatusDescription("Status Description");
        transactions1.setTransactionStatus("Transaction Status");
        transactions1.setTransaction_id(1);
        transactions1.setUpdatedOn(mock(Timestamp.class));
        assertSame(transactions, this.bankServiceImpl.saveTransaction(transactions1));
        verify(this.transactionsRepository).save((Transactions) any());
        assertEquals("################er: ", transactions1.getCardNumber());
    }

    @Test
    void testUpdateTransaction() {
        Transactions transactions = new Transactions();
        transactions.setAmount(10.0f);
        transactions.setBankReferenceCode(1);
        transactions.setCardNumber("42");
        transactions.setCardType("Card Type");
        transactions.setCreatedOn(mock(Timestamp.class));
        transactions.setFullName("Dr Jane Doe");
        transactions.setStatusCode(1);
        transactions.setStatusDescription("Status Description");
        transactions.setTransactionStatus("Transaction Status");
        transactions.setTransaction_id(1);
        transactions.setUpdatedOn(mock(Timestamp.class));

        Transactions transactions1 = new Transactions();
        transactions1.setAmount(10.0f);
        transactions1.setBankReferenceCode(1);
        transactions1.setCardNumber("42");
        transactions1.setCardType("Card Type");
        transactions1.setCreatedOn(mock(Timestamp.class));
        transactions1.setFullName("Dr Jane Doe");
        transactions1.setStatusCode(1);
        transactions1.setStatusDescription("Status Description");
        transactions1.setTransactionStatus("Transaction Status");
        transactions1.setTransaction_id(1);
        transactions1.setUpdatedOn(mock(Timestamp.class));
        when(this.transactionsRepository.save((Transactions) any())).thenReturn(transactions1);
        when(this.transactionsRepository.getTransactionById((Integer) any())).thenReturn(transactions);

        Transactions transactions2 = new Transactions();
        transactions2.setAmount(10.0f);
        transactions2.setBankReferenceCode(1);
        transactions2.setCardNumber("42");
        transactions2.setCardType("Card Type");
        transactions2.setCreatedOn(mock(Timestamp.class));
        transactions2.setFullName("Dr Jane Doe");
        transactions2.setStatusCode(1);
        transactions2.setStatusDescription("Status Description");
        transactions2.setTransactionStatus("Transaction Status");
        transactions2.setTransaction_id(1);
        transactions2.setUpdatedOn(mock(Timestamp.class));

        AMEXResponseModel amexResponseModel = new AMEXResponseModel();
        amexResponseModel.setResponseId(123);
        amexResponseModel.setStatusCode(1);
        amexResponseModel.setStatusDescription("Status Description");
        amexResponseModel.setTransactionId(123);
        this.bankServiceImpl.updateTransaction(transactions2, amexResponseModel);
        verify(this.transactionsRepository).getTransactionById((Integer) any());
        verify(this.transactionsRepository).save((Transactions) any());
        assertEquals("Failure", transactions2.getTransactionStatus());
    }

    @Test
    void testUpdateTransaction2() {
        Transactions transactions = new Transactions();
        transactions.setAmount(10.0f);
        transactions.setBankReferenceCode(1);
        transactions.setCardNumber("42");
        transactions.setCardType("Card Type");
        transactions.setCreatedOn(mock(Timestamp.class));
        transactions.setFullName("Dr Jane Doe");
        transactions.setStatusCode(1);
        transactions.setStatusDescription("Status Description");
        transactions.setTransactionStatus("Transaction Status");
        transactions.setTransaction_id(1);
        transactions.setUpdatedOn(mock(Timestamp.class));

        Transactions transactions1 = new Transactions();
        transactions1.setAmount(10.0f);
        transactions1.setBankReferenceCode(1);
        transactions1.setCardNumber("42");
        transactions1.setCardType("Card Type");
        transactions1.setCreatedOn(mock(Timestamp.class));
        transactions1.setFullName("Dr Jane Doe");
        transactions1.setStatusCode(1);
        transactions1.setStatusDescription("Status Description");
        transactions1.setTransactionStatus("Transaction Status");
        transactions1.setTransaction_id(1);
        transactions1.setUpdatedOn(mock(Timestamp.class));
        when(this.transactionsRepository.save((Transactions) any())).thenReturn(transactions1);
        when(this.transactionsRepository.getTransactionById((Integer) any())).thenReturn(transactions);

        Transactions transactions2 = new Transactions();
        transactions2.setAmount(10.0f);
        transactions2.setBankReferenceCode(1);
        transactions2.setCardNumber("42");
        transactions2.setCardType("Card Type");
        transactions2.setCreatedOn(mock(Timestamp.class));
        transactions2.setFullName("Dr Jane Doe");
        transactions2.setStatusCode(1);
        transactions2.setStatusDescription("Status Description");
        transactions2.setTransactionStatus("Transaction Status");
        transactions2.setTransaction_id(1);
        transactions2.setUpdatedOn(mock(Timestamp.class));

        AMEXResponseModel amexResponseModel = new AMEXResponseModel();
        amexResponseModel.setResponseId(123);
        amexResponseModel.setStatusCode(200);
        amexResponseModel.setStatusDescription("Status Description");
        amexResponseModel.setTransactionId(123);
        this.bankServiceImpl.updateTransaction(transactions2, amexResponseModel);
        verify(this.transactionsRepository).getTransactionById((Integer) any());
        verify(this.transactionsRepository).save((Transactions) any());
        assertEquals("Completed", transactions2.getTransactionStatus());
    }

    @Test
    void testUpdateTransaction3() {
        Transactions transactions = new Transactions();
        transactions.setAmount(10.0f);
        transactions.setBankReferenceCode(1);
        transactions.setCardNumber("42");
        transactions.setCardType("Card Type");
        transactions.setCreatedOn(mock(Timestamp.class));
        transactions.setFullName("Dr Jane Doe");
        transactions.setStatusCode(1);
        transactions.setStatusDescription("Status Description");
        transactions.setTransactionStatus("Transaction Status");
        transactions.setTransaction_id(1);
        transactions.setUpdatedOn(mock(Timestamp.class));

        Transactions transactions1 = new Transactions();
        transactions1.setAmount(10.0f);
        transactions1.setBankReferenceCode(1);
        transactions1.setCardNumber("42");
        transactions1.setCardType("Card Type");
        transactions1.setCreatedOn(mock(Timestamp.class));
        transactions1.setFullName("Dr Jane Doe");
        transactions1.setStatusCode(1);
        transactions1.setStatusDescription("Status Description");
        transactions1.setTransactionStatus("Transaction Status");
        transactions1.setTransaction_id(1);
        transactions1.setUpdatedOn(mock(Timestamp.class));
        when(this.transactionsRepository.save((Transactions) any())).thenReturn(transactions1);
        when(this.transactionsRepository.getTransactionById((Integer) any())).thenReturn(transactions);

        Transactions transactions2 = new Transactions();
        transactions2.setAmount(10.0f);
        transactions2.setBankReferenceCode(1);
        transactions2.setCardNumber("42");
        transactions2.setCardType("Card Type");
        transactions2.setCreatedOn(mock(Timestamp.class));
        transactions2.setFullName("Dr Jane Doe");
        transactions2.setStatusCode(1);
        transactions2.setStatusDescription("Status Description");
        transactions2.setTransactionStatus("Transaction Status");
        transactions2.setTransaction_id(1);
        transactions2.setUpdatedOn(mock(Timestamp.class));

        GenericResponseModel genericResponseModel = new GenericResponseModel();
        genericResponseModel.setResponseId(123);
        genericResponseModel.setStatusCode(1);
        genericResponseModel.setStatusDescription("Status Description");
        genericResponseModel.setTransactionId(123);
        this.bankServiceImpl.updateTransaction(transactions2, genericResponseModel);
        verify(this.transactionsRepository).getTransactionById((Integer) any());
        verify(this.transactionsRepository).save((Transactions) any());
        assertEquals("Failure", transactions2.getTransactionStatus());
    }

    @Test
    void testUpdateTransaction4() {
        Transactions transactions = new Transactions();
        transactions.setAmount(10.0f);
        transactions.setBankReferenceCode(1);
        transactions.setCardNumber("42");
        transactions.setCardType("Card Type");
        transactions.setCreatedOn(mock(Timestamp.class));
        transactions.setFullName("Dr Jane Doe");
        transactions.setStatusCode(1);
        transactions.setStatusDescription("Status Description");
        transactions.setTransactionStatus("Transaction Status");
        transactions.setTransaction_id(1);
        transactions.setUpdatedOn(mock(Timestamp.class));

        Transactions transactions1 = new Transactions();
        transactions1.setAmount(10.0f);
        transactions1.setBankReferenceCode(1);
        transactions1.setCardNumber("42");
        transactions1.setCardType("Card Type");
        transactions1.setCreatedOn(mock(Timestamp.class));
        transactions1.setFullName("Dr Jane Doe");
        transactions1.setStatusCode(1);
        transactions1.setStatusDescription("Status Description");
        transactions1.setTransactionStatus("Transaction Status");
        transactions1.setTransaction_id(1);
        transactions1.setUpdatedOn(mock(Timestamp.class));
        when(this.transactionsRepository.save((Transactions) any())).thenReturn(transactions1);
        when(this.transactionsRepository.getTransactionById((Integer) any())).thenReturn(transactions);

        Transactions transactions2 = new Transactions();
        transactions2.setAmount(10.0f);
        transactions2.setBankReferenceCode(1);
        transactions2.setCardNumber("42");
        transactions2.setCardType("Card Type");
        transactions2.setCreatedOn(mock(Timestamp.class));
        transactions2.setFullName("Dr Jane Doe");
        transactions2.setStatusCode(1);
        transactions2.setStatusDescription("Status Description");
        transactions2.setTransactionStatus("Transaction Status");
        transactions2.setTransaction_id(1);
        transactions2.setUpdatedOn(mock(Timestamp.class));

        GenericResponseModel genericResponseModel = new GenericResponseModel();
        genericResponseModel.setResponseId(123);
        genericResponseModel.setStatusCode(200);
        genericResponseModel.setStatusDescription("Status Description");
        genericResponseModel.setTransactionId(123);
        this.bankServiceImpl.updateTransaction(transactions2, genericResponseModel);
        verify(this.transactionsRepository).getTransactionById((Integer) any());
        verify(this.transactionsRepository).save((Transactions) any());
        assertEquals("Completed", transactions2.getTransactionStatus());
    }
}

