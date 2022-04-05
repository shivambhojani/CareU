package com.group6.careu.repository;

import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor,Integer> {
    @Query("FROM User WHERE id = :id")
    User findDoctor(@Param("id")Integer id);
}
