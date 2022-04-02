package com.group6.careu.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDocumentModel {
        private Integer id;
        private String fileName;
        private String fileType;
        private byte[] fileUrl;
}

