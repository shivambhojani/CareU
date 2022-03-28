package com.group6.careu.service;

import org.springframework.stereotype.Service;

@Service
public interface BillService {
    void billProcessor(Integer id,Integer bankReferenceCode);
}
