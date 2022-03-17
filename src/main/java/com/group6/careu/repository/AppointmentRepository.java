package com.group6.careu.repository;

import com.group6.careu.entity.Appointment;
import com.group6.careu.model.PatientAppointmentModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {


    //@Query("From Appointment where Patient .patient_id=:patientId")

//    public Date getCurrentDate(){
//
//    }


    @Query(value = "Select * from appointment where patient_id=:patientId and appointment_date =:todayDate " +
            "order by appointment_date desc", nativeQuery = true)
    List<Appointment> getTodaysAppointmentByPatientId(Integer patientId, Date todayDate);


    @Query(value = "Select * from appointment where patient_id=:patientId and appointment_date >:todayDate " +
            "order by appointment_date desc ", nativeQuery = true)
    List<Appointment> getFutureAppointmentByPatientId(Integer patientId, Date todayDate);

    @Query(value = "Select * from appointment where patient_id=:patientId and appointment_date <:todayDate " +
            "order by appointment_date desc ", nativeQuery = true)
    List<Appointment> getPastAppointmentByPatientId(Integer patientId, Date todayDate);


}
