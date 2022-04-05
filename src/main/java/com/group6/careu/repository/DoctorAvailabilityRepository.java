package com.group6.careu.repository;

import com.group6.careu.entity.DoctorAvailability;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface DoctorAvailabilityRepository extends CrudRepository<DoctorAvailability, Integer> {

    @Query(value = "select * from doctor_availability da where da.doctor_id=:doctor_id", nativeQuery = true)
    public List<DoctorAvailability> getAvailableTimes(@Param("doctor_id") int doctor_id);

    @Query(value = "select * from doctor_availability da where da.doctor_id=:doctor_id and da.available_date=:available_date and da.appointment_booked=0", nativeQuery = true)
    public List<DoctorAvailability> getAvailableTimeOfDoctorBasedOnDate(@Param("doctor_id") int doctor_id, @Param("available_date") Date available_date);

    @Query(value = "UPDATE doctor_availability da SET da.appointment_booked=:appointmentBooked WHERE da.doctor_id=:doctor_id and da.start_time=:startTime and da.available_date=:availableDate", nativeQuery = true)
    @Modifying
    public void updateBookedAppointment(@Param("doctor_id") int doctor_id, @Param("availableDate") Date availableDate, @Param("startTime") Time startTime, @Param("appointmentBooked") boolean appointmentBooked);
}
