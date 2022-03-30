package com.group6.careu.RestControllers;

import com.group6.careu.entity.Appointment;
import com.group6.careu.entity.User;
import com.group6.careu.service.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class AppointmentRestControllers {

    @Autowired
    AppointmentServiceImpl appointmentServiceImpl;

//    @GetMapping("/searchDoctors")
//    public List<User> getAllDoctors(Model model) {
//        List<User> doctorList = doctorService.getAllDoctor();
//        System.out.println("printing doctor 1" + doctorList.get(0).getDoctor().getLicense_number());
//        model.addAttribute("doctorList", doctorList);
//        return doctorList;
//    }

    @GetMapping(path = "/AppointmentById/{appointment_id}")
    public Appointment getPatientFeedback(@PathVariable(name="appointment_id") UUID appointment_id, Model model)
    {
        String a = appointment_id.toString();
        Appointment appointment;
        appointment = appointmentServiceImpl.getAppointmentsByAppointmentId(appointment_id);
        model.addAttribute("appointment", appointment);
        return appointment;
    }

}
