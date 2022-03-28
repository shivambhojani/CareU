package com.group6.careu.repository;

import com.group6.careu.entity.Bank;
import com.group6.careu.entity.User;;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends CrudRepository<Bank, Integer> {
    @Query(value = "SELECT * FROM bank b WHERE b.card_number=:card_number",nativeQuery = true)
    public Bank getUserByCardNumber(@Param("card_number") String card_number);



}