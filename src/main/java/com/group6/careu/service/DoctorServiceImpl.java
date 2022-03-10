package com.group6.careu.service;

import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.User;
import com.group6.careu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    UserRepository doctorRepository;

    @Override
    public List<User> getAllDoctor() {
        return (List<User>) doctorRepository.getAllDoctor("doctor");
    }

    @Override
    public User getDoctorById(Integer doctor_id) {
        return doctorRepository.getUserByDoctorId(doctor_id);
    }
}
