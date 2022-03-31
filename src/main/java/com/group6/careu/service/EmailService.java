package com.group6.careu.service;

import com.group6.careu.entity.Appointment;
import com.group6.careu.entity.UserDocument;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public interface EmailService {

    void sendEmailForAppointment(String email, String content, boolean isBookingAppointment) throws MessagingException, UnsupportedEncodingException;

    String getContentStringToBookAppointment(Appointment appointment);

    String getContentForCancelAppointment(Appointment appointment);

    void sendEmailWithAttachment(UserDocument userDocument, String email, Appointment appointment) throws MessagingException, UnsupportedEncodingException;
}
