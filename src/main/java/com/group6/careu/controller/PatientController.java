/*
*  Created by Shivam Bhojani
*/
package com.group6.careu.controller;

import com.group6.careu.entity.Appointment;
import com.group6.careu.entity.Patient;
import com.group6.careu.entity.User;
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
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PatientController {

    @Autowired
    PatientServiceImpl patientServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    UserRepository repository;

    @Autowired
    AppointmentServiceImpl appointmentServiceImpl;

    @GetMapping(path = "/patienthomepage")
    public String getAllPatients(Model model, Integer id, @AuthenticationPrincipal CareuUserDetails loggedUser) {
       //List<BookAppointment> appointments = bookAppointmentService.getAppointmentsByPatientId(id);
        String email = loggedUser.getUsername();
        User user = userServiceImpl.getByEmail(email);
        Integer userId = user.getId();
        user = patientServiceImpl.getPatientbyID(userId);
        model.addAttribute("patient", user);


        List<PatientAppointmentModel> patientAppointmentModels = new ArrayList<>();
        List<Appointment> appointments = appointmentServiceImpl.getPatientAppointments(user.getPatient().getPatient_id());
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

        model.addAttribute("patientAppointments", patientAppointmentModels);
        System.out.println("modelSize = " + patientAppointmentModels.size());
        return "patient";
    }

    @GetMapping(path = "/patientProfile")
    public String getPatientDetails(Model model, @AuthenticationPrincipal CareuUserDetails loggedUser){
        String email = loggedUser.getUsername();
        User user = userServiceImpl.getByEmail(email);
        Integer userId = user.getId();
        user = patientServiceImpl.getPatientbyID(userId);

        PatientSettingsModel patientSettingsModel = new PatientSettingsModel();
        patientSettingsModel.setFirstName(user.getFirstName());
        patientSettingsModel.setLastName(user.getLastName());
        patientSettingsModel.setEmail(user.getEmail());
        patientSettingsModel.setGender(user.getGender());
        patientSettingsModel.setPhone(user.getPhone());
        patientSettingsModel.setDisease(user.getPatient().getDisease());

        model.addAttribute("patientDetails", patientSettingsModel);

        return "profile_patient";
    }

    @PostMapping("/updatePatient")
    public String updatePatient(@AuthenticationPrincipal CareuUserDetails loggedUser, PatientSettingsModel patientSettingsModel,
        RedirectAttributes redirectAttributes){
        String email = loggedUser.getUsername();
        User user = userServiceImpl.getByEmail(email);
        Integer userId = user.getId();
        user.setId(userId);

        String firstName = patientSettingsModel.getFirstName();
        String lastName = patientSettingsModel.getLastName();
        String phone = patientSettingsModel.getPhone();
        String gender = patientSettingsModel.getGender();
        String disease = patientSettingsModel.getDisease();



        patientRepository.updatePatientData(userId, firstName, lastName, phone, gender);
        patientRepository.updatePatientDatainPatient(loggedUser.getPatientId(), disease);

        return "redirect:/patientProfile";
    }

    @GetMapping(path = "/getAppointmentbyPatientId/")
    public List<PatientAppointmentModel> getAppointmentByPatientId(Model model, @AuthenticationPrincipal CareuUserDetails loggedUser) {
        String email = loggedUser.getUsername();
        User user = userServiceImpl.getByEmail(email);
        Integer userId = user.getId();

        List<PatientAppointmentModel> patientAppointmentModels = new ArrayList<>();
        List<Appointment> appointments = appointmentServiceImpl.getPatientAppointments(userId);
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

        model.addAttribute("patientAppointments", patientAppointmentModels);
        return patientAppointmentModels;
    }

}
