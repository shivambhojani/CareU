package com.group6.careu.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.Patient;
import com.group6.careu.entity.User;
import com.group6.careu.repository.DoctorRepository;
import com.group6.careu.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
@ContextConfiguration(classes = {DoctorServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DoctorServiceImplTest {
    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorServiceImpl doctorServiceImpl;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testGetAllDoctor() {
        ArrayList<User> userList = new ArrayList<>();
        when(this.userRepository.getAllDoctor((String) any())).thenReturn(userList);
        List<User> actualAllDoctor = this.doctorServiceImpl.getAllDoctor();
        assertSame(userList, actualAllDoctor);
        assertTrue(actualAllDoctor.isEmpty());
        verify(this.userRepository).getAllDoctor((String) any());
    }

    @Test
    void testGetDoctorById() {
        Doctor doctor = new Doctor();
        doctor.setDoctorAge(1);
        doctor.setDoctorExperience("Doctor Experience");
        doctor.setDoctorLocation("Doctor Location");
        doctor.setDoctorOverview("Doctor Overview");
        doctor.setDoctorQualification("Doctor Qualification");
        doctor.setDoctorRegNumber("42");
        doctor.setDoctorSpel("Doctor Spel");
        doctor.setDoctor_id(1);

        Patient patient = new Patient();
        patient.setDisease("Disease");
        patient.setPatient_id(1);

        User user = new User();
        user.setDoctor(doctor);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setFirstName("Jane");
        user.setGender("Gender");
        user.setId(1);
        user.setLastName("Doe");
        user.setPassword("44556");
        user.setPatient(patient);
        user.setPhone("4105551212");
        user.setResetPasswordToken("ABC123");
        when(this.userRepository.getUserByDoctorId(anyInt())).thenReturn(user);
        assertSame(user, this.doctorServiceImpl.getDoctorById(1));
        verify(this.userRepository).getUserByDoctorId(anyInt());
    }

    @Test
    void testGetFilteredDoctor() {
        ArrayList<User> userList = new ArrayList<>();
        when(this.userRepository.getFilteredDoctors((String) any(), (String) any())).thenReturn(userList);
        List<User> actualFilteredDoctor = this.doctorServiceImpl.getFilteredDoctor("Keyword");
        assertSame(userList, actualFilteredDoctor);
        assertTrue(actualFilteredDoctor.isEmpty());
        verify(this.userRepository).getFilteredDoctors((String) any(), (String) any());
    }

    @Test
    void testGetDoctorDetailsById() {
        Doctor doctor = new Doctor();
        doctor.setDoctorAge(1);
        doctor.setDoctorExperience("Doctor Experience");
        doctor.setDoctorLocation("Doctor Location");
        doctor.setDoctorOverview("Doctor Overview");
        doctor.setDoctorQualification("Doctor Qualification");
        doctor.setDoctorRegNumber("42");
        doctor.setDoctorSpel("Doctor Spel");
        doctor.setDoctor_id(1);
        Optional<Doctor> ofResult = Optional.of(doctor);
        when(this.doctorRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<Doctor> actualDoctorDetailsById = this.doctorServiceImpl.getDoctorDetailsById(1);
        assertSame(ofResult, actualDoctorDetailsById);
        assertTrue(actualDoctorDetailsById.isPresent());
        verify(this.doctorRepository).findById((Integer) any());
    }

    @Test
    void testGetUserByDoctor() {
        Doctor doctor = new Doctor();
        doctor.setDoctorAge(1);
        doctor.setDoctorExperience("Doctor Experience");
        doctor.setDoctorLocation("Doctor Location");
        doctor.setDoctorOverview("Doctor Overview");
        doctor.setDoctorQualification("Doctor Qualification");
        doctor.setDoctorRegNumber("42");
        doctor.setDoctorSpel("Doctor Spel");
        doctor.setDoctor_id(1);

        Patient patient = new Patient();
        patient.setDisease("Disease");
        patient.setPatient_id(1);

        User user = new User();
        user.setDoctor(doctor);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setFirstName("Jane");
        user.setGender("Gender");
        user.setId(1);
        user.setLastName("Doe");
        user.setPassword("44556");
        user.setPatient(patient);
        user.setPhone("4105551212");
        user.setResetPasswordToken("ABC123");
        when(this.userRepository.getUserByDoctor(anyInt())).thenReturn(user);
        assertSame(user, this.doctorServiceImpl.getUserByDoctor(1));
        verify(this.userRepository).getUserByDoctor(anyInt());
    }
}

