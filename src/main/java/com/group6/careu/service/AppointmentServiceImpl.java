package com.group6.careu.service;

import com.group6.careu.entity.Appointment;
import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.Patient;
import com.group6.careu.model.AppointmentModel;
import com.group6.careu.model.PatientAppointmentModel;
import com.group6.careu.repository.AppointmentRepository;
import com.group6.careu.security.CareuUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
import java.util.List;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public Appointment pushPatientAppointment(AppointmentModel appointmentModel) {
        Appointment appointment = getAppointmentDTO(appointmentModel);
        appointment = appointmentRepository.save(appointment);
        return appointment;
    }

    private Appointment getAppointmentDTO(AppointmentModel appointmentModel) {
        CareuUserDetails u = (CareuUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = new Patient();
        patient.setPatient_id(u.getId());
        Appointment appointment = new Appointment();
        appointment.setAppointment_date(appointmentModel.getAppointment_date());
        Doctor doctor = new Doctor();
        doctor.setDoctor_id(appointmentModel.getDoctor_id());
        appointment.setDoctor(doctor);
        appointment.setConsulationType(appointmentModel.getConsulationType());
        String[] arr = appointmentModel.getTime().split("to");
        appointment.setStartTime(Time.valueOf(arr[0].trim()));
        appointment.setEndTime(Time.valueOf(arr[1].trim()));
        appointment.setPatient(patient);
        return appointment;
    }



    @Override
    public List<Appointment> getPatientAppointments(Integer id) {
        return appointmentRepository.getAppointmentByPatientId(id);
    }
}
