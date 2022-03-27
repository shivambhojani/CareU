package com.group6.careu.repository;

import com.group6.careu.entity.Appointment;
import com.group6.careu.model.PatientAppointmentModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {


    //@Query("From Appointment where Patient .patient_id=:patientId")

    //    public Date getCurrentDate(){
//
//    }
    @Transactional
    @Modifying
    @Query(value = "Update Appointment set patientFeedback=?2 where appointmentId=?1")
    Integer updatePatientFeedback(UUID appointmentID, String patientFeedback);

    Appointment getAppointmentsByAppointmentId(UUID appointment_id);

    @Query(value = "Select * from appointment where patient_id=:patientId and appointment_date =:todayDate " +
            "order by appointment_date desc", nativeQuery = true)
    List<Appointment> getTodaysAppointmentByPatientId(Integer patientId, Date todayDate);


    @Query(value = "Select * from appointment where patient_id=:patientId and appointment_date >:todayDate " +
            "order by appointment_date desc ", nativeQuery = true)
    List<Appointment> getFutureAppointmentByPatientId(Integer patientId, Date todayDate);

    @Query(value = "Select * from appointment where patient_id=:patientId and appointment_date <:todayDate " +
            "order by appointment_date desc ", nativeQuery = true)
    List<Appointment> getPastAppointmentByPatientId(Integer patientId, Date todayDate);
	
	@Query(value = "Select * from appointment where doctor_id=:doctorId and appointment_date =:todayDate " +
            "order by appointment_date desc", nativeQuery = true)
    List<Appointment> getTodaysAppointmentByDoctorId(Integer doctorId, Date todayDate);

    @Query(value = "Select * from appointment where doctor_id=:doctorId and appointment_date >:todayDate " +
            "order by appointment_date desc ", nativeQuery = true)
    List<Appointment> getFutureAppointmentByDoctorId(Integer doctorId, Date todayDate);

    @Query(value = "Select * from appointment where doctor_id=:doctorId and appointment_date <:todayDate " +
            "order by appointment_date desc ", nativeQuery = true)
    List<Appointment> getPastAppointmentByDoctorId(Integer doctorId, Date todayDate);


    @Query(value = "DELETE from appointment where appointment_id=:apptId", nativeQuery = true)
    void deleteAppointmentBasedOnId(String apptId);

    @Query(value = "SELECT * from appointment where appointment_id=:apptId", nativeQuery = true)
    Appointment getAppointmentBasedOnApptId(String apptId);
}
