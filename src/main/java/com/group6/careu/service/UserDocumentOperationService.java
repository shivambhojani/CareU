package com.group6.careu.service;

import com.group6.careu.entity.UserDocument;
import com.group6.careu.repository.UserDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class UserDocumentOperationService implements IUserDocumentOperationService {

    @Autowired
    private UserDocumentRepository userDocumentRepository;

    @Override
    public UserDocument store(MultipartFile file, Integer userId) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        UserDocument UserDocument = new UserDocument(fileName, file.getContentType(), file.getBytes(), userId);
        return userDocumentRepository.save(UserDocument);
    }

    @Override
    public UserDocument getDocument(Integer id) {
        return userDocumentRepository.findById(id).get();
    }

    @Override
    public List<UserDocument> getAllUserDocuments(Integer userId) {
        return userDocumentRepository.gerDocumentsByUserId(userId);
    }
}
