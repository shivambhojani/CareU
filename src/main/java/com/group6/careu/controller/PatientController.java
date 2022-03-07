/*
*  Created by Shivam Bhojani
*/
package com.group6.careu.controller;

import com.group6.careu.entity.Patient;
import com.group6.careu.entity.User;
import com.group6.careu.model.PatientSettingsModel;
import com.group6.careu.repository.PatientRepository;
import com.group6.careu.security.CareuUserDetails;
import com.group6.careu.service.PatientServiceImpl;
import com.group6.careu.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class PatientController {

    @Autowired
    PatientServiceImpl patientServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping(path = "/patienthomepage")
    public String getAllPatients(Model model, Integer id, @AuthenticationPrincipal CareuUserDetails loggedUser) {
       //List<BookAppointment> appointments = bookAppointmentService.getAppointmentsByPatientId(id);
        String email = loggedUser.getUsername();
        User user = userServiceImpl.getByEmail(email);
        Integer userId = user.getId();
        user = patientServiceImpl.getPatientbyID(userId);
        model.addAttribute("patient", user);
        //model.addAttribute("appointments", appointments);
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
        patientRepository.updatePatientDatainPatient(userId, disease);

        return "redirect:/patientProfile";
    }

}
