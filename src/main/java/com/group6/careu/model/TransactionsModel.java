package com.group6.careu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsModel {

    private String email;
    private String country;
    private String zipcode;
    private String fullName;
    private String cardNumber;
    private String cardType;
    private String transactionStatus;
    private String bankReferenceCode;
    private float amount;
    private Integer statusCode;
    private String statusDescription;
    private Integer expiryMonth;
    private Integer expiryYear;
    private Integer cvv;
    private Timestamp createdOn;
    private Timestamp updatedOn;

}
