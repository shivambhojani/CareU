package com.group6.careu.service;

import com.group6.careu.entity.Appointment;
import com.group6.careu.entity.Patient;
import com.group6.careu.entity.User;
import com.group6.careu.model.PatientAppointmentModel;
import com.group6.careu.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{


    @Autowired
    private PatientRepository repository;

    @Override
    public User getPatientbyID(Integer id) {
        return repository.findPatient(id);
    }



}
