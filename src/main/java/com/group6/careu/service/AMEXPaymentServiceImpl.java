package com.group6.careu.service;

import com.group6.careu.entity.*;
import com.group6.careu.model.Request.AMEXRequestModel;
import com.group6.careu.model.Response.AMEXResponseModel;
import com.group6.careu.repository.AMEXRequestRepository;
import com.group6.careu.repository.AMEXResponseRepository;
import com.group6.careu.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AMEXPaymentServiceImpl implements PaymentService<AMEXRequestModel, AMEXResponseModel> {
    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private AMEXRequestRepository amexRequestRepository;

    @Autowired
    private AMEXResponseRepository amexResponseRepository;

    @Override
    public AMEXResponseModel makePayment(AMEXRequestModel amexRequestModel) {
        AMEXRequest amexRequest=new AMEXRequest();
        amexRequest.setCardNumber(amexRequestModel.getCardNumber());
        amexRequest.setAmount(amexRequestModel.getAmount());
        amexRequest.setCvv(amexRequestModel.getCvv());
        amexRequest.setCardType(amexRequestModel.getTypeOfCard());
        amexRequest.setCreditorId(amexRequestModel.getCreditorId());
        amexRequest.setCreditorName(amexRequestModel.getCreditorName());

        amexRequest=amexRequestRepository.save(amexRequest);

        boolean status=pay(amexRequestModel);

        System.out.println("Payment status: "+status);

        AMEXResponse amexResponse=new AMEXResponse();
        AMEXResponseModel amexResponseModel=new AMEXResponseModel();
        if(status){
            amexResponseModel.setStatusCode(HttpStatus.OK.value());
            amexResponseModel.setStatusDescription("Success");
            amexResponseModel.setTransactionId(amexRequest.getBankTransactionId());

            amexResponse.setStatusCode(amexResponseModel.getStatusCode());
            amexResponse.setStatusDescription(amexResponseModel.getStatusDescription());
            amexResponse.setTransactionId(amexResponseModel.getTransactionId());

            amexResponse=amexResponseRepository.save(amexResponse);

        }else{
            amexResponseModel.setStatusCode(HttpStatus.BAD_REQUEST.value());
            amexResponseModel.setStatusDescription("Failure");
            amexResponseModel.setTransactionId(amexRequest.getBankTransactionId()); // Foreign Key from request
            System.out.println("In line 63; "+amexResponseModel);

            amexResponse.setStatusCode(amexResponseModel.getStatusCode());
            amexResponse.setStatusDescription(amexResponseModel.getStatusDescription());
            amexResponse.setTransactionId(amexResponseModel.getTransactionId());

            amexResponse=amexResponseRepository.save(amexResponse);
        }

        return amexResponseModel;
    }

    public boolean pay(AMEXRequestModel amexRequestModel){

        // Account Check
        Bank userByCardNumber=bankRepository.getUserByCardNumber(amexRequestModel.getCardNumber());
        //System.out.println(userByCardNumber.get(0).getCard_number());
        Bank customerAccount=new Bank();

        boolean accountCheck=false;

        int id;


        if(amexRequestModel.getCvv() == userByCardNumber.getCvv()){
            if(amexRequestModel.getExpiryYear()== userByCardNumber.getExpiryYear()){
                if(amexRequestModel.getExpiryMonth() <= userByCardNumber.getExpiryMonth()){
                    accountCheck= true;
                    id=userByCardNumber.getId();
                    customerAccount=userByCardNumber;
                }
            }else{
                 boolean checkExpiryMonth = amexRequestModel.getExpiryMonth()<= userByCardNumber.getExpiryMonth();
                 boolean checkExpiryYear = amexRequestModel.getExpiryYear()<= userByCardNumber.getExpiryYear();
                if(checkExpiryMonth &&
                        checkExpiryYear){
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
        customerAccount.setAmount(customerAccount.getAmount() - amexRequestModel.getAmount());
        bankRepository.save(customerAccount);

        return true;
    }
}
