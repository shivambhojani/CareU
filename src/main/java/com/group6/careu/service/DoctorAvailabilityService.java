package com.group6.careu.service;

import com.group6.careu.entity.DoctorAvailability;
import com.group6.careu.model.DoctorAvailabilityModel;

import java.util.List;

public interface DoctorAvailabilityService {

    public List<DoctorAvailability> getAllTimes(Integer doctor_id);
    public List<DoctorAvailability> getEntites(DoctorAvailabilityModel model);
    public void saveAvailability(DoctorAvailabilityModel model);
}
