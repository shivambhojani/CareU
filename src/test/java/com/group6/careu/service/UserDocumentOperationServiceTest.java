package com.group6.careu.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group6.careu.entity.UserDocument;
import com.group6.careu.repository.UserDocumentRepository;

import java.io.IOException;

import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserDocumentOperationService.class})
@ExtendWith(SpringExtension.class)
class UserDocumentOperationServiceTest {
    @Autowired
    private UserDocumentOperationService userDocumentOperationService;

    @MockBean
    private UserDocumentRepository userDocumentRepository;

    @Test
    void testStore() throws IOException {
        UserDocument userDocument = new UserDocument();
        userDocument.setFileContent("AAAAAAAA".getBytes("UTF-8"));
        userDocument.setFileName("foo.txt");
        userDocument.setFileType("File Type");
        userDocument.setId(1);
        userDocument.setUserId(123);
        when(this.userDocumentRepository.save((UserDocument) any())).thenReturn(userDocument);
        assertSame(userDocument,
                this.userDocumentOperationService.store(new MockMultipartFile("Name", "AAAAAAAA".getBytes("UTF-8")), 123));
        verify(this.userDocumentRepository).save((UserDocument) any());
    }


    @Test
    void testGetDocument() throws UnsupportedEncodingException {
        UserDocument userDocument = new UserDocument();
        userDocument.setFileContent("AAAAAAAA".getBytes("UTF-8"));
        userDocument.setFileName("foo.txt");
        userDocument.setFileType("File Type");
        userDocument.setId(1);
        userDocument.setUserId(123);
        Optional<UserDocument> ofResult = Optional.of(userDocument);
        when(this.userDocumentRepository.findById((Integer) any())).thenReturn(ofResult);
        assertSame(userDocument, this.userDocumentOperationService.getDocument(1));
        verify(this.userDocumentRepository).findById((Integer) any());
    }



    @Test
    void testGetAllUserDocuments() {
        ArrayList<UserDocument> userDocumentList = new ArrayList<>();
        when(this.userDocumentRepository.gerDocumentsByUserId((Integer) any())).thenReturn(userDocumentList);
        List<UserDocument> actualAllUserDocuments = this.userDocumentOperationService.getAllUserDocuments(123);
        assertSame(userDocumentList, actualAllUserDocuments);
        assertTrue(actualAllUserDocuments.isEmpty());
        verify(this.userDocumentRepository).gerDocumentsByUserId((Integer) any());
    }
}

