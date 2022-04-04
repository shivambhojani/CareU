package com.group6.careu.controller;

import com.group6.careu.entity.Appointment;
import com.group6.careu.entity.UserDocument;
import com.group6.careu.model.AppointmentModel;
import com.group6.careu.model.Response.GenericResponseModel;
import com.group6.careu.model.TransactionsModel;
import com.group6.careu.security.CareuUserDetails;
import com.group6.careu.service.*;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.Date;

@Controller
@ToString
public class PaymentController {

    @Autowired
    private BankService bankService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorAvailabilityService doctorAvailabilityService;

    @Autowired
    EmailService emailService;

    @Autowired
    BillService billService;

    @GetMapping("/payment-latest")
    public String showLandingPage(Model model) {
        model.addAttribute("transactionsModel",new TransactionsModel());
        return "paymentlatest";
    }

    @PostMapping("/payment-latest")
    public String getPaymentAttributes(@ModelAttribute TransactionsModel transactionsModel, Model model, HttpServletRequest httpServletRequest) throws MessagingException, UnsupportedEncodingException {
        model.addAttribute("transactionsModel", transactionsModel);
        Appointment appointment = (Appointment) httpServletRequest.getSession().getAttribute("Appointment_Session");
        CareuUserDetails careuUserDetails = (CareuUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        boolean status =bankService.processPayment(transactionsModel);
        GenericResponseModel genericResponseModel = bankService.processPayment(transactionsModel);
        //System.out.println("debugggg" + genericResponseModel.getStatusCode());
        //System.out.println("debuugggg" + appointment);
        if(genericResponseModel==null){
            Appointment deletedAppointment = appointmentService.deleteAppointmentBasedOnId(appointment.getAppointmentId().toString());
            doctorAvailabilityService.updateBookedAppointment(setAppointmentModelForUpdating(appointment.getAppointment_date().toString(), appointment.getStartTime().toString(), appointment.getEndTime().toString(), appointment), false);
            return "failurescreen";
        }else if(null != genericResponseModel && genericResponseModel.getStatusCode() != 200){
            Appointment deletedAppointment = appointmentService.deleteAppointmentBasedOnId(appointment.getAppointmentId().toString());
            doctorAvailabilityService.updateBookedAppointment(setAppointmentModelForUpdating(appointment.getAppointment_date().toString(), appointment.getStartTime().toString(), appointment.getEndTime().toString(), appointment), false);
            return "failurescreen";
        } else {
            UserDocument userDocument = billService.billProcessor(careuUserDetails.getId(),genericResponseModel.getResponseId());
            emailService.sendEmailWithAttachment(userDocument, careuUserDetails.getUsername(), appointment);
            emailService.sendEmailForAppointment(careuUserDetails.getUsername(), emailService.getContentStringToBookAppointment(appointment), true);
            System.out.println("Email Sent SuccessFully");
            model.addAttribute("appointment_id", appointment.getAppointmentId().toString());
            return "successscreen";
        }
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
