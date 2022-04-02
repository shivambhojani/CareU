package com.group6.careu.controller;

import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.UserDocument;
import com.group6.careu.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DoctorProfileController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctorProfile")
    public String showDoctorProfilePage(Model model) {
        model.addAttribute("doctor",new Doctor());
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
