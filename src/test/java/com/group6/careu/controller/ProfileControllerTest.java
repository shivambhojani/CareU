package com.group6.careu.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.Patient;
import com.group6.careu.entity.User;
import com.group6.careu.security.CareuUserDetails;
import com.group6.careu.service.UserServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;

class ProfileControllerTest {



    @Test
    void testEditProfile1() {

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
        UserServiceImpl userServiceImpl = mock(UserServiceImpl.class);
        when(userServiceImpl.getByEmail((String) any())).thenReturn(user);
        ProfileController profileController = new ProfileController(userServiceImpl);
        ConcurrentModel model = new ConcurrentModel();
        assertEquals("profile_doctor", profileController.editProfile(model, new CareuUserDetails(
                new User("Jane", "Doe", "doctor", "4105551212", "jane.doe@example.org", "44556", true, "doctor"))));
        verify(userServiceImpl).getByEmail((String) any());
    }



    @Test
    void testEditProfile2() {

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
        UserServiceImpl userServiceImpl = mock(UserServiceImpl.class);
        when(userServiceImpl.getByEmail((String) any())).thenReturn(user);
        ProfileController profileController = new ProfileController(userServiceImpl);
        ConcurrentModel model = new ConcurrentModel();
        assertEquals("profile_patient", profileController.editProfile(model, new CareuUserDetails(
                new User("Jane", "Doe", "doctor", "4105551212", "jane.doe@example.org", "44556", true, "profile_doctor"))));
        verify(userServiceImpl).getByEmail((String) any());
    }
}

