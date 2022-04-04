package com.group6.careu.service;

import com.group6.careu.entity.Bank;
import com.group6.careu.entity.VISARequest;
import com.group6.careu.entity.VISAResponse;
import com.group6.careu.model.Request.VISARequestModel;
import com.group6.careu.model.Response.VISAResponseModel;
import com.group6.careu.repository.BankRepository;
import com.group6.careu.repository.TransactionsRepository;
import com.group6.careu.repository.VISARequestRepository;
import com.group6.careu.repository.VISAResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class VISAPaymentServiceImpl implements PaymentService<VISARequestModel, VISAResponseModel> {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private VISARequestRepository visaRequestRepository;

    @Autowired
    private VISAResponseRepository visaResponseRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Override
    public VISAResponseModel makePayment(VISARequestModel visaRequestModel) {

        VISARequest visaRequest=new VISARequest();
        visaRequest.setCardNumber(visaRequestModel.getCardNumber());
        visaRequest.setAmount(visaRequestModel.getAmount());
        visaRequest.setCvv(visaRequestModel.getCvv());
        visaRequest.setCardType(visaRequestModel.getTypeOfCard());

        visaRequest=visaRequestRepository.save(visaRequest);

        //VISAResponseModel visaResponseModel=new VISAResponseModel();

        boolean status=pay(visaRequestModel);

        System.out.println("Payment status: "+status);

        VISAResponse visaResponse=new VISAResponse();
        VISAResponseModel visaResponseModel=new VISAResponseModel();
        if(status){
            visaResponseModel.setStatusCode(200);
            visaResponseModel.setStatusDescription("Success");
            visaResponseModel.setTransactionId(visaRequest.getBankTransactionId());

            visaResponse.setStatusCode(visaResponseModel.getStatusCode());
            visaResponse.setStatusDescription(visaResponseModel.getStatusDescription());
            visaResponse.setTransactionId(visaResponseModel.getTransactionId());

            visaResponse=visaResponseRepository.save(visaResponse);
            visaResponseModel.setResponseId(visaResponse.getResponseId());

        }else{
            visaResponseModel.setStatusCode(400);
            visaResponseModel.setStatusDescription("Failure");
            visaResponseModel.setTransactionId(visaRequest.getBankTransactionId()); // Foreign Key from request
            System.out.println("In line 63; "+visaResponseModel);

            visaResponse.setStatusCode(visaResponseModel.getStatusCode());
            visaResponse.setStatusDescription(visaResponseModel.getStatusDescription());
            visaResponse.setTransactionId(visaResponseModel.getTransactionId());

            visaResponse=visaResponseRepository.save(visaResponse);
            visaResponseModel.setResponseId(visaResponse.getResponseId());

        }

        return visaResponseModel;
    }

    public boolean pay(VISARequestModel visaRequestModel){

        // Account Check
        Bank userByCardNumber=bankRepository.getUserByCardNumber(visaRequestModel.getCardNumber());
        //System.out.println(userByCardNumber.get(0).getCard_number());
        Bank customerAccount=new Bank();

        boolean accountCheck=false;

        int id;

        System.out.println(userByCardNumber);

        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println("Date Today Month--------------------->"+localDate.getMonthValue());
        System.out.println("Date Today Year---------------------->"+localDate.getYear());

        int month=localDate.getMonthValue();
        int year=localDate.getYear()%100;

        if(visaRequestModel.getCvv() == userByCardNumber.getCvv()
        && visaRequestModel.getExpiryYear()== userByCardNumber.getExpiryYear()
        && visaRequestModel.getExpiryMonth() == userByCardNumber.getExpiryMonth()){
            if(visaRequestModel.getExpiryYear()== year ){
                if(visaRequestModel.getExpiryMonth() >= month &&
                        visaRequestModel.getAmount() <= userByCardNumber.getAmount()){
                    accountCheck= true;
                    id=userByCardNumber.getId();
                    customerAccount=userByCardNumber;
                }
            }else{
                if(visaRequestModel.getExpiryMonth() >= month &&
                        visaRequestModel.getExpiryYear() >= year &&
                        visaRequestModel.getAmount() <= userByCardNumber.getAmount()){
                    accountCheck= true;
                    id=userByCardNumber.getId();
                    customerAccount=userByCardNumber;
                }
            }
        }

        if(!accountCheck){
            System.out.println("In Line 70");
            return false;
        }

        // Update Balance
        customerAccount.setAmount(customerAccount.getAmount() - visaRequestModel.getAmount());
        bankRepository.save(customerAccount);

        return true;
    }

}
