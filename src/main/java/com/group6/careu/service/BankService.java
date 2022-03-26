package com.group6.careu.service;

import com.group6.careu.entity.AMEXResponse;
import com.group6.careu.entity.Bank;
import com.group6.careu.entity.Transactions;
import com.group6.careu.entity.VISAResponse;
import com.group6.careu.model.Response.AMEXResponseModel;
import com.group6.careu.model.Response.VISAResponseModel;
import com.group6.careu.model.TransactionsModel;
import org.springframework.stereotype.Service;

@Service
public interface BankService {
    //boolean paymentCompletion(Transactions transactions);
    boolean updateBalance(int id);
    boolean processPayment(TransactionsModel transactionsModel);
    Transactions saveTransaction(Transactions transaction);
    void updateTransaction(Transactions transactions, VISAResponseModel response );
    void updateTransaction(Transactions transactions, AMEXResponseModel response );
}
