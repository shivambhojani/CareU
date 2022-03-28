package com.group6.careu.repository;

import com.group6.careu.entity.Bank;
import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.Transactions;
import com.group6.careu.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TransactionsRepository extends CrudRepository<Transactions,Integer> {
    @Query(value = "SELECT * FROM transactions t WHERE t.transaction_id=:transaction_id",nativeQuery = true)
    public Transactions getTransactionById(@Param("transaction_id") Integer transaction_id);

}
