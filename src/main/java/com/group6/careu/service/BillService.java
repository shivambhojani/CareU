package com.group6.careu.service;

import com.group6.careu.entity.UserDocument;
import org.springframework.stereotype.Service;

@Service
public interface BillService {
    UserDocument billProcessor(Integer id, Integer bankReferenceCode);
}
