package com.group6.careu.service;

import com.group6.careu.entity.Appointment;
import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.Patient;
import com.group6.careu.entity.User;
import com.group6.careu.model.AppointmentModel;
import com.group6.careu.model.PatientAppointmentModel;
import com.group6.careu.repository.AppointmentRepository;
import com.group6.careu.repository.UserRepository;
import com.group6.careu.security.CareuUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.UUID;

/**
 * Created by Sai Chinthirla on 03, 2022
 */

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Appointment pushPatientAppointment(AppointmentModel appointmentModel) {
        CareuUserDetails u = (CareuUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.getUserById(u.getId());
        Appointment appointment = getAppointmentDTO(appointmentModel,user.getPatient().getPatient_id());
        appointment = appointmentRepository.save(appointment);
        return appointment;
    }

    private Appointment getAppointmentDTO(AppointmentModel appointmentModel,Integer patientId) {
        Patient patient = new Patient();
        patient.setPatient_id(patientId);
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
    public List<Appointment> getTodaysPatientAppointments(Integer id, Date date) {
        return appointmentRepository.getTodaysAppointmentByPatientId(id, date);
    }

    @Override
    public List<Appointment> getPatientFutureAppointments(Integer id, Date date) {
        return appointmentRepository.getFutureAppointmentByPatientId(id, date);
    }

    @Override
    public List<Appointment> getPatientPastAppointments(Integer id, Date date) {
        return appointmentRepository.getPastAppointmentByPatientId(id, date);
    }

    @Override
    public Appointment getAppointmentsByAppointmentId(UUID appointment_id) {
        return appointmentRepository.getAppointmentsByAppointmentId(appointment_id);
    }

    @Override
    public Integer updatePatientFeedback(UUID appointment_id, String patientFeedback) {
        return appointmentRepository.updatePatientFeedback(appointment_id, patientFeedback);
    }

    @Override
    public Integer updateMedication(UUID appointment_id, String medications) {
        return appointmentRepository.updateMedication(appointment_id, medications);
    }

    @Override
    public List<Appointment> getDoctorTodaysAppointments(Integer id, Date date) {
        return appointmentRepository.getTodaysAppointmentByDoctorId(id, date);
    }

    @Override
    public List<Appointment> getDoctorFutureAppointments(Integer id, Date date) {
        return appointmentRepository.getFutureAppointmentByDoctorId(id, date);
    }

    @Override
    public List<Appointment> getDoctorPastAppointments(Integer id, Date date) {
        return appointmentRepository.getPastAppointmentByDoctorId(id, date);
    }

    @Override
    public Appointment deleteAppointmentBasedOnId(String apptId) {
        Appointment appointment = appointmentRepository.getAppointmentBasedOnApptId(apptId);
        appointmentRepository.delete(appointment);
        return appointment;
    }
}

