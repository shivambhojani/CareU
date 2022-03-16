package com.group6.careu.controller;

import com.group6.careu.model.DoctorAvailabilityModel;
import com.group6.careu.service.DoctorAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DoctorAvailabilityController {

    @Autowired
    DoctorAvailabilityService doctorAvailabilityService;


    @GetMapping("/doctor-availability")
    public String showDcotorAvailability(Model model){
            model.addAttribute("doctoravailabilitymodel",new DoctorAvailabilityModel());
            return "doctoravailability";
    }

    @RequestMapping(value = "doctor-availability", method = RequestMethod.POST)
    public @ResponseBody ModelAndView saveObject (@RequestBody DoctorAvailabilityModel availableDate) {
        System.out.println("available date: " + availableDate);

        doctorAvailabilityService.saveAvailability(availableDate);
        return new ModelAndView("redirect:/doctor");
    }

}
