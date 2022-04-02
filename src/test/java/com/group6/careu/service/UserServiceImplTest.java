package com.group6.careu.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.Patient;
import com.group6.careu.entity.User;
import com.group6.careu.exceptions.UserNotFoundException;
import com.group6.careu.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class UserServiceImplTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    User user;

    List<User> userList;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .firstName("Ramesh")
                .lastName("Singh")
                .email("ramesh@gmail.com")
                .gender("male")
                .phone("11254")
                .enabled(true)
                .role("doctor")
                .build();
        userList = new ArrayList<>();
        userList.add(user);
    }

    @Test
    void Save() {
        /*
        Doctor doctor = new Doctor();
        doctor.setAge(1);
        doctor.setDoctorContact(1);
        doctor.setDoctor_id(1);
        doctor.setLicense_number("42");
        doctor.setSpecialization("Specialization");
        */
        Doctor doctor = new Doctor();
        doctor.setDoctorQualification("Sample Qualification");
        doctor.setDoctorOverview("Sample Overview");
        doctor.setDoctorLocation("Sample Location");
        doctor.setDoctorAge(1);
        doctor.setDoctorSpel("Sample Specialization");
        doctor.setDoctorExperience("Sample Experience");
        doctor.setDoctor_id(1);
        doctor.setDoctorRegNumber("S1");

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
        user.setPassword("element");
        user.setPatient(patient);
        user.setPhone("4105551212");
        when(this.userRepository.save((User) any())).thenReturn(user);
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        Doctor doctor1 = new Doctor();
        /*
        doctor1.setAge(1);
        doctor1.setDoctorContact(1);
        doctor1.setDoctor_id(1);
        doctor1.setLicense_number("42");
        doctor1.setSpecialization("Specialization");
        */
        doctor1.setDoctorQualification("Sample Qualification");
        doctor1.setDoctorOverview("Sample Overview");
        doctor1.setDoctorLocation("Sample Location");
        doctor1.setDoctorAge(1);
        doctor1.setDoctorSpel("Sample Specialization");
        doctor1.setDoctorExperience("Sample Experience");
        doctor1.setDoctor_id(1);
        doctor1.setDoctorRegNumber("S1");

        Patient patient1 = new Patient();
        patient1.setDisease("Disease");
        patient1.setPatient_id(1);

        User user1 = new User();
        user1.setDoctor(doctor1);
        user1.setEmail("jane.doe@example.org");
        user1.setEnabled(true);
        user1.setFirstName("Jane");
        user1.setGender("Gender");
        user1.setId(1);
        user1.setLastName("Doe");
        user1.setPassword("element");
        user1.setPatient(patient1);
        user1.setPhone("4105551212");
        assertTrue(this.userServiceImpl.save(user1));
        verify(this.userRepository).save((User) any());
        verify(this.passwordEncoder).encode((CharSequence) any());
        assertTrue(user1.isEnabled());
        assertEquals("secret", user1.getPassword());
    }

    @Test
    void listAll() {
        when(userRepository.findAll()).thenReturn(userList);
        List<User> userData =userServiceImpl.listAll();
        System.out.println(userData);
        assertThat(userData.size()).isGreaterThan(0);
    }

    @Test
    void encodePassword() {
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        Doctor doctor = new Doctor();
        doctor.setDoctorQualification("Sample Qualification");
        doctor.setDoctorOverview("Sample Overview");
        doctor.setDoctorLocation("Sample Location");
        doctor.setDoctorAge(1);
        doctor.setDoctorSpel("Sample Specialization");
        doctor.setDoctorExperience("Sample Experience");
        doctor.setDoctor_id(1);
        doctor.setDoctorRegNumber("S1");

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
        user.setPassword("iloveyou");
        user.setPatient(patient);
        user.setPhone("4105551212");
        this.userServiceImpl.encodePassword(user);
        verify(this.passwordEncoder).encode((CharSequence) any());
        assertEquals("secret", user.getPassword());
    }

    @Test
    void isEmailUnique() {
        Doctor doctor = new Doctor();
        doctor.setDoctorQualification("Sample Qualification");
        doctor.setDoctorOverview("Sample Overview");
        doctor.setDoctorLocation("Sample Location");
        doctor.setDoctorAge(1);
        doctor.setDoctorSpel("Sample Specialization");
        doctor.setDoctorExperience("Sample Experience");
        doctor.setDoctor_id(1);
        doctor.setDoctorRegNumber("S1");

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
        user.setPassword("element");
        user.setPatient(patient);
        user.setPhone("4105551212");
        when(this.userRepository.getUserByEmail((String) any())).thenReturn(user);
        assertFalse(this.userServiceImpl.isEmailUnique("jane.doe@example.org"));
        verify(this.userRepository).getUserByEmail((String) any());
    }

    @Test
    void updateUserEnabledStatus() {
        doNothing().when(this.userRepository).updateEnabledStatus((Integer) any(), anyBoolean());
        assertTrue(this.userServiceImpl.updateUserEnabledStatus(1, true));
        verify(this.userRepository).updateEnabledStatus((Integer) any(), anyBoolean());
    }

    @Test
    void delete() throws UserNotFoundException {
        doNothing().when(this.userRepository).deleteById((Integer) any());
        when(this.userRepository.countById((Integer) any())).thenReturn(3L);
        this.userServiceImpl.delete(1);
        verify(this.userRepository).countById((Integer) any());
        verify(this.userRepository).deleteById((Integer) any());
    }

    @Test
    void delete2() throws UserNotFoundException {
        doNothing().when(this.userRepository).deleteById((Integer) any());
        when(this.userRepository.countById((Integer) any())).thenReturn(0L);
        assertThrows(UserNotFoundException.class, () -> this.userServiceImpl.delete(1));
        verify(this.userRepository).countById((Integer) any());
    }

    @Test
    void delete3() throws UserNotFoundException {
        doNothing().when(this.userRepository).deleteById((Integer) any());
        when(this.userRepository.countById((Integer) any())).thenReturn(null);
        assertThrows(UserNotFoundException.class, () -> this.userServiceImpl.delete(1));
        verify(this.userRepository).countById((Integer) any());
    }

    @Test
    void getByEmail() {
        Doctor doctor = new Doctor();
        doctor.setDoctorQualification("Sample Qualification");
        doctor.setDoctorOverview("Sample Overview");
        doctor.setDoctorLocation("Sample Location");
        doctor.setDoctorAge(1);
        doctor.setDoctorSpel("Sample Specialization");
        doctor.setDoctorExperience("Sample Experience");
        doctor.setDoctor_id(1);
        doctor.setDoctorRegNumber("S1");

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
        user.setPassword("element");
        user.setPatient(patient);
        user.setPhone("4105551212");
        when(this.userRepository.getUserByEmail((String) any())).thenReturn(user);
        assertSame(user, this.userServiceImpl.getByEmail("jane.doe@example.org"));
        verify(this.userRepository).getUserByEmail((String) any());
    }
}

