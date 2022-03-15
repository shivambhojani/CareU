package com.group6.careu.controller;
import com.group6.careu.entity.UserDocument;
import com.group6.careu.model.UserDocumentModel;
import com.group6.careu.service.UserDocumentOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserDocumentOperationController {
    @Autowired
    private UserDocumentOperationService userDocumentOperationService;

    @PostMapping("/uploadDocument/file")
    //@PathVariable Integer userId
    public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            userDocumentOperationService.store(file, 1);
            message = "Document uploaded successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Could not upload document: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @GetMapping("/getUploadedDocuments/{userId}")
    public String getUploadedDocuments(@PathVariable Integer userId, Model model) {
        List<UserDocumentModel> files =  userDocumentOperationService.getAllUserDocuments(userId).stream().map(dbFile -> {
            return new UserDocumentModel(
                    dbFile.getId(),
                    dbFile.getFileName(),
                    dbFile.getFileType(),
                    dbFile.getFileContent());
        }).collect(Collectors.toList());
        model.addAttribute("files", files);
        return "userDocumentUpload";
    }

    @GetMapping(value = "/file-download/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Integer fileId) {
        String attachmentString = "attachment; filename=\"%s\"";
        UserDocument file = userDocumentOperationService.getDocument(fileId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, file.getFileType());
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, attachmentString.format(file.getFileName()));
        return ResponseEntity.ok().headers(httpHeaders).body(file.getFileContent());
    }
}
