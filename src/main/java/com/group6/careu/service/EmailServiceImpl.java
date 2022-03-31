package com.group6.careu.service;

import com.group6.careu.Utility;
import com.group6.careu.entity.Appointment;
import com.group6.careu.entity.UserDocument;
import com.group6.careu.security.CareuUserDetails;
import com.group6.careu.setting.EmailSetting;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.UnsupportedEncodingException;

@Service
public class EmailServiceImpl implements EmailService{

    @Override
    public void sendEmailForAppointment(String email, String content, boolean isBookingAppointment) throws MessagingException, UnsupportedEncodingException {
        EmailSetting emailSettings = new EmailSetting();
        JavaMailSenderImpl mailSender = Utility.prepareMailSender(emailSettings);
        String subject = "";
        if (isBookingAppointment) {
            subject = "Your appointment has been Booked!!";
        } else {
            subject = "Your appointment has been Cancelled!!";
        }
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(emailSettings.getMAIL_FROM(), emailSettings.getMAIL_SENDER_NAME());
        helper.setTo("johnvickyj7@gmail.com");
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }

    @Override
    public String getContentStringToBookAppointment(Appointment appointment) {
        return "Your appointment Id is - <b>" + appointment.getAppointmentId() + "</b>" +
                "<br>" +
                "<h4><b>Appointment Date : </b>" + appointment.getAppointment_date() +"</h4>" +
                "<h4><b>Appointment Start Time : </b>" + appointment.getStartTime() +"</h4>" +
                "<h4><b>Appointment End Time : </b>" + appointment.getEndTime() +"</h4>" +
                "<h4><b>Consultation Type : </b>" + appointment.getConsulationType() +"</h4>";
    }

    @Override
    public String getContentForCancelAppointment(Appointment appointment) {
        return "Your upcoming appointment with Id - <b>" + appointment.getAppointmentId() + "</b> and date - <b>"
                + appointment.getAppointment_date() + "</b> has been cancelled."
                + "<br>"
                + "<p>If you have not cancelled the appointment then please revert back to us by replying to this email.</p>"
                + "<p>Any issues related to cancelling appointments will be resolved in 2-3 hours so please be patient while our team revert back to you.</p>"
                + "<b>Thank you for using CareU</b>";
    }

    @Override
    public void sendEmailWithAttachment(UserDocument userDocument, String email, Appointment appointment) throws MessagingException, UnsupportedEncodingException {
        EmailSetting emailSettings = new EmailSetting();
        JavaMailSenderImpl mailSender = Utility.prepareMailSender(emailSettings);
        String subject = "Payment Reciept for you booking";
        BodyPart messageBodyPart1 = new MimeBodyPart();
        messageBodyPart1.setText(getContentStringForBillEmail());

        MimeBodyPart messageBodyPart2 = new MimeBodyPart();
        //change accordingly
        DataSource source = new ByteArrayDataSource(userDocument.getFileContent(),"application/bill_16.pdf");
        messageBodyPart2.setDataHandler(new DataHandler(source));
        messageBodyPart2.setFileName("Payment_reciept.pdf");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart1);
        multipart.addBodyPart(messageBodyPart2);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(emailSettings.getMAIL_FROM(), emailSettings.getMAIL_SENDER_NAME());
        helper.setTo("johnvickyj7@gmail.com");
        helper.setSubject(subject);
        String content = "Please keep the attached pdf for your future reference.";
        helper.setText(content, true);
        message.setContent(multipart);
        mailSender.send(message);
    }

    private String getContentStringForBillEmail() {
        CareuUserDetails careuUserDetails = (CareuUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "<p><b>Hi " + careuUserDetails.getFullname() + "</b></p>" +
                "<p>Your payment for the appointment has been processed successfully. If you have any queries related to payment of appointment please write to careumail@gmail.com. </p>" +
                "<p><b>NOTE: </b></p>" + "<p>Please mention your appointment or payment reference id for any queries.</p>";
    }
}
