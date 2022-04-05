package com.group6.careu.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.DoctorAvailability;
import com.group6.careu.entity.Patient;
import com.group6.careu.model.AppointmentModel;
import com.group6.careu.model.DoctorAvailabilityModel;
import com.group6.careu.repository.DoctorAvailabilityRepository;
import com.group6.careu.repository.DoctorRepository;
import com.group6.careu.repository.UserRepository;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
/**
 * Created by Bijitashya on 04, 2022
 */
@ContextConfiguration(classes = {DoctorAvailabilityServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DoctorAvailabilityServiceImplTest {
    @MockBean
    private DoctorAvailabilityRepository doctorAvailabilityRepository;

    @Autowired
    private DoctorAvailabilityServiceImpl doctorAvailabilityServiceImpl;

    @MockBean
    private DoctorRepository doctorRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testGetAllTimes() {
        ArrayList<DoctorAvailability> doctorAvailabilityList = new ArrayList<>();
        when(this.doctorAvailabilityRepository.getAvailableTimes(anyInt())).thenReturn(doctorAvailabilityList);
        List<DoctorAvailability> actualAllTimes = this.doctorAvailabilityServiceImpl.getAllTimes(1);
        assertSame(doctorAvailabilityList, actualAllTimes);
        assertTrue(actualAllTimes.isEmpty());
        verify(this.doctorAvailabilityRepository).getAvailableTimes(anyInt());
    }


    @Test
    void testGetEntites2() {
        DoctorAvailabilityModel doctorAvailabilityModel = new DoctorAvailabilityModel();
        doctorAvailabilityModel.setAvailableDate(new ArrayList<>());
        assertTrue(this.doctorAvailabilityServiceImpl.getEntites(doctorAvailabilityModel).isEmpty());
    }

}

