package com.group6.careu.integration;

import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.DoctorAvailability;
import com.group6.careu.entity.Patient;
import com.group6.careu.entity.User;
import com.group6.careu.repository.DoctorAvailabilityRepository;
import com.group6.careu.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Created by Bijitashya on 04, 2022
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(true)
public class DoctorAvailabilityRepositoryITest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    DoctorAvailabilityRepository doctorAvailabilityRepository;

    @Test
    void getAvailableTimes() throws ParseException {
        User user = new User("Doctor", "doctor 1", "male", "236541789","test@mail.com","12344", true, "doctor");
        user.setId(1);
        Doctor d = new Doctor();
        d.setDoctor_id(1);
        user.setDoctor(d);
        userRepository.save(user);
        DoctorAvailability da = new DoctorAvailability();
        da.setDoctor(d);
        da.setAvailableDate(Date.valueOf("2022-01-30"));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Time t = Time.valueOf(dtf.format(now));
        LocalDateTime endTime = now.plusHours(5);
        Time t2 = Time.valueOf(dtf.format(endTime));
        da.setStartTime(t);
        da.setEndTime(t2);
        doctorAvailabilityRepository.save(da);
        List<DoctorAvailability> list = doctorAvailabilityRepository.getAvailableTimes(1);
        assertThat(list.size()).isNotEqualTo(0);


    }

    @Test
    void getAvailableTimeOfDoctorBasedOnDate() {
        User user = new User("Doctor", "doctor 1", "male", "236541789","test@mail.com","12344", true, "doctor");
        user.setId(1);
        Doctor d = new Doctor();
        d.setDoctor_id(1);
        user.setDoctor(d);
        User savedUser = userRepository.save(user);
        DoctorAvailability da = new DoctorAvailability();
        da.setDoctor(d);
        da.setAvailableDate(Date.valueOf("2022-01-30"));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Time t = Time.valueOf(dtf.format(now));
        LocalDateTime endTime = now.plusHours(5);
        Time t2 = Time.valueOf(dtf.format(endTime));
        da.setStartTime(t);
        da.setEndTime(t2);
        doctorAvailabilityRepository.save(da);
        List<DoctorAvailability> list  = doctorAvailabilityRepository.getAvailableTimeOfDoctorBasedOnDate(savedUser.getDoctor().getDoctor_id(),Date.valueOf("2022-01-30"));
    }

    @Test
    void updateBookedAppointment() {
    }
}
