package com.group6.careu.service;

import com.group6.careu.entity.DoctorAvailability;
import com.group6.careu.repository.DoctorAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DoctorAvailabilityServiceImpl implements DoctorAvailabilityService{

    @Autowired
    DoctorAvailabilityRepository doctorAvailabilityRepository;

    @Override
    public List<DoctorAvailability> getAllTimes(Integer doctor_id) {
        return doctorAvailabilityRepository.getAvailableTimes(doctor_id);
    }
}
