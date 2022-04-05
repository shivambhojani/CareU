package com.group6.careu.controller;

import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.User;
import com.group6.careu.entity.UserDocument;
import com.group6.careu.security.CareuUserDetails;
import com.group6.careu.service.DoctorService;
import com.group6.careu.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class DoctorProfileController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping("/doctorProfile")
    public String showDoctorProfilePage(Model model,@AuthenticationPrincipal CareuUserDetails loggedUser) {
        String email = loggedUser.getUsername();
        User user = userServiceImpl.getByEmail(email);
        Integer userId = user.getId();
        user = doctorService.getDoctorById(userId);

        Optional<Doctor> doctor = doctorService.getDoctorDetailsById(user.getDoctor().getDoctor_id());
        model.addAttribute("doctor",doctor.get());
        return "doctor_profile";
    }

    @PostMapping("/doctorProfile")
    public String getDoctorProfileAttributes(@ModelAttribute Doctor doctor, Model model){
        model.addAttribute("doctor", doctor);
        //System.out.println("Model: "+model);
        doctorService.saveDoctorInformation(doctor);
        return "redirect:/licenseUpload";
    }

    @GetMapping("/licenseUpload")
    public String uploadLicenseUpload(Model model) {
        model.addAttribute("file",new UserDocument());
        return "doctorlicense";
    }
}
