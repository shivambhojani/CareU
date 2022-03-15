package com.group6.careu.repository;

import com.group6.careu.entity.DoctorAvailability;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorAvailabilityRepository extends CrudRepository<DoctorAvailability, Integer> {

    @Query(value = "select * from doctor_availability da where da.doctor_id=:doctor_id", nativeQuery = true)
    public List<DoctorAvailability> getAvailableTimes(@Param("doctor_id") int doctor_id);
}
