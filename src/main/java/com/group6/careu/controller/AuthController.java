package com.group6.careu.controller;

import com.group6.careu.entity.User;
import com.group6.careu.exceptions.UserNotFoundException;
import com.group6.careu.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AuthController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("")
    public String showLandingPage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        return "register";
    }

    @PostMapping("/register/user")
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
        userServiceImpl.save(user);
        redirectAttributes.addFlashAttribute("message", "Registration Successful, Please login to continue.");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        return "login";
    }

    @PostMapping ("/login")
    public String loginUser(Model model) {
        User user = new User();
        model.addAttribute("user",user);
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

    @GetMapping("/admin")
    public String redirectToAdmin(Model model) {

        List<User> listUsers = userServiceImpl.listAll();
        model.addAttribute("listUsers",listUsers);
        return "admin";
    }

    @GetMapping("/users/{id}/enabled/{status}")
    public String updateUserEnabledStatus(@PathVariable("id") Integer id,
                                          @PathVariable("status") boolean enabled, RedirectAttributes redirectAttributes) {
        userServiceImpl.updateUserEnabledStatus(id, enabled);
        String status = enabled ? "enabled" : "disabled";
        String message = "The user ID " + id + " has been " + status;
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/admin";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Integer id,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        try {
            userServiceImpl.delete(id);;
            redirectAttributes.addFlashAttribute("message",
                    "The user ID " + id + " has been deleted successfully");
        } catch (UserNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
        }

        return "redirect:/admin";
    }


}
