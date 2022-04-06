package com.group6.careu.integration;

import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.Patient;
import com.group6.careu.entity.User;
import com.group6.careu.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Bijitashya on 03, 2022
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(true)
public class UserITest {
    @Autowired
    private UserRepository repo;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getUserByEmail() {
        User userNamHM = new User("Doctor", "doctor 1", "male", "236541789","test@mail.com","12344", true, "doctor");
        repo.save(userNamHM);
        User user = repo.findByEmail(userNamHM.getEmail());
        assertThat(user).isNotNull();
    }

    @Test
    void countById() {
        int userId = 1;
        User userNamHM = new User("Doctor", "doctor 1", "male", "236541789","test@mail.com","12344", true, "doctor");
        repo.save(userNamHM);
        Long id = repo.countById(userId);
        assertThat(id).isNotNull();
    }

    @Test
    void updateEnabledStatus() {
        User userNamHM = new User("Doctor", "doctor 1", "male", "236541789","test@mail.com","12344", true, "doctor");
        User savedUser = repo.save(userNamHM);
        User fetchedUser = repo.findById(savedUser.getId()).get();
        fetchedUser.setEnabled(false);
        User updatedUser = repo.save(fetchedUser);
        assertThat(updatedUser.isEnabled()).isEqualTo(false);
    }

    @Test
    void getUserByDoctorId() {
        User doctorUser = new User("Doctor", "doctor 1", "male", "236541789","test@mail.com","12344", true, "doctor");
        doctorUser.setId(45);
        Doctor doc = new Doctor();
        doc.setDoctor_id(45);
        doctorUser.setDoctor(doc);
        User savedDoc = repo.save(doctorUser);
        int doctorId = savedDoc.getDoctor().getDoctor_id();
        assertThat(doctorId).isGreaterThan(0);
    }

    @Test
    void getAllDoctor() {
        User doctorUser = new User("Doctor", "doctor ", "male", "236541789","test@mail.com","12344", true, "doctor");
        User doctorUser1 = new User("Doctor1", "doctor1", "male", "236541789","test43@mail.com","1245344", true, "doctor");
        List<User> doctors = new ArrayList<>();
        doctors.add(doctorUser1);
        doctors.add(doctorUser);
        Iterable <User> doc = repo.saveAll(doctors);
        String role = "doctor";
        List<User> fetchedUsers = repo.getAllDoctor(role);
        assertThat(fetchedUsers).isNotNull();
        repo.deleteAll(fetchedUsers);
    }

    @Test
    void getFilteredDoctors() {
        User doctorUser = new User("Doctor", "doctor ", "male", "236541789","test@mail.com","12344", true, "doctor");
        doctorUser.setId(22);
        Doctor doc  = new Doctor();
        doc.setDoctorSpel("bone");
        doc.setDoctor_id(22);
        doctorUser.setDoctor(doc);
        repo.save(doctorUser);
        User doctorUser2 = new User("Doctor2", "doctor ", "male", "236541789","test2@mail.com","12344", true, "doctor");
        doctorUser2.setId(23);
        Doctor doc1  = new Doctor();
        doc1.setDoctorSpel("bone");
        doc1.setDoctor_id(23);
        doctorUser2.setDoctor(doc1);
        repo.save(doctorUser2);
        List<User> filterdDoctor = repo.getFilteredDoctors("doctor","bone");
        assertThat(filterdDoctor).isNotNull();


    }

    @Test
    void getUserByPatientId() {
        User patientUser = new User("Patient", "p1", "male", "236541789","test@mail.com","12344", true, "patient");
        patientUser.setId(45);
        Patient p = new Patient();
        p.setPatient_id(45);
        patientUser.setPatient(p);
        User savedPatient = repo.save(patientUser);
        int patientId = savedPatient.getPatient().getPatient_id();
        assertThat(patientId).isGreaterThan(0);
    }

    @Test
    void getUserById() {
    }

    @Test
    void findByEmail() {
        User userNamHM = new User("Doctor", "doctor 1", "male", "236541789","test@mail.com","12344", true, "doctor");
        User savedUser = repo.save(userNamHM);
        User fetchedUser = repo.findByEmail(savedUser.getEmail());
        assertThat(fetchedUser).isNotNull();
    }
}
