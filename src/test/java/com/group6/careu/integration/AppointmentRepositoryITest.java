package com.group6.careu.integration;

import com.group6.careu.entity.Appointment;
import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.Patient;
import com.group6.careu.entity.User;
import com.group6.careu.repository.AppointmentRepository;
import com.group6.careu.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Bijitashya on 04, 2022
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(true)
class AppointmentRepositoryITest {

    @Autowired
    private UserRepository repo;
    @Autowired
    private AppointmentRepository aRepo;
    @Test
    void updatePatientFeedback() {
        User user = new User("Doctor", "doctor 1", "male", "236541789","test@mail.com","12344", true, "doctor");
        user.setId(1);
        Doctor d = new Doctor();
        d.setDoctor_id(1);
        repo.save(user);
        User user2 = new User("Patient", "pp 1", "male", "236541789","test1@mail.com","12344", true, "patient");
        user2.setId(2);
        Patient p = new Patient();
        p.setPatient_id(1);
        repo.save(user2);
        Appointment a = new Appointment();
        a.setDoctor(d);
        a.setPatient(p);
        a.setAppointment_date(Date.valueOf("2022-01-30"));
        a.setPatientFeedback("Good");
        Appointment a1 = aRepo.save(a);
        a1.getAppointmentId();
        int status = aRepo.updatePatientFeedback(a1.getAppointmentId(), "Awesome");
        assertThat(status).isNotEqualTo(0);
    }

    @Test
    void updateMedication() {
        User user = new User("Doctor", "doctor 1", "male", "236541789","test@mail.com","12344", true, "doctor");
        user.setId(1);
        Doctor d = new Doctor();
        d.setDoctor_id(1);
        repo.save(user);
        User user2 = new User("Patient", "pp 1", "male", "236541789","test1@mail.com","12344", true, "patient");
        user2.setId(2);
        Patient p = new Patient();
        p.setPatient_id(1);
        repo.save(user2);
        Appointment a = new Appointment();
        a.setDoctor(d);
        a.setPatient(p);
        a.setAppointment_date(Date.valueOf("2022-01-30"));
        a.setMedications("DF55");
        Appointment a1 = aRepo.save(a);
        int status = aRepo.updateMedication(a1.getAppointmentId(), "889");
        assertThat(status).isNotEqualTo(0);
//        assertThat(aRepo.getAppointmentBasedOnApptId(String.valueOf(a1.getAppointmentId())).getMedications()).isEqualTo("889");
    }

    @Test
    void getAppointmentsByAppointmentId() {
        User user = new User("Doctor", "doctor 1", "male", "236541789","test@mail.com","12344", true, "doctor");
        user.setId(1);
        Doctor d = new Doctor();
        d.setDoctor_id(1);
        repo.save(user);
        User user2 = new User("Patient", "pp 1", "male", "236541789","test1@mail.com","12344", true, "patient");
        user2.setId(2);
        Patient p = new Patient();
        p.setPatient_id(1);
        repo.save(user2);
        Appointment a = new Appointment();
        a.setDoctor(d);
        a.setPatient(p);
        a.setAppointment_date(Date.valueOf("2022-01-30"));
        a.setMedications("DF55");
        Appointment a1 = aRepo.save(a);
        UUID id = a1.getAppointmentId();
        assertThat(aRepo.getAppointmentBasedOnApptId(String.valueOf(id))).isEqualTo(a1);
    }

    @Test
    void getTodaysAppointmentByPatientId() {
        User user = new User("Doctor", "doctor 1", "male", "236541789","test@mail.com","12344", true, "doctor");
        user.setId(1);
        Doctor d = new Doctor();
        d.setDoctor_id(1);
        repo.save(user);
        User user2 = new User("Patient", "pp 1", "male", "236541789","test1@mail.com","12344", true, "patient");
        user2.setId(2);
        Patient p = new Patient();
        p.setPatient_id(1);
        repo.save(user2);
        User user3 = new User("Doc 2", "pp 3", "male", "236541789","test3@mail.com","12344", true, "doctor");
        user2.setId(3);
        Doctor d2 = new Doctor();
        d2.setDoctor_id(3);
        repo.save(user3);
        Appointment a = new Appointment();
        a.setDoctor(d);
        a.setPatient(p);
        a.setAppointment_date(Date.valueOf("2022-04-01"));
        a.setMedications("DF55");
        Appointment a1 = aRepo.save(a);
        Appointment a2 = new Appointment();
        a2.setDoctor(d2);
        a2.setPatient(p);
        a2.setAppointment_date(Date.valueOf("2022-04-01"));
        a2.setMedications("1233");
        Appointment a3 = aRepo.save(a);
        List<Appointment> list = aRepo.getTodaysAppointmentByPatientId(1, Date.valueOf("2022-04-01"));
        assertThat(list.size()).isGreaterThan(0);
    }

