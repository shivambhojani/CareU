package com.group6.careu.integration;

import com.group6.careu.entity.Patient;
import com.group6.careu.entity.User;
import com.group6.careu.repository.PatientRepository;
import com.group6.careu.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Bijitashya on 04, 2022
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(true)
public class PatientITest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Test
    void findPatient() {
        User user = new User("Patient", "p 1", "male", "236541789","test@mail.com","12344", true, "patient");
        user.setId(1);
        Patient p =  new Patient();
        p.setPatient_id(1);
        user.setPatient(p);
        User savedPatient = userRepository.save(user);
        User getUser = patientRepository.findPatient(savedPatient.getId());
        assertThat(getUser.getPatient().getPatient_id()).isEqualTo(savedPatient.getPatient().getPatient_id());
    }

    @Test
    void updatePatientData() {
        User user = new User("Patient", "p 1", "male", "236541789","test@mail.com","12344", true, "patient");
        user.setId(1);
        Patient p =  new Patient();
        p.setPatient_id(1);
        user.setPatient(p);
        User savedPatient = userRepository.save(user);
        int id = savedPatient.getId();
        int update = patientRepository.updatePatientData(id, "p1","p2", "55666", "male");
        User updatedUser = userRepository.getUserById(1);
        assertThat(update).isEqualTo(1);
    }

    @Test
    void updatePatientDatainPatient() {
        User user = new User("Patient", "p 1", "male", "236541789","test@mail.com","12344", true, "patient");
        user.setId(1);
        Patient p =  new Patient();
        p.setPatient_id(1);
        user.setPatient(p);
        User savedPatient = userRepository.save(user);
        int update = patientRepository.updatePatientDatainPatient(1,"POX");
        assertThat(update).isEqualTo(1);
    }
}
