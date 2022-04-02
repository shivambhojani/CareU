/*
 *  Created by Shivam Bhojani
 */
package com.group6.careu.controller;

import com.group6.careu.entity.Appointment;
import com.group6.careu.entity.Patient;
import com.group6.careu.entity.User;
import com.group6.careu.model.PatientAppointmentModel;
import com.group6.careu.model.PatientSettingsModel;
import com.group6.careu.repository.PatientRepository;
import com.group6.careu.repository.UserRepository;
import com.group6.careu.security.CareuUserDetails;
import com.group6.careu.service.AppointmentServiceImpl;
import com.group6.careu.service.PatientServiceImpl;
import com.group6.careu.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
public class PatientController {

    @Autowired
    PatientServiceImpl patientServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    UserRepository repository;

    @Autowired
    AppointmentServiceImpl appointmentServiceImpl;

    //Used for fetching information for patient homepage.
    //It extracts patient basic details and also the appointment details for the logged in patient
    @GetMapping(path = "/patienthomepage")
    public String getPatient(Model model, Integer id, @AuthenticationPrincipal CareuUserDetails loggedUser) {

        String email = loggedUser.getUsername();
        User user = userServiceImpl.getByEmail(email);
        Integer userId = user.getId();
        user = patientServiceImpl.getPatientbyID(userId);
        model.addAttribute("patient", user);

        List<PatientAppointmentModel> patientTodaysAppointmentModels = new ArrayList<>();
        List<PatientAppointmentModel> patientFutureAppointmentModels = new ArrayList<>();
        List<PatientAppointmentModel> patientPastAppointmentModels = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        Date dateToday = Date.valueOf(dtf.format(now));

        patientTodaysAppointmentModels = getTodaysAppointments(user, user.getPatient().getPatient_id(), dateToday);
        patientFutureAppointmentModels = getFutureAppointments(user, user.getPatient().getPatient_id(), dateToday);
        patientPastAppointmentModels = getPastAppointments(user, user.getPatient().getPatient_id(), dateToday);

        model.addAttribute("patientTodaysAppointments", patientTodaysAppointmentModels);
        model.addAttribute("patientPastAppointments", patientPastAppointmentModels);
        model.addAttribute("patientFutureAppointments", patientFutureAppointmentModels);

        return "patient";
    }


    //Controller used for getting the patient details on patient profile page
    //It displays various types of information for patient which they can edit from the UI
    @GetMapping(path = "/patientProfile")
    public String getPatientDetails(Model model, @AuthenticationPrincipal CareuUserDetails loggedUser) {
        String email = loggedUser.getUsername();
        User user = userServiceImpl.getByEmail(email);
        Integer userId = user.getId();
        user = patientServiceImpl.getPatientbyID(userId);

        PatientSettingsModel patientSettingsModel = new PatientSettingsModel();
        patientSettingsModel.setFirstName(user.getFirstName());
        patientSettingsModel.setLastName(user.getLastName());
        patientSettingsModel.setEmail(user.getEmail());
        patientSettingsModel.setGender(user.getGender());
        patientSettingsModel.setPhone(user.getPhone());
        patientSettingsModel.setDisease(user.getPatient().getDisease());

        model.addAttribute("patientDetails", patientSettingsModel);

        return "profile_patient";
    }


    //This controller is used to save the latest edits done by the patients on patient profile page
    //This controller is called from the patientprofile.html
    @PostMapping("/updatePatient")
    public String updatePatient(@AuthenticationPrincipal CareuUserDetails loggedUser, PatientSettingsModel patientSettingsModel,
                                RedirectAttributes redirectAttributes) {
        String email = loggedUser.getUsername();
        User user = userServiceImpl.getByEmail(email);
        Integer userId = user.getId();
        user.setId(userId);

        String firstName = patientSettingsModel.getFirstName();
        String lastName = patientSettingsModel.getLastName();
        String phone = patientSettingsModel.getPhone();
        String gender = patientSettingsModel.getGender();
        String disease = patientSettingsModel.getDisease();

        Integer i = patientRepository.updatePatientData(userId, firstName, lastName, phone, gender);
        Integer j = patientRepository.updatePatientDatainPatient(loggedUser.getPatientId(), disease);

        if (i == 1 && j == 1) {
            redirectAttributes.addFlashAttribute("success", "Details Updated Successfully.");
        }

        return "redirect:/patientProfile";
    }


