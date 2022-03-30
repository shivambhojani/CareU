package com.group6.careu.service;
import com.group6.careu.entity.UserDocument;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface IUserDocumentOperationService {
    public UserDocument store(MultipartFile file, Integer userId) throws IOException;
    public UserDocument getDocument(Integer id);
    public List<UserDocument> getAllUserDocuments(Integer userId);
}
