package com.group6.careu.controller;

import com.group6.careu.entity.Appointment;
import com.group6.careu.entity.User;
import com.group6.careu.model.DoctorAppointmentModel;
import com.group6.careu.model.PatientAppointmentModel;
import com.group6.careu.repository.AppointmentRepository;
import com.group6.careu.repository.UserRepository;
import com.group6.careu.security.CareuUserDetails;
import com.group6.careu.service.AppointmentService;
import com.group6.careu.service.DoctorService;
import com.group6.careu.service.DoctorServiceImpl;
import com.group6.careu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class DoctorHomePageController {

    @Autowired
    UserService userService;

    @Autowired
    DoctorServiceImpl doctorServiceImpl;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/doctorhomepage")
    public String getAllDoctors(Model model,@AuthenticationPrincipal CareuUserDetails loggedUser) {
        //List<BookAppointment> appointments = bookAppointmentService.getAppointmentsByPatientId(id);

        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        Integer userId = user.getId();
        user = doctorServiceImpl.getDoctorById(userId);
        System.out.println("User: "+ user);
        model.addAttribute("doctor", user);

        List<DoctorAppointmentModel> doctorTodaysAppointmentModels;
        List<DoctorAppointmentModel> doctorFutureAppointmentModels;
        List<DoctorAppointmentModel> doctorPastAppointmentModels;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        Date dateToday = Date.valueOf(dtf.format(now));
        System.out.println("Today's date: "+dateToday);



        doctorTodaysAppointmentModels = getTodaysAppointments(user, user.getDoctor().getDoctor_id(), dateToday);

        for(DoctorAppointmentModel today_model:doctorTodaysAppointmentModels){
            System.out.println(today_model);
        }

        doctorFutureAppointmentModels = getFutureAppointments(user, user.getDoctor().getDoctor_id(), dateToday);
        for(DoctorAppointmentModel future_model:doctorTodaysAppointmentModels){
            System.out.println(future_model);
        }

        doctorPastAppointmentModels = getPastAppointments(user, user.getDoctor().getDoctor_id(), dateToday);
        for(DoctorAppointmentModel past_model:doctorTodaysAppointmentModels){
            System.out.println(past_model);
        }

        model.addAttribute("doctorTodaysAppointmentModels", doctorTodaysAppointmentModels);
        model.addAttribute("doctorPastAppointmentModels",doctorPastAppointmentModels);
        model.addAttribute("doctorFutureAppointmentModels", doctorFutureAppointmentModels);


        return "doctor";
    }

    @GetMapping(path = "/doctorPrescription/{appointment_id}")
    public String getDoctorAppointmentDetails(@PathVariable(name = "appointment_id") UUID appointment_id, Model model) {
        PatientAppointmentModel patientAppointmentModelsByID = new PatientAppointmentModel();
        Appointment singleAppointmentbyID = appointmentService.getAppointmentsByAppointmentId(appointment_id);

        patientAppointmentModelsByID.setAppointment_id(appointment_id);
        patientAppointmentModelsByID.setPatient_id(singleAppointmentbyID.getPatient().getPatient_id());

        int doctorId = singleAppointmentbyID.getDoctor().getDoctor_id();
        User u = userRepository.getUserByDoctor(doctorId);
        patientAppointmentModelsByID.setDoctorName(u.getFirstName() + " " + u.getLastName());

        patientAppointmentModelsByID.setDate(singleAppointmentbyID.getAppointment_date());
        patientAppointmentModelsByID.setConsultationType(singleAppointmentbyID.getConsulationType());
        patientAppointmentModelsByID.setStart_time(singleAppointmentbyID.getStartTime());
        patientAppointmentModelsByID.setEnd_time(singleAppointmentbyID.getEndTime());
        patientAppointmentModelsByID.setPatientFeedback(singleAppointmentbyID.getPatientFeedback());
        patientAppointmentModelsByID.setMedications(singleAppointmentbyID.getMedications());

        model.addAttribute("appointmentModel", patientAppointmentModelsByID);
        return "doctorprescription";
    }

    @PostMapping("/updateDoctorPrescription/{appointment_id}")
    public String postMedications(@PathVariable(name = "appointment_id") UUID appointment_id, PatientAppointmentModel patientAppointmentModel,
                                  RedirectAttributes redirectAttributes) {

        String medications = patientAppointmentModel.getMedications();
        Integer i = appointmentService.updateMedication(patientAppointmentModel.getAppointment_id(), medications);
        if (i == 1) {
            redirectAttributes.addFlashAttribute("success", "Medications Submitted Successfully.");
        }
        return "redirect:/doctorPrescription/" + appointment_id;
    }

    public List<DoctorAppointmentModel> getTodaysAppointments(User user, Integer doctorId, Date todaysDate) {
        List<DoctorAppointmentModel> doctorTodaysAppointmentModels;
        List<Appointment> appointments = appointmentService.getDoctorTodaysAppointments(user.getDoctor().getDoctor_id(), todaysDate);
        doctorTodaysAppointmentModels = fetchAppointmentDetails(appointments);
        return doctorTodaysAppointmentModels;
    }

    public List<DoctorAppointmentModel> getPastAppointments(User user, Integer doctorId, Date todaysDate) {
        List<DoctorAppointmentModel> doctorPastAppointmentModels;
        List<Appointment> appointments = appointmentService.getDoctorPastAppointments(user.getDoctor().getDoctor_id(), todaysDate);
        doctorPastAppointmentModels = fetchAppointmentDetails(appointments);
        return doctorPastAppointmentModels;
    }

    public List<DoctorAppointmentModel> getFutureAppointments(User user, Integer doctorId, Date todaysDate) {
        List<DoctorAppointmentModel> doctorFurtureAppointmentModels;
        List<Appointment> appointments = appointmentService.getDoctorFutureAppointments(user.getDoctor().getDoctor_id(), todaysDate);
        doctorFurtureAppointmentModels = fetchAppointmentDetails(appointments);
        return doctorFurtureAppointmentModels;
    }

    public List<DoctorAppointmentModel> fetchAppointmentDetails(List<Appointment> appointments)
    {
        List<DoctorAppointmentModel> doctorAppointmentModels = new ArrayList<>();
        for (int i = 0; i < appointments.size(); i++) {
            DoctorAppointmentModel d = new DoctorAppointmentModel();
            int patientId = appointments.get(i).getPatient().getPatient_id();
            System.out.println("Appointment: "+appointments.get(i));
            System.out.println("Patient ID: "+patientId);
            User u = userRepository.getUserByPatientId(patientId);
            d.setPatientName(u.getFirstName() + " " + u.getLastName());
            d.setMedications(appointments.get(i).getMedications());
            d.setAppointment_id(appointments.get(i).getAppointmentId());
            d.setConsultationType(appointments.get(i).getConsulationType());
            d.setDate(appointments.get(i).getAppointment_date());
            d.setEnd_time(appointments.get(i).getEndTime());
            d.setStart_time(appointments.get(i).getStartTime());
            d.setPatient_id(appointments.get(i).getPatient().getPatient_id());
            doctorAppointmentModels.add(d);
        }
        return doctorAppointmentModels;
    }



}
