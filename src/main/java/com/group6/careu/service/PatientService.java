package com.group6.careu.service;

import com.group6.careu.entity.Patient;
import com.group6.careu.entity.User;
import org.springframework.stereotype.Service;

public interface PatientService {

    User getPatientbyID(Integer id);


}
