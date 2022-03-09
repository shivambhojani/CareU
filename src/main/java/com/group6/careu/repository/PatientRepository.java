package com.group6.careu.repository;


import com.group6.careu.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


public interface PatientRepository extends CrudRepository<User, Integer> {

    @Query("FROM User WHERE id = :id")
    User findPatient(@Param("id")Integer id);

    String updateQuery= "";

    @Transactional
    @Modifying
    @Query(value = "Update User set firstName=?2, lastName=?3, phone=?4, gender=?5 where id=?1")
    Integer updatePatientData(Integer id, String firstName, String lastName, String phone, String gender);


    @Transactional
    @Modifying
    @Query(value = "Update Patient set disease=?2 where patient_id=?1")
    Integer updatePatientDatainPatient(Integer id, String disease);

//
//    @Query(value = "Update User set firstName=:firstName, lastName=:lastName, phone=:phone, gender=:gender  where id=:id")




}
