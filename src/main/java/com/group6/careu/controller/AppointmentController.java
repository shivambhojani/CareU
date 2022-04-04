package com.group6.careu.controller;

import com.group6.careu.Utility;
import com.group6.careu.constants.ApplicationConstants;
import com.group6.careu.entity.*;
import com.group6.careu.model.AppointmentModel;
import com.group6.careu.model.DoctorAvailabilityModel;
import com.group6.careu.model.PatientAppointmentModel;
import com.group6.careu.repository.UserRepository;
import com.group6.careu.security.CareuUserDetails;
import com.group6.careu.service.*;
import com.group6.careu.setting.EmailSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sai Chinthirla on 03, 2022
 */

@Controller
public class AppointmentController {

    @Autowired
    PatientServiceImpl patientServiceImpl;

    @Autowired
    DoctorService doctorService;

    @Autowired
    DoctorAvailabilityService doctorAvailabilityService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    UserRepository repository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    EmailService emailService;

    public AppointmentController(AppointmentService appointmentService) {
    }

    @GetMapping("/searchDoctors")
    public String getAllDoctors(Model model, String keyword) {
        List<User> doctorList = new ArrayList<>();
        if (keyword!=null && !keyword.isEmpty()){
            doctorList = doctorService.getFilteredDoctor(keyword);
        }
        else if (keyword == null || keyword.trim().length()==0){
            doctorList = doctorService.getAllDoctor();
        }
        model.addAttribute("doctorList", doctorList);
        return ApplicationConstants.DOCTOR_LIST_VIEWPAGE;
    }

    @GetMapping("/searchDoctors/{doctor_id}")
    public String bookAnAppointment(@PathVariable("doctor_id") Integer doctor_id, Model model) {
        User doctor = doctorService.getUserByDoctor(doctor_id);
        List<DoctorAvailability> availTimes = doctorAvailabilityService.getAllTimes(doctor_id);
        Appointment appointment = new Appointment();
        AppointmentModel appointmentModel = new AppointmentModel();
        model.addAttribute("appointment", appointmentModel);
        model.addAttribute("selectedDoctor", doctor);
        model.addAttribute("availTimes", availTimes);
        return ApplicationConstants.BOOK_APPOINTMENT_VIEWPAGE;
    }

    @RequestMapping(value = "/searchDoctors/get-times", method = RequestMethod.POST)
    public @ResponseBody List<DoctorAvailability> getAppointmentTimesBasedOnDate(@RequestBody DoctorAvailabilityModel doctorAvailability, Model model) {
        String date = doctorAvailability.getAvailableDate().get(0).replaceFirst("=", "");
        User doctor = doctorService.getDoctorById(doctorAvailability.getDoctorId());
        AppointmentModel appointmentModel = new AppointmentModel();
        model.addAttribute("appointment", appointmentModel);
        model.addAttribute("selectedDoctor", doctor);
        List<DoctorAvailability> availabilityList = doctorAvailabilityService.getAvailableTimesOfDoctor(doctorAvailability);
        model.addAttribute("availTimes", availabilityList);
        return availabilityList;
    }

    @PostMapping("/book")
    public String bookAppointment(Model model, @ModelAttribute("appointmentModel") AppointmentModel appointmentModel, HttpServletRequest httpServletRequest) throws MessagingException, UnsupportedEncodingException {
        Appointment appt = appointmentService.pushPatientAppointment(appointmentModel);
        Appointment appointment = (Appointment) httpServletRequest.getSession().getAttribute("Appointment_Session");
        appointment = appt;
        httpServletRequest.getSession().setAttribute("Appointment_Session", appointment);
        doctorAvailabilityService.updateBookedAppointment(appointmentModel, true);
        model.addAttribute("appointment_id", appt.getAppointmentId().toString());
        return ApplicationConstants.PAYMENT_LATEST_REDIRECT;
    }

    private void sendEmailForAppointment(String email, String content, boolean isBookingAppointment) throws MessagingException, UnsupportedEncodingException {
        EmailSetting emailSettings = new EmailSetting();
        JavaMailSenderImpl mailSender = Utility.prepareMailSender(emailSettings);
        String subject = "";
        if (isBookingAppointment) {
            subject = ApplicationConstants.APPOINTMENT_BOOKED;
        } else {
            subject = ApplicationConstants.APPOINTMENT_CANCELLED;
        }
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(emailSettings.getMAIL_FROM(), emailSettings.getMAIL_SENDER_NAME());
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }

    @GetMapping("/cancelAppointment")
    public String getCancelAppointment (Model model, @AuthenticationPrincipal CareuUserDetails loggedUser){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        Date dateToday = Date.valueOf(dtf.format(now));
        User user = userServiceImpl.getByEmail(loggedUser.getUsername());
        Integer userId = user.getId();
        user = patientServiceImpl.getPatientbyID(userId);
        model.addAttribute("patient", user);
        List<PatientAppointmentModel> patientFutureAppointmentModels = getFutureAppointments(user, user.getPatient().getPatient_id(), dateToday);
        model.addAttribute("patientFutureAppointments", patientFutureAppointmentModels);
        return ApplicationConstants.CANCEL_APPOINTMENT_VIEWPAGE;
    }

    public List<PatientAppointmentModel> getFutureAppointments (User user, Integer patientId, Date todaysDate){
        List<PatientAppointmentModel> patientFurtureAppointmentModels = new ArrayList<>();
        List<Appointment> appointments = appointmentService.getPatientFutureAppointments(user.getPatient().getPatient_id(), todaysDate);
        patientFurtureAppointmentModels = fetchAppointmentDetails(appointments);
        return patientFurtureAppointmentModels;
    }

    public List<PatientAppointmentModel> fetchAppointmentDetails (List < Appointment > appointments) {
        List<PatientAppointmentModel> patientAppointmentModels = new ArrayList<>();
        for (int i = 0; i < appointments.size(); i++) {
            PatientAppointmentModel p = new PatientAppointmentModel();
            p.setAppointment_id(appointments.get(i).getAppointmentId());
            int doctorId = appointments.get(i).getDoctor().getDoctor_id();
            User u = repository.getUserByDoctorId(doctorId);
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

    @GetMapping("/cancelAppointment/{apptId}/{date}/{time}/{endTime}")
    public String cancelCurrentAppointmentOfUser (@PathVariable("apptId") String apptId, @PathVariable("date") String date, @PathVariable("time") String time, @PathVariable String endTime) throws MessagingException, UnsupportedEncodingException {
        Appointment appointment = appointmentService.deleteAppointmentBasedOnId(apptId);
        doctorAvailabilityService.updateBookedAppointment(setAppointmentModelForUpdating(date, time, endTime, appointment), false);
        CareuUserDetails careuUserDetails = (CareuUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        emailService.sendEmailForAppointment(careuUserDetails.getUsername(), emailService.getContentForCancelAppointment(appointment), false);
        return ApplicationConstants.PATIENT_HOMEPAGE_REDIRECT;
    }

    private AppointmentModel setAppointmentModelForUpdating (String date, String startTime, String endTime, Appointment appointment){
        AppointmentModel appointmentModel = new AppointmentModel();
        String time = startTime + " to " + endTime;
        appointmentModel.setAppointment_date(Date.valueOf(date));
        appointmentModel.setTime(time);
        appointmentModel.setDoctor_id(appointment.getDoctor().getDoctor_id());
        return appointmentModel;
    }
}
