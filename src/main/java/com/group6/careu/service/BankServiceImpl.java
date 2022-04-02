package com.group6.careu.service;

import com.group6.careu.entity.AMEXResponse;
import com.group6.careu.entity.Transactions;
import com.group6.careu.entity.VISAResponse;
import com.group6.careu.model.Request.AMEXRequestModel;
import com.group6.careu.model.Request.VISARequestModel;
import com.group6.careu.model.Response.AMEXResponseModel;
import com.group6.careu.model.Response.VISAResponseModel;
import com.group6.careu.model.TransactionsModel;
import com.group6.careu.repository.BankRepository;
import com.group6.careu.repository.TransactionsRepository;
import com.group6.careu.security.CareuUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BankServiceImpl implements BankService{

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private VISAPaymentServiceImpl visaPaymentService;

    @Autowired
    private AMEXPaymentServiceImpl amexPaymentService;

    @Override
    public boolean updateBalance(int id){

        return false;
    }

    @Override
    public boolean processPayment(TransactionsModel transactionsModel){
        boolean status=false;

        System.out.println(transactionsModel);
        String cardNumber=transactionsModel.getCardNumber();
        String cardType = "";
        if(cardNumber.matches("^4[0-9]{0,15}$")){
            cardType="VISA";
        }else if(cardNumber.matches("^3$|^3[47][0-9]{0,13}$")){
            cardType="AMEX";
        }

        // What to do if cardType is null
        // Status Code: 455: Card Type Not Found

        System.out.println("Card Type: "+cardType);
        transactionsModel.setCardType(cardType);
        transactionsModel.setAmount(46);
        //transaction.setUsers();

        // Create Transaction Entity Object & Save Transaction
        Transactions transaction=new Transactions();
        transaction.setCardNumber(transactionsModel.getCardNumber());
        transaction.setFullName(transactionsModel.getFullName());
        transaction.setCardType(transactionsModel.getCardType());
        transaction.setTransactionStatus("In Progress");
        transaction.setAmount(transactionsModel.getAmount());

        transaction=saveTransaction(transaction);

        CareuUserDetails userDetails= (CareuUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId=userDetails.getId();

        if(cardType.equals("VISA")){
            VISARequestModel visaRequestModel = new VISARequestModel();

            visaRequestModel.setUserId(userId); // Change this
            visaRequestModel.setAmount(transactionsModel.getAmount());
            visaRequestModel.setCardNumber(transactionsModel.getCardNumber());
            visaRequestModel.setCountry(transactionsModel.getCountry());
            visaRequestModel.setCurrency("USD"); // Change this
            visaRequestModel.setCvv(transactionsModel.getCvv());
            visaRequestModel.setEmail(transactionsModel.getEmail());
            visaRequestModel.setExpiryMonth(transactionsModel.getExpiryMonth());
            visaRequestModel.setExpiryYear(transactionsModel.getExpiryYear());
            visaRequestModel.setNameOnCard(transactionsModel.getFullName());
            visaRequestModel.setTypeOfCard(transactionsModel.getCardType());
            visaRequestModel.setZipcode(transactionsModel.getZipcode());
            visaRequestModel.setTransactionId(transaction.getTransaction_id());

            VISAResponseModel visaResponseModel= visaPaymentService.makePayment(visaRequestModel);

            //System.out.println(response);
            System.out.println("Response Id: "+visaResponseModel.getResponseId());
            if(visaResponseModel.getStatusCode() == HttpStatus.OK.value()){
                status=true;
            }else{
                status=false;
            }

            updateTransaction(transaction,visaResponseModel);


        }else if(cardType.equals("AMEX")){
            AMEXRequestModel amexRequestModel = new AMEXRequestModel();

            amexRequestModel.setUserId(userId); // Change this
            amexRequestModel.setAmount(transactionsModel.getAmount());
            amexRequestModel.setCardNumber(transactionsModel.getCardNumber());
            amexRequestModel.setCountry(transactionsModel.getCountry());
            amexRequestModel.setCurrency("USD"); // Change this
            amexRequestModel.setCvv(transactionsModel.getCvv());
            amexRequestModel.setEmail(transactionsModel.getEmail());
            amexRequestModel.setExpiryMonth(transactionsModel.getExpiryMonth());
            amexRequestModel.setExpiryYear(transactionsModel.getExpiryYear());
            amexRequestModel.setNameOnCard(transactionsModel.getFullName());
            amexRequestModel.setTypeOfCard(transactionsModel.getCardType());
            amexRequestModel.setZipcode(transactionsModel.getZipcode());
            amexRequestModel.setTransactionId(transaction.getTransaction_id());
            amexRequestModel.setCreditorId("A2345");
            amexRequestModel.setCreditorName("CareU");

            AMEXResponseModel amexResponseModel= amexPaymentService.makePayment(amexRequestModel);

            //System.out.println(response);
            System.out.println("Response Id: "+amexResponseModel.getResponseId());
            if(amexResponseModel.getStatusCode() == HttpStatus.OK.value()){
                status=true;
            }else{
                status=false;
            }

            updateTransaction(transaction,amexResponseModel);
        }

        return status;
    }

    @Override
    public Transactions saveTransaction(Transactions transaction){
        String maskedCardNumber="";
        String cardNumber=transaction.getCardNumber();
        for(int i=0;i<cardNumber.length();i++) {
            if(i<cardNumber.length()-4){
                maskedCardNumber=maskedCardNumber+'#';
            }else{
                maskedCardNumber=maskedCardNumber+cardNumber.charAt(i);
            }
        }
        System.out.println("Masked Card Number: "+maskedCardNumber);
        transaction.setCardNumber(maskedCardNumber);
        transaction=transactionsRepository.save(transaction);
        System.out.println("Transaction Id: "+ transaction.getTransaction_id());
        return transaction;
    }

    @Override
    public void updateTransaction(Transactions transaction,VISAResponseModel response ){
        if(response.getStatusCode()==HttpStatus.OK.value()){
            transaction.setTransactionStatus("Completed");
        }else{
            transaction.setTransactionStatus("Failure");
        }
        //transaction.setBankReferenceCode(response.getResponseId());
        //transaction.setStatusCode(response.getStatusCode());
        //transaction.setStatusDescription(response.getStatusDescription());

        Transactions transactionById= transactionsRepository.getTransactionById(transaction.getTransaction_id());

        transactionById.setStatusCode(response.getStatusCode());
        transactionById.setBankReferenceCode(response.getResponseId());
        transactionById.setStatusDescription(response.getStatusDescription());
        transactionsRepository.save(transactionById);
    }

    @Override
    public void updateTransaction(Transactions transaction, AMEXResponseModel response ){
        if(response.getStatusCode()==HttpStatus.OK.value()){
            transaction.setTransactionStatus("Completed");
        }else{
            transaction.setTransactionStatus("Failure");
        }
        //transaction.setBankReferenceCode(response.getResponseId());
        //transaction.setStatusCode(response.getStatusCode());
        //transaction.setStatusDescription(response.getStatusDescription());

        Transactions transactionById= transactionsRepository.getTransactionById(transaction.getTransaction_id());

        transactionById.setStatusCode(response.getStatusCode());
        transactionById.setBankReferenceCode(response.getResponseId());
        transactionById.setStatusDescription(response.getStatusDescription());
        transactionsRepository.save(transactionById);
    }
}
