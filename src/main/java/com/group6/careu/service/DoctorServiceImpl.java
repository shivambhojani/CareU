package com.group6.careu.service;

import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.User;
import com.group6.careu.repository.DoctorRepository;
import com.group6.careu.repository.UserRepository;
import com.group6.careu.security.CareuUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    UserRepository doctorRepository;

    @Autowired
    DoctorRepository doctorProfileRepository;

    @Override
    public List<User> getAllDoctor() {
        return (List<User>) doctorRepository.getAllDoctor("doctor");
    }

    @Override
    public User getDoctorById(Integer doctor_id) {
        return doctorRepository.getUserByDoctorId(doctor_id);
    }

    @Override
    public List<User> getFilteredDoctor(String keyword) {
        return (List<User>) doctorRepository.getFilteredDoctors("doctor", keyword);
    }

    @Override
    public void saveDoctorInformation(Doctor doctor){
        Doctor d=new Doctor();

        CareuUserDetails userDetails= (CareuUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        doctor.setDoctor_id(userDetails.getId());
        System.out.println(userDetails.getId());

        d = doctorProfileRepository.save(doctor);
    }
}