    @PostMapping("/updatePatientFeedback/{appointment_id}")
    public String postPatientFeedback(@PathVariable(name = "appointment_id") UUID appointment_id, PatientAppointmentModel patientAppointmentModel,
                                      RedirectAttributes redirectAttributes) {

        String patientFeedback = patientAppointmentModel.getPatientFeedback();
        Integer i = appointmentServiceImpl.updatePatientFeedback(patientAppointmentModel.getAppointment_id(), patientFeedback);
        if (i == 1) {
            redirectAttributes.addFlashAttribute("success", "FeedBack Submitted Successfully.");
        }
        return "redirect:/patientFeedback/" + appointment_id;

    }

    //This controller gets all the appointment related details on the UI.
    //These details are displayed when patient clicks on feed back button
    @GetMapping(path = "/patientFeedback/{appointment_id}")
    public String getAppointmentDetails(@PathVariable(name = "appointment_id") UUID appointment_id, Model model) {
        PatientAppointmentModel patientAppointmentModelsByID = new PatientAppointmentModel();
        Appointment singleAppointmentbyID = appointmentServiceImpl.getAppointmentsByAppointmentId(appointment_id);

        patientAppointmentModelsByID.setAppointment_id(appointment_id);
        patientAppointmentModelsByID.setPatient_id(singleAppointmentbyID.getPatient().getPatient_id());

        int doctorId = singleAppointmentbyID.getDoctor().getDoctor_id();
        User u = repository.getUserByDoctorId(doctorId);
        patientAppointmentModelsByID.setDoctorName(u.getFirstName() + " " + u.getLastName());

        patientAppointmentModelsByID.setDate(singleAppointmentbyID.getAppointment_date());
        patientAppointmentModelsByID.setConsultationType(singleAppointmentbyID.getConsulationType());
        patientAppointmentModelsByID.setStart_time(singleAppointmentbyID.getStartTime());
        patientAppointmentModelsByID.setEnd_time(singleAppointmentbyID.getEndTime());
        patientAppointmentModelsByID.setPatientFeedback(singleAppointmentbyID.getPatientFeedback());
        patientAppointmentModelsByID.setMedications(singleAppointmentbyID.getMedications());

        model.addAttribute("appointmentModel", patientAppointmentModelsByID);
        return "appointmentfeedback";
    }

    public List<PatientAppointmentModel> getTodaysAppointments(User user, Integer patientId, Date todaysDate) {
        List<PatientAppointmentModel> patientTodaysAppointmentModels = new ArrayList<>();
        List<Appointment> appointments = appointmentServiceImpl.getTodaysPatientAppointments(user.getPatient().getPatient_id(), todaysDate);
        patientTodaysAppointmentModels = fetchAppointmentDetails(appointments);
        return patientTodaysAppointmentModels;
    }

    public List<PatientAppointmentModel> getPastAppointments(User user, Integer patientId, Date todaysDate) {
        List<PatientAppointmentModel> patientPastAppointmentModels = new ArrayList<>();
        List<Appointment> appointments = appointmentServiceImpl.getPatientPastAppointments(user.getPatient().getPatient_id(), todaysDate);
        patientPastAppointmentModels = fetchAppointmentDetails(appointments);
        return patientPastAppointmentModels;
    }

    public List<PatientAppointmentModel> getFutureAppointments(User user, Integer patientId, Date todaysDate) {
        List<PatientAppointmentModel> patientFurtureAppointmentModels = new ArrayList<>();
        List<Appointment> appointments = appointmentServiceImpl.getPatientFutureAppointments(user.getPatient().getPatient_id(), todaysDate);
        patientFurtureAppointmentModels = fetchAppointmentDetails(appointments);
        return patientFurtureAppointmentModels;
    }

    //This method transforms the appointment details from the appointment List to PatientAppointmentModel.
    //PatientAppointmentModel is passed to UI, using which the patient appointment details are shown in the UI
    public List<PatientAppointmentModel> fetchAppointmentDetails(List<Appointment> appointments) {
        List<PatientAppointmentModel> patientAppointmentModels = new ArrayList<>();
        for (int i = 0; i < appointments.size(); i++) {
            PatientAppointmentModel p = new PatientAppointmentModel();
            int doctorId = appointments.get(i).getDoctor().getDoctor_id();
            User u = repository.getUserByDoctorId(doctorId);
            p.setAppointment_id(appointments.get(i).getAppointmentId());
            p.setDoctorName(u.getFirstName() + " " + u.getLastName());
            p.setMedications(appointments.get(i).getMedications());
            p.setConsultationType(appointments.get(i).getConsulationType());
            p.setDate(appointments.get(i).getAppointment_date());
            p.setEnd_time(appointments.get(i).getEndTime());
            p.setStart_time(appointments.get(i).getStartTime());
            p.setPatient_id(appointments.get(i).getPatient().getPatient_id());
            patientAppointmentModels.add(p);
        }
        return patientAppointmentModels;
    }

}
