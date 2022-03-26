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

        }else{
            visaResponseModel.setStatusCode(400);
            visaResponseModel.setStatusDescription("Failure");
            visaResponseModel.setTransactionId(visaRequest.getBankTransactionId()); // Foreign Key from request
            System.out.println("In line 63; "+visaResponseModel);

            visaResponse.setStatusCode(visaResponseModel.getStatusCode());
            visaResponse.setStatusDescription(visaResponseModel.getStatusDescription());
            visaResponse.setTransactionId(visaResponseModel.getTransactionId());

            visaResponse=visaResponseRepository.save(visaResponse);
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

        if(visaRequestModel.getCvv() == userByCardNumber.getCvv()){
            if(visaRequestModel.getExpiryYear()== userByCardNumber.getExpiryYear()){
                if(visaRequestModel.getExpiryMonth() <= userByCardNumber.getExpiryMonth()){
                    accountCheck= true;
                    id=userByCardNumber.getId();
                    customerAccount=userByCardNumber;
                }
            }else{
                if(visaRequestModel.getExpiryMonth()<= userByCardNumber.getExpiryMonth() &&
                        visaRequestModel.getExpiryYear()<= userByCardNumber.getExpiryYear()){
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
