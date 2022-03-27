package com.group6.careu.controller;

import com.group6.careu.entity.*;
import com.group6.careu.model.AppointmentModel;
import com.group6.careu.model.DoctorAvailabilityModel;
import com.group6.careu.security.CareuUserDetails;
import com.group6.careu.service.AppointmentService;
import com.group6.careu.service.DoctorAvailabilityService;
import com.group6.careu.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppointmentController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    DoctorAvailabilityService doctorAvailabilityService;

    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/searchDoctors")
    public String getAllDoctors(Model model, String keyword) {
        List<User> doctorList = new ArrayList<>();
        if (keyword!=null && !keyword.isEmpty()){
            doctorList = doctorService.getFilteredDoctor(keyword);
        }
        else if (keyword == null || keyword.trim().length()==0){
            doctorList = doctorService.getAllDoctor();
        }
  //      System.out.println("printing doctor 1" + doctorList.get(0).getDoctor().getLicense_number());
        model.addAttribute("doctorList", doctorList);
        return "doctorlist";
    }

    @GetMapping("/searchDoctors/{doctor_id}")
    public String bookAnAppointment(@PathVariable("doctor_id") Integer doctor_id, Model model) {
        System.out.println("Doctor id " + doctor_id);
        User doctor = doctorService.getDoctorById(doctor_id);
        List<DoctorAvailability> availTimes = doctorAvailabilityService.getAllTimes(doctor_id);
        Appointment appointment = new Appointment();
        AppointmentModel appointmentModel = new AppointmentModel();
        model.addAttribute("appointment", appointmentModel);
        model.addAttribute("selectedDoctor", doctor);
        model.addAttribute("availTimes", availTimes);
        return "bookappointment";
    }

    @RequestMapping(value = "/searchDoctors/get-times", method = RequestMethod.POST)
    public @ResponseBody List<DoctorAvailability> getAppointmentTimesBasedOnDate(@RequestBody DoctorAvailabilityModel doctorAvailability, Model model) {
        System.out.println("selected appt date: " + doctorAvailability.getAvailableDate().get(0) + " wowow");
        String date = doctorAvailability.getAvailableDate().get(0).replaceFirst("=", "");
        System.out.println("Doctor id " + doctorAvailability.getDoctorId());
        System.out.println("Converted Date: " + Date.valueOf(date));
        User doctor = doctorService.getDoctorById(doctorAvailability.getDoctorId());
        AppointmentModel appointmentModel = new AppointmentModel();
        model.addAttribute("appointment", appointmentModel);
        model.addAttribute("selectedDoctor", doctor);
        List<DoctorAvailability> availabilityList = doctorAvailabilityService.getAvailableTimesOfDoctor(doctorAvailability);
        model.addAttribute("availTimes", availabilityList);
        return availabilityList;
    }

    @PostMapping("/book")
    public String bookAppointment(Model model, @ModelAttribute("appointmentModel") AppointmentModel appointmentModel) {
        Appointment appt = appointmentService.pushPatientAppointment(appointmentModel);
        model.addAttribute("appointment_id", appt.getAppointmentId().toString());
        return "successscreen";
    }
}
