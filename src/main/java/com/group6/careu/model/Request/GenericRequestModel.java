package com.group6.careu.model.Request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GenericRequestModel {
    private int userId;
    private int transactionId;
    private String email;
    private String cardNumber;
    private int expiryMonth;
    private int expiryYear;
    private int cvv;
    private String nameOnCard;
    private float amount;
    private String currency;
    private String country;
    private String zipcode;
    private String typeOfCard;
}