    @Test
    void getFutureAppointmentByPatientId() {
        User user = new User("Doctor", "doctor 1", "male", "236541789","test@mail.com","12344", true, "doctor");
        user.setId(1);
        Doctor d = new Doctor();
        d.setDoctor_id(1);
        repo.save(user);
        User user2 = new User("Patient", "pp 1", "male", "236541789","test1@mail.com","12344", true, "patient");
        user2.setId(2);
        Patient p = new Patient();
        p.setPatient_id(1);
        repo.save(user2);
        User user3 = new User("Doc 2", "pp 3", "male", "236541789","test3@mail.com","12344", true, "doctor");
        user2.setId(3);
        Doctor d2 = new Doctor();
        d2.setDoctor_id(3);
        repo.save(user3);
        Appointment a = new Appointment();
        a.setDoctor(d);
        a.setPatient(p);
        a.setAppointment_date(Date.valueOf("2023-04-01"));
        a.setMedications("DF55");
        Appointment a1 = aRepo.save(a);
        Appointment a2 = new Appointment();
        a2.setDoctor(d2);
        a2.setPatient(p);
        a2.setAppointment_date(Date.valueOf("2022-04-01"));
        a2.setMedications("1233");
        Appointment a3 = aRepo.save(a);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        Date dateToday = Date.valueOf(dtf.format(now));
        List<Appointment> list = aRepo.getFutureAppointmentByPatientId(1,dateToday);
        assertThat(list.size()).isGreaterThan(0);
    }

    @Test
    void getPastAppointmentByPatientId() {
        User user = new User("Doctor", "doctor 1", "male", "236541789","test@mail.com","12344", true, "doctor");
        user.setId(1);
        Doctor d = new Doctor();
        d.setDoctor_id(1);
        repo.save(user);
        User user2 = new User("Patient", "pp 1", "male", "236541789","test1@mail.com","12344", true, "patient");
        user2.setId(2);
        Patient p = new Patient();
        p.setPatient_id(1);
        repo.save(user2);
        User user3 = new User("Doc 2", "pp 3", "male", "236541789","test3@mail.com","12344", true, "doctor");
        user2.setId(3);
        Doctor d2 = new Doctor();
        d2.setDoctor_id(3);
        repo.save(user3);
        Appointment a = new Appointment();
        a.setDoctor(d);
        a.setPatient(p);
        a.setAppointment_date(Date.valueOf("2020-04-01"));
        a.setMedications("DF55");
        Appointment a1 = aRepo.save(a);
        Appointment a2 = new Appointment();
        a2.setDoctor(d2);
        a2.setPatient(p);
        a2.setAppointment_date(Date.valueOf("2020-04-01"));
        a2.setMedications("1233");
        Appointment a3 = aRepo.save(a);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        Date dateToday = Date.valueOf(dtf.format(now));
        List<Appointment> list = aRepo.getPastAppointmentByPatientId(1,dateToday);
        assertThat(list.size()).isGreaterThan(0);
    }

    @Test
    void getTodaysAppointmentByDoctorId() {
        User user = new User("Doctor", "doctor 1", "male", "236541789","test@mail.com","12344", true, "doctor");
        user.setId(1);
        Doctor d = new Doctor();
        d.setDoctor_id(1);
        repo.save(user);
        User user2 = new User("Patient", "pp 1", "male", "236541789","test1@mail.com","12344", true, "patient");
        user2.setId(2);
        Patient p = new Patient();
        p.setPatient_id(1);
        repo.save(user2);
        User user3 = new User("Doc 2", "pp 3", "male", "236541789","test3@mail.com","12344", true, "doctor");
        user2.setId(3);
        Doctor d2 = new Doctor();
        d2.setDoctor_id(3);
        repo.save(user3);
        Appointment a = new Appointment();
        a.setDoctor(d);
        a.setPatient(p);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        Date dateToday = Date.valueOf(dtf.format(now));
        a.setAppointment_date(dateToday);
        a.setMedications("DF55");
        Appointment a1 = aRepo.save(a);
        Appointment a2 = new Appointment();
        a2.setDoctor(d2);
        a2.setPatient(p);
        a2.setAppointment_date(dateToday);
        a2.setMedications("1233");
        Appointment a3 = aRepo.save(a);

        List<Appointment> list = aRepo.getTodaysAppointmentByPatientId(1,dateToday);
        assertThat(list.size()).isGreaterThan(0);
    }

