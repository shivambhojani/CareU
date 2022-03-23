package com.group6.careu.controller;

import com.group6.careu.entity.User;
import com.group6.careu.security.CareuUserDetails;
import com.group6.careu.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Bijitashya on 03, 2022
 */
@Controller
public class ProfileController {
//    @Autowired
    private UserServiceImpl userServiceImpl;

    public ProfileController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/profile")
    public String editProfile(Model model, @AuthenticationPrincipal CareuUserDetails loggedUser){
        String email = loggedUser.getUsername();
        User user = userServiceImpl.getByEmail(email);
        String role = loggedUser.getRole();
        if(role.equalsIgnoreCase("doctor")){
            return "profile_doctor";
        } else{
            return "profile_patient";
        }

    }
}
