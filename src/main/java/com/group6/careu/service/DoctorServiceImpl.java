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

    @Autowired
    UserRepository userRepository;

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
        User user = userRepository.getUserByDoctorId(userDetails.getId());
        doctor.setDoctor_id(user.getDoctor().getDoctor_id());
        System.out.println(doctor);

        d = doctorProfileRepository.save(doctor);
        System.out.println(d + "updated doctor");
    }

    @Override
    public Optional<Doctor> getDoctorDetailsById(Integer id) {
        return doctorProfileRepository.findById(id);
    }

    @Override
    public User getUserByDoctor(Integer doctor_id) {
        return userRepository.getUserByDoctor(doctor_id);
    }


}
