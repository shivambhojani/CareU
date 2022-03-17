/*
 *  Created by Shivam Bhojani
 */
package com.group6.careu.RestControllers;

import com.group6.careu.entity.Appointment;
import com.group6.careu.entity.Patient;
import com.group6.careu.entity.User;
import com.group6.careu.model.AppointmentModel;
import com.group6.careu.model.PatientAppointmentModel;
import com.group6.careu.model.PatientSettingsModel;
import com.group6.careu.repository.PatientRepository;
import com.group6.careu.repository.UserRepository;
import com.group6.careu.security.CareuUserDetails;
import com.group6.careu.service.AppointmentServiceImpl;
import com.group6.careu.service.PatientServiceImpl;
import com.group6.careu.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@RestController
public class PatientRestControllers {

    @Autowired
    PatientServiceImpl patientServiceImpl;

    @Autowired
    AppointmentServiceImpl appointmentServiceImpl;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    UserRepository repository;

    @GetMapping(path = "/patienthomepage/{id}")
    public User getAllPatientsRest(Model model, @PathVariable("id") Integer id) {
        return patientServiceImpl.getPatientbyID(id);
    }


    @PutMapping("/updatePatient/{id}")
    public Integer updatePatient(@PathVariable("id") Integer id, @RequestBody PatientSettingsModel patientSettingsModel) {
        User user = new User();
        user.setId(id);
        String firstName = patientSettingsModel.getFirstName();
        String lastName = patientSettingsModel.getLastName();
        String phone = patientSettingsModel.getPhone();
        String gender = patientSettingsModel.getGender();
        String email = patientSettingsModel.getEmail();
        String disease = patientSettingsModel.getDisease();
        Integer a = patientRepository.updatePatientData(id, firstName, lastName, phone, gender);
        Integer b = patientRepository.updatePatientDatainPatient(id, disease);
        if (a==1 && b==1)
        {
            return 1;
        }
        else {
            return 0;
        }
    }

    @GetMapping(path = "/getTodaysAppointmentbyPatientId/{PatientId}")
    public List<PatientAppointmentModel> getTodaysAppointmentByPatientId(@PathVariable("PatientId") Integer id) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        Date dateToday = Date.valueOf(dtf.format(now));

        System.out.println(id);
        List<PatientAppointmentModel> patientAppointmentModels = new ArrayList<>();
        List<Appointment> appointments = appointmentServiceImpl.getTodaysPatientAppointments(id, dateToday);
        for (int i = 0 ; i< appointments.size(); i++){
            PatientAppointmentModel p = new PatientAppointmentModel();
            int doctorId = appointments.get(i).getDoctor().getDoctor_id();
            User u  = repository.getUserByDoctorId(doctorId);
            p.setDoctorName(u.getFirstName() + " " + u.getLastName());
            p.setMedications(appointments.get(i).getMedications());
            p.setConsultationType(appointments.get(i).getConsulationType());
            p.setDate(appointments.get(i).getAppointment_date());
            p.setEnd_time(appointments.get(i).getEndTime());
            p.setStart_time(appointments.get(i).getStartTime());
            p.setPatient_id(appointments.get(i).getPatient().getPatient_id());

            patientAppointmentModels.add(p);
        }
        return patientAppointmentModels;
    }


    @GetMapping(path = "/getFutureAppointmentbyPatientId/{PatientId}")
    public List<PatientAppointmentModel> getFutureAppointmentByPatientId(@PathVariable("PatientId") Integer id) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        Date dateToday = Date.valueOf(dtf.format(now));

        System.out.println(id);
        List<PatientAppointmentModel> patientAppointmentModels = new ArrayList<>();
        List<Appointment> appointments = appointmentServiceImpl.getPatientFutureAppointments(id, dateToday);
        for (int i = 0 ; i< appointments.size(); i++){
            PatientAppointmentModel p = new PatientAppointmentModel();
            int doctorId = appointments.get(i).getDoctor().getDoctor_id();
            User u  = repository.getUserByDoctorId(doctorId);
            p.setDoctorName(u.getFirstName() + " " + u.getLastName());
            p.setMedications(appointments.get(i).getMedications());
            p.setConsultationType(appointments.get(i).getConsulationType());
            p.setDate(appointments.get(i).getAppointment_date());
            p.setEnd_time(appointments.get(i).getEndTime());
            p.setStart_time(appointments.get(i).getStartTime());
            p.setPatient_id(appointments.get(i).getPatient().getPatient_id());

            patientAppointmentModels.add(p);
        }
        return patientAppointmentModels;
    }


    @GetMapping(path = "/getPastAppointmentbyPatientId/{PatientId}")
    public List<PatientAppointmentModel> getPastAppointmentByPatientId(@PathVariable("PatientId") Integer id) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        Date dateToday = Date.valueOf(dtf.format(now));

        System.out.println(id);
        List<PatientAppointmentModel> patientAppointmentModels = new ArrayList<>();
        List<Appointment> appointments = appointmentServiceImpl.getPatientPastAppointments(id, dateToday);
        for (int i = 0 ; i< appointments.size(); i++){
            PatientAppointmentModel p = new PatientAppointmentModel();
            int doctorId = appointments.get(i).getDoctor().getDoctor_id();
            User u  = repository.getUserByDoctorId(doctorId);
            p.setDoctorName(u.getFirstName() + " " + u.getLastName());
            p.setMedications(appointments.get(i).getMedications());
            p.setConsultationType(appointments.get(i).getConsulationType());
            p.setDate(appointments.get(i).getAppointment_date());
            p.setEnd_time(appointments.get(i).getEndTime());
            p.setStart_time(appointments.get(i).getStartTime());
            p.setPatient_id(appointments.get(i).getPatient().getPatient_id());

            patientAppointmentModels.add(p);
        }
        return patientAppointmentModels;
    }


    }
