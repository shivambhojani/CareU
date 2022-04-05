package com.group6.careu.controller;

import com.group6.careu.entity.User;
import com.group6.careu.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by Bijitashya on 03, 2022
 */
@Controller

public class AuthController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    public AuthController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("")
    public String showLandingPage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/user")
    @ResponseBody
    public RedirectView saveUser(User user, RedirectAttributes redirectAttributes) {
        userServiceImpl.save(user);
        redirectAttributes.addFlashAttribute("message", "Registration Successful, Please login to continue.");
       return new RedirectView("/login", true);
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/doctor")
    public String redirectToDoctor() {
        return "doctor";
    }


    @GetMapping("/patient")
    public String redirectToPatient() {
        return "patient";
    }

    @GetMapping("/doctor/uploadDocuments/{id}")
    public String uploadUserDocuments(@PathVariable(name = "id") Integer id,
                                      Model model,
                                      RedirectAttributes redirectAttributes) {
        return "redirect:/getUploadedDocuments/1";
    }
}
