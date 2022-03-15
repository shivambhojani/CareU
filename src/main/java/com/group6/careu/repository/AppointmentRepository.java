package com.group6.careu.repository;

import com.group6.careu.entity.Appointment;
import com.group6.careu.model.PatientAppointmentModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {




    //@Query("From Appointment where Patient .patient_id=:patientId")

    @Query(value = "Select * from appointment where patient_id=:patientId",nativeQuery = true)
    List<Appointment> getAppointmentByPatientId(Integer patientId);



}
