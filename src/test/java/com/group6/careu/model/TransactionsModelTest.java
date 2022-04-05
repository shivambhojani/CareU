package com.group6.careu.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

class TransactionsModelTest {
    @Test
    void testCanEqual() {
        assertFalse((new TransactionsModel()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        TransactionsModel transactionsModel = new TransactionsModel();
        assertTrue(transactionsModel.canEqual(new TransactionsModel()));
    }

    @Test
    void testConstructor() {
        TransactionsModel actualTransactionsModel = new TransactionsModel();
        actualTransactionsModel.setAmount(10.0f);
        actualTransactionsModel.setBankReferenceCode("Bank Reference Code");
        actualTransactionsModel.setCardNumber("42");
        actualTransactionsModel.setCardType("Card Type");
        actualTransactionsModel.setCountry("GB");
        actualTransactionsModel.setCreatedOn(mock(Timestamp.class));
        actualTransactionsModel.setCvv(1);
        actualTransactionsModel.setEmail("jane.doe@example.org");
        actualTransactionsModel.setExpiryMonth(1);
        actualTransactionsModel.setExpiryYear(1);
        actualTransactionsModel.setFullName("Dr Jane Doe");
        actualTransactionsModel.setStatusCode(1);
        actualTransactionsModel.setStatusDescription("Status Description");
        actualTransactionsModel.setTransactionStatus("Transaction Status");
        actualTransactionsModel.setUpdatedOn(mock(Timestamp.class));
        actualTransactionsModel.setZipcode("21654");
        assertEquals(10.0f, actualTransactionsModel.getAmount());
        assertEquals("Bank Reference Code", actualTransactionsModel.getBankReferenceCode());
        assertEquals("42", actualTransactionsModel.getCardNumber());
        assertEquals("Card Type", actualTransactionsModel.getCardType());
        assertEquals("GB", actualTransactionsModel.getCountry());
        assertEquals(1, actualTransactionsModel.getCvv().intValue());
        assertEquals("jane.doe@example.org", actualTransactionsModel.getEmail());
        assertEquals(1, actualTransactionsModel.getExpiryMonth().intValue());
        assertEquals(1, actualTransactionsModel.getExpiryYear().intValue());
        assertEquals("Dr Jane Doe", actualTransactionsModel.getFullName());
        assertEquals(1, actualTransactionsModel.getStatusCode().intValue());
        assertEquals("Status Description", actualTransactionsModel.getStatusDescription());
        assertEquals("Transaction Status", actualTransactionsModel.getTransactionStatus());
        assertEquals("21654", actualTransactionsModel.getZipcode());
    }

    @Test
    void testConstructor2() {
        TransactionsModel actualTransactionsModel = new TransactionsModel("jane.doe@example.org", "GB", "21654",
                "Dr Jane Doe", "42", "Card Type", "Transaction Status", "Bank Reference Code", 10.0f, 1, "Status Description",
                1, 1, 1, mock(Timestamp.class), mock(Timestamp.class));
        actualTransactionsModel.setAmount(10.0f);
        actualTransactionsModel.setBankReferenceCode("Bank Reference Code");
        actualTransactionsModel.setCardNumber("42");
        actualTransactionsModel.setCardType("Card Type");
        actualTransactionsModel.setCountry("GB");
        actualTransactionsModel.setCreatedOn(mock(Timestamp.class));
        actualTransactionsModel.setCvv(1);
        actualTransactionsModel.setEmail("jane.doe@example.org");
        actualTransactionsModel.setExpiryMonth(1);
        actualTransactionsModel.setExpiryYear(1);
        actualTransactionsModel.setFullName("Dr Jane Doe");
        actualTransactionsModel.setStatusCode(1);
        actualTransactionsModel.setStatusDescription("Status Description");
        actualTransactionsModel.setTransactionStatus("Transaction Status");
        actualTransactionsModel.setUpdatedOn(mock(Timestamp.class));
        actualTransactionsModel.setZipcode("21654");
        assertEquals(10.0f, actualTransactionsModel.getAmount());
        assertEquals("Bank Reference Code", actualTransactionsModel.getBankReferenceCode());
        assertEquals("42", actualTransactionsModel.getCardNumber());
        assertEquals("Card Type", actualTransactionsModel.getCardType());
        assertEquals("GB", actualTransactionsModel.getCountry());
        assertEquals(1, actualTransactionsModel.getCvv().intValue());
        assertEquals("jane.doe@example.org", actualTransactionsModel.getEmail());
        assertEquals(1, actualTransactionsModel.getExpiryMonth().intValue());
        assertEquals(1, actualTransactionsModel.getExpiryYear().intValue());
        assertEquals("Dr Jane Doe", actualTransactionsModel.getFullName());
        assertEquals(1, actualTransactionsModel.getStatusCode().intValue());
        assertEquals("Status Description", actualTransactionsModel.getStatusDescription());
        assertEquals("Transaction Status", actualTransactionsModel.getTransactionStatus());
        assertEquals("21654", actualTransactionsModel.getZipcode());
    }

    @Test
    void testEquals() {
        assertNotEquals(new TransactionsModel(), null);
        assertNotEquals(new TransactionsModel(), "Different type to TransactionsModel");
    }

    @Test
    void testEquals2() {
        TransactionsModel transactionsModel = new TransactionsModel();
        assertEquals(transactionsModel, transactionsModel);
        int expectedHashCodeResult = transactionsModel.hashCode();
        assertEquals(expectedHashCodeResult, transactionsModel.hashCode());
    }

    @Test
    void testEquals3() {
        TransactionsModel transactionsModel = new TransactionsModel();
        TransactionsModel transactionsModel1 = new TransactionsModel();
        assertEquals(transactionsModel, transactionsModel1);
        int expectedHashCodeResult = transactionsModel.hashCode();
        assertEquals(expectedHashCodeResult, transactionsModel1.hashCode());
    }

    @Test
    void testEquals4() {
        TransactionsModel transactionsModel = new TransactionsModel("jane.doe@example.org", "GB", "21654", "Dr Jane Doe",
                "42", "Card Type", "Transaction Status", "Bank Reference Code", 10.0f, 1, "Status Description", 1, 1, 1,
                mock(Timestamp.class), mock(Timestamp.class));
        assertNotEquals(transactionsModel, new TransactionsModel());
    }

    @Test
    void testEquals5() {
        TransactionsModel transactionsModel = new TransactionsModel();
        transactionsModel.setEmail("jane.doe@example.org");
        assertNotEquals(transactionsModel, new TransactionsModel());
    }

    @Test
    void testEquals6() {
        TransactionsModel transactionsModel = new TransactionsModel();
        transactionsModel.setCountry("GB");
        assertNotEquals(transactionsModel, new TransactionsModel());
    }

    @Test
    void testEquals7() {
        TransactionsModel transactionsModel = new TransactionsModel();
        transactionsModel.setZipcode("21654");
        assertNotEquals(transactionsModel, new TransactionsModel());
    }

    @Test
    void testEquals8() {
        TransactionsModel transactionsModel = new TransactionsModel();
        transactionsModel.setFullName("Dr Jane Doe");
        assertNotEquals(transactionsModel, new TransactionsModel());
    }

    @Test
    void testEquals9() {
        TransactionsModel transactionsModel = new TransactionsModel();
        transactionsModel.setCardNumber("42");
        assertNotEquals(transactionsModel, new TransactionsModel());
    }

    @Test
    void testEquals10() {
        TransactionsModel transactionsModel = new TransactionsModel();
        transactionsModel.setCardType("Card Type");
        assertNotEquals(transactionsModel, new TransactionsModel());
    }

    @Test
    void testEquals11() {
        TransactionsModel transactionsModel = new TransactionsModel();
        transactionsModel.setTransactionStatus("Transaction Status");
        assertNotEquals(transactionsModel, new TransactionsModel());
    }

    @Test
    void testEquals12() {
        TransactionsModel transactionsModel = new TransactionsModel();
        transactionsModel.setBankReferenceCode("Bank Reference Code");
        assertNotEquals(transactionsModel, new TransactionsModel());
    }

    @Test
    void testEquals13() {
        TransactionsModel transactionsModel = new TransactionsModel();
        transactionsModel.setStatusCode(1);
        assertNotEquals(transactionsModel, new TransactionsModel());
    }

    @Test
    void testEquals14() {
        TransactionsModel transactionsModel = new TransactionsModel();
        transactionsModel.setStatusDescription("Status Description");
        assertNotEquals(transactionsModel, new TransactionsModel());
    }

    @Test
    void testEquals15() {
        TransactionsModel transactionsModel = new TransactionsModel();
        transactionsModel.setExpiryMonth(1);
        assertNotEquals(transactionsModel, new TransactionsModel());
    }

    @Test
    void testEquals16() {
        TransactionsModel transactionsModel = new TransactionsModel();
        transactionsModel.setExpiryYear(1);
        assertNotEquals(transactionsModel, new TransactionsModel());
    }

    @Test
    void testEquals17() {
        TransactionsModel transactionsModel = new TransactionsModel();
        transactionsModel.setCvv(1);
        assertNotEquals(transactionsModel, new TransactionsModel());
    }

    @Test
    void testEquals18() {
        TransactionsModel transactionsModel = new TransactionsModel();
        transactionsModel.setCreatedOn(mock(Timestamp.class));
        assertNotEquals(transactionsModel, new TransactionsModel());
    }

    @Test
    void testEquals19() {
        TransactionsModel transactionsModel = new TransactionsModel();
        transactionsModel.setUpdatedOn(mock(Timestamp.class));
        assertNotEquals(transactionsModel, new TransactionsModel());
    }

    @Test
    void testEquals20() {
        TransactionsModel transactionsModel = new TransactionsModel("jane.doe@example.org", "GB", "21654", "Dr Jane Doe",
                "42", "Card Type", "Transaction Status", "Bank Reference Code", 10.0f, 1, "Status Description", 1, 1, 1,
                mock(Timestamp.class), mock(Timestamp.class));
        assertNotEquals(transactionsModel,
                new TransactionsModel("jane.doe@example.org", "GB", "21654", "Dr Jane Doe", "42", "Card Type",
                        "Transaction Status", "Bank Reference Code", 10.0f, 1, "Status Description", 1, 1, 1, mock(Timestamp.class),
                        mock(Timestamp.class)));
    }

    @Test
    void testEquals21() {
        TransactionsModel transactionsModel = new TransactionsModel();

        TransactionsModel transactionsModel1 = new TransactionsModel();
        transactionsModel1.setEmail("jane.doe@example.org");
        assertNotEquals(transactionsModel, transactionsModel1);
    }

    @Test
    void testEquals22() {
        TransactionsModel transactionsModel = new TransactionsModel();

        TransactionsModel transactionsModel1 = new TransactionsModel();
        transactionsModel1.setCountry("GB");
        assertNotEquals(transactionsModel, transactionsModel1);
    }

    @Test
    void testEquals23() {
        TransactionsModel transactionsModel = new TransactionsModel();

        TransactionsModel transactionsModel1 = new TransactionsModel();
        transactionsModel1.setZipcode("21654");
        assertNotEquals(transactionsModel, transactionsModel1);
    }

    @Test
    void testEquals24() {
        TransactionsModel transactionsModel = new TransactionsModel();

        TransactionsModel transactionsModel1 = new TransactionsModel();
        transactionsModel1.setFullName("Dr Jane Doe");
        assertNotEquals(transactionsModel, transactionsModel1);
    }

    @Test
    void testEquals25() {
        TransactionsModel transactionsModel = new TransactionsModel();

        TransactionsModel transactionsModel1 = new TransactionsModel();
        transactionsModel1.setCardNumber("42");
        assertNotEquals(transactionsModel, transactionsModel1);
    }

    @Test
    void testEquals26() {
        TransactionsModel transactionsModel = new TransactionsModel();

        TransactionsModel transactionsModel1 = new TransactionsModel();
        transactionsModel1.setCardType("Card Type");
        assertNotEquals(transactionsModel, transactionsModel1);
    }

    @Test
    void testEquals27() {
        TransactionsModel transactionsModel = new TransactionsModel();

        TransactionsModel transactionsModel1 = new TransactionsModel();
        transactionsModel1.setTransactionStatus("Transaction Status");
        assertNotEquals(transactionsModel, transactionsModel1);
    }

    @Test
    void testEquals28() {
        TransactionsModel transactionsModel = new TransactionsModel();

        TransactionsModel transactionsModel1 = new TransactionsModel();
        transactionsModel1.setBankReferenceCode("Bank Reference Code");
        assertNotEquals(transactionsModel, transactionsModel1);
    }

    @Test
    void testEquals29() {
        TransactionsModel transactionsModel = new TransactionsModel();

        TransactionsModel transactionsModel1 = new TransactionsModel();
        transactionsModel1.setStatusCode(1);
        assertNotEquals(transactionsModel, transactionsModel1);
    }

    @Test
    void testEquals30() {
        TransactionsModel transactionsModel = new TransactionsModel();

        TransactionsModel transactionsModel1 = new TransactionsModel();
        transactionsModel1.setStatusDescription("Status Description");
        assertNotEquals(transactionsModel, transactionsModel1);
    }

    @Test
    void testEquals31() {
        TransactionsModel transactionsModel = new TransactionsModel();

        TransactionsModel transactionsModel1 = new TransactionsModel();
        transactionsModel1.setExpiryMonth(1);
        assertNotEquals(transactionsModel, transactionsModel1);
    }

    @Test
    void testEquals32() {
        TransactionsModel transactionsModel = new TransactionsModel();

        TransactionsModel transactionsModel1 = new TransactionsModel();
        transactionsModel1.setExpiryYear(1);
        assertNotEquals(transactionsModel, transactionsModel1);
    }

    @Test
    void testEquals33() {
        TransactionsModel transactionsModel = new TransactionsModel();

        TransactionsModel transactionsModel1 = new TransactionsModel();
        transactionsModel1.setCvv(1);
        assertNotEquals(transactionsModel, transactionsModel1);
    }

    @Test
    void testEquals34() {
        TransactionsModel transactionsModel = new TransactionsModel();

        TransactionsModel transactionsModel1 = new TransactionsModel();
        transactionsModel1.setCreatedOn(mock(Timestamp.class));
        assertNotEquals(transactionsModel, transactionsModel1);
    }

    @Test
    void testEquals35() {
        TransactionsModel transactionsModel = new TransactionsModel();

        TransactionsModel transactionsModel1 = new TransactionsModel();
        transactionsModel1.setUpdatedOn(mock(Timestamp.class));
        assertNotEquals(transactionsModel, transactionsModel1);
    }
}

