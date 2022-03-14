package com.group6.careu;
import com.group6.careu.controller.UserDocumentOperationController;
import com.group6.careu.entity.UserDocument;
import com.group6.careu.repository.UserDocumentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(true)
public class UserDocumentUploadTest {
    private UserDocumentOperationController userDocumentOperationController = new UserDocumentOperationController();

    @Autowired
    private UserDocumentRepository userDocumentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testDocumentUpload() throws Exception {
        MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
        assertThat(userDocumentOperationController.uploadDocument(firstFile).getStatusCode().value()==200) ;
    }

    @Test
    public void testDocumentList() throws Exception {
        List<UserDocument> files = userDocumentRepository.gerDocumentsByUserId(1);
        files.forEach(file-> System.out.println(file.getFileName()));
    }

    @Test
    public void testGetDocumentById() throws Exception {
         UserDocument file  = userDocumentRepository.findById(1).get();
         System.out.println(file.getFileName());
    }
}
