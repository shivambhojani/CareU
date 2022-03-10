package com.group6.careu.repository;

import com.group6.careu.entity.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
}
