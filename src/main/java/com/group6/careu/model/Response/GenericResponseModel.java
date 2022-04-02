package com.group6.careu.model.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GenericResponseModel {
    private Integer responseId;
    private int statusCode;
    private String statusDescription;
    private int transactionId;
}
