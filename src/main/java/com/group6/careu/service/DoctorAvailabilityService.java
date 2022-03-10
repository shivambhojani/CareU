package com.group6.careu.service;

import com.group6.careu.entity.DoctorAvailability;

import java.util.List;

public interface DoctorAvailabilityService {

    public List<DoctorAvailability> getAllTimes(Integer doctor_id);
}
