package com.group6.careu.RestControllers;

import com.group6.careu.entity.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppointmentRestControllers {

//    @GetMapping("/searchDoctors")
//    public List<User> getAllDoctors(Model model) {
//        List<User> doctorList = doctorService.getAllDoctor();
//        System.out.println("printing doctor 1" + doctorList.get(0).getDoctor().getLicense_number());
//        model.addAttribute("doctorList", doctorList);
//        return doctorList;
//    }

}