    @Test
    void getFutureAppointmentByDoctorId() {
        User user = new User("Doctor", "doctor 1", "male", "236541789","test@mail.com","12344", true, "doctor");
        user.setId(1);
        Doctor d = new Doctor();
        d.setDoctor_id(1);
        repo.save(user);
        User user2 = new User("Patient", "pp 1", "male", "236541789","test1@mail.com","12344", true, "patient");
        user2.setId(2);
        Patient p = new Patient();
        p.setPatient_id(1);
        repo.save(user2);
        User user3 = new User("Doc 2", "pp 3", "male", "236541789","test3@mail.com","12344", true, "doctor");
        user2.setId(3);
        Doctor d2 = new Doctor();
        d2.setDoctor_id(3);
        repo.save(user3);
        Appointment a = new Appointment();
        a.setDoctor(d);
        a.setPatient(p);
        a.setAppointment_date(Date.valueOf("2023-04-01"));
        a.setMedications("DF55");
        Appointment a1 = aRepo.save(a);
        Appointment a2 = new Appointment();
        a2.setDoctor(d2);
        a2.setPatient(p);
        a2.setAppointment_date(Date.valueOf("2023-04-01"));
        a2.setMedications("1233");
        Appointment a3 = aRepo.save(a);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        Date dateToday = Date.valueOf(dtf.format(now));
        List<Appointment> list = aRepo.getFutureAppointmentByDoctorId(1,dateToday);
        assertThat(list.size()).isGreaterThan(0);
    }

    @Test
    void getPastAppointmentByDoctorId() {
        User user = new User("Doctor", "doctor 1", "male", "236541789","test@mail.com","12344", true, "doctor");
        user.setId(1);
        Doctor d = new Doctor();
        d.setDoctor_id(1);
        repo.save(user);
        User user2 = new User("Patient", "pp 1", "male", "236541789","test1@mail.com","12344", true, "patient");
        user2.setId(2);
        Patient p = new Patient();
        p.setPatient_id(1);
        repo.save(user2);
        User user3 = new User("Doc 2", "pp 3", "male", "236541789","test3@mail.com","12344", true, "doctor");
        user2.setId(3);
        Doctor d2 = new Doctor();
        d2.setDoctor_id(3);
        repo.save(user3);
        Appointment a = new Appointment();
        a.setDoctor(d);
        a.setPatient(p);
        a.setAppointment_date(Date.valueOf("2020-04-01"));
        a.setMedications("DF55");
        Appointment a1 = aRepo.save(a);
        Appointment a2 = new Appointment();
        a2.setDoctor(d2);
        a2.setPatient(p);
        a2.setAppointment_date(Date.valueOf("2020-04-01"));
        a2.setMedications("1233");
        Appointment a3 = aRepo.save(a);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        Date dateToday = Date.valueOf(dtf.format(now));
        List<Appointment> list = aRepo.getPastAppointmentByDoctorId(1,dateToday);
        assertThat(list.size()).isGreaterThan(0);
    }


    @Test
    void getAppointmentBasedOnApptId() {
        User user = new User("Doctor", "doctor 1", "male", "236541789","test@mail.com","12344", true, "doctor");
        user.setId(1);
        Doctor d = new Doctor();
        d.setDoctor_id(1);
        repo.save(user);
        User user2 = new User("Patient", "pp 1", "male", "236541789","test1@mail.com","12344", true, "patient");
        user2.setId(2);
        Patient p = new Patient();
        p.setPatient_id(1);
        repo.save(user2);
        User user3 = new User("Doc 2", "pp 3", "male", "236541789","test3@mail.com","12344", true, "doctor");
        user2.setId(3);
        Doctor d2 = new Doctor();
        d2.setDoctor_id(3);
        repo.save(user3);
        Appointment a = new Appointment();
        a.setDoctor(d);
        a.setPatient(p);
        a.setAppointment_date(Date.valueOf("2020-04-01"));
        a.setMedications("DF55");
        Appointment a1 = aRepo.save(a);
        UUID id = a1.getAppointmentId();
        Appointment retrived = aRepo.getAppointmentBasedOnApptId(String.valueOf(id));
        assertThat(retrived.getAppointmentId()).isEqualTo(id);
    }
}