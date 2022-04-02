package com.group6.careu.service;

import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.User;

import java.util.List;
import java.util.Optional;

public interface DoctorService {

    List<User> getAllDoctor();

    User getDoctorById(Integer doctor_id);

    List<User> getFilteredDoctor(String keyWord);

    void saveDoctorInformation(Doctor doctor);

    Optional<Doctor> getDoctorDetailsById(Integer id);
}
