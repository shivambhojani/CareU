package com.group6.careu.service;

import com.group6.careu.entity.AMEXResponse;
import com.group6.careu.entity.VISAResponse;
import com.group6.careu.model.Request.GenericRequestModel;
import com.group6.careu.model.Response.GenericResponseModel;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService<T extends GenericRequestModel,R extends GenericResponseModel> {
    R makePayment(T paymentRequest);
}
