package com.group6.careu.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.junit.jupiter.api.Test;

class BillModelTest {
    @Test
    void testConstructor() throws IOException {
        BillModel actualBillModel = new BillModel();
        PDDocument expectedBill = actualBillModel.bill;
        PDDocument bill = actualBillModel.getBill();
        assertSame(expectedBill, bill);
        assertFalse(bill.isEncrypted());
        assertEquals(1, bill.getNumberOfPages());
        assertTrue(bill.getResourceCache() instanceof org.apache.pdfbox.pdmodel.DefaultResourceCache);
        List<PDSignature> signatureDictionaries = bill.getSignatureDictionaries();
        assertEquals(signatureDictionaries, bill.getSignatureFields());
        assertEquals(1, bill.getPages().getCount());
        COSDocument document = bill.getDocument();
        assertFalse(document.isDecrypted());
        assertFalse(document.isClosed());
        assertEquals(1.4f, document.getVersion());
        assertEquals(signatureDictionaries, document.getObjects());
        assertNull(document.getEncryptionDictionary());
        PDDocumentCatalog documentCatalog = bill.getDocumentCatalog();
        assertNull(documentCatalog.getAcroForm());
        assertNull(documentCatalog.getDests());
    }
}

